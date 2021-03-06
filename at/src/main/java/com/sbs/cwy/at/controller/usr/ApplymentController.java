package com.sbs.cwy.at.controller.usr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.cwy.at.config.AppConfig;
import com.sbs.cwy.at.dto.Applyment;
import com.sbs.cwy.at.dto.Member;
import com.sbs.cwy.at.dto.ResultData;
import com.sbs.cwy.at.service.ApplymentService;
import com.sbs.cwy.at.service.FileService;
import com.sbs.cwy.at.service.RecruitmentService;
import com.sbs.cwy.at.util.Util;

@Controller
public class ApplymentController {
	@Autowired
	private ApplymentService applymentService;
	@Autowired
	private RecruitmentService recruitmentService;
	@Autowired
	private AppConfig appConfig;
	@Autowired
	private FileService fileService;

	@RequestMapping("/usr/applyment/getForPrintApplyments")
	@ResponseBody
	public ResultData getForPrintApplyments(@RequestParam Map<String, Object> param, HttpServletRequest req) {
		Member loginedMember = (Member) req.getAttribute("loginedMember");
		Map<String, Object> rsDataBody = new HashMap<>();

		param.put("relTypeCode", "recruitment");
		Util.changeMapKey(param, "recruitmentId", "relId");

		boolean actorIsWriter = recruitmentService.actorIsWriter(loginedMember, Util.getAsInt(param.get("relId")));

		if (actorIsWriter == false) {
			param.put("memberId", loginedMember.getId());
		}

		param.put("actor", loginedMember);
		List<Applyment> applyments = applymentService.getForPrintApplyments(param);
		rsDataBody.put("applyments", applyments);

		return new ResultData("S-1", String.format("%d개의 신청을 불러왔습니다.", applyments.size()), rsDataBody);
	}

	@RequestMapping("/usr/applyment/doWriteApplymentAjax")
	@ResponseBody
	public ResultData doWriteApplymentAjax(@RequestParam Map<String, Object> param, HttpServletRequest request) {
		Map<String, Object> rsDataBody = new HashMap<>();

		Member loginedMember = (Member) request.getAttribute("loginedMember");
		param.put("memberId", request.getAttribute("loginedMemberId"));

		ResultData checkWriteApplymentAvailableResultData = applymentService.checkActorCanWriteApplyment(loginedMember,
				(String) param.get("relTypeCode"), Util.getAsInt(param.get("relId")));

		if (checkWriteApplymentAvailableResultData.isFail()) {
			fileService.deleteFiles((String) param.get("fileIdsStr"));

			return checkWriteApplymentAvailableResultData;
		}

		int newApplymentId = applymentService.writeApplyment(param);
		rsDataBody.put("applymentId", newApplymentId);

		return new ResultData("S-1",
				String.format("신청이 완료되었습니다, " + appConfig.getModifyAvailablePerioMinutes() + "분 이내에만 삭제, 수정이 가능합니다.",
						newApplymentId),
				rsDataBody);
	}

	@RequestMapping("/usr/applyment/doDeleteApplymentAjax")
	@ResponseBody
	public ResultData doDeleteApplymentAjax(int id, HttpServletRequest req) {
		Member loginedMember = (Member) req.getAttribute("loginedMember");
		Applyment applyment = applymentService.getForPrintApplymentById(id);

		if (applymentService.actorCanDelete(loginedMember, applyment) == false) {
			return new ResultData("F-1", String.format("%d번 신청을 삭제할 권한이 없습니다.", id));
		}

		applymentService.deleteApplyment(id);

		return new ResultData("S-1", String.format("%d번 신청을 삭제하였습니다.", id));
	}

	@RequestMapping("/usr/applyment/doModifyApplymentAjax")
	@ResponseBody
	public ResultData doModifyApplymentAjax(@RequestParam Map<String, Object> param, HttpServletRequest req, int id) {
		Member loginedMember = (Member) req.getAttribute("loginedMember");
		Applyment applyment = applymentService.getForPrintApplymentById(id);

		if (applymentService.actorCanModify(loginedMember, applyment) == false) {
			return new ResultData("F-1", String.format("%d번 신청을 수정할 권한이 없습니다.", id));
		}

		Map<String, Object> modfiyApplymentParam = Util.getNewMapOf(param, "id", "body");
		ResultData rd = applymentService.modfiyApplyment(modfiyApplymentParam);

		return rd;
	}

	@RequestMapping("/usr/applyment/doSetVisible")
	@ResponseBody
	public ResultData doSetVisible(HttpServletRequest req, int id, String value) {
		Member loginedMember = (Member) req.getAttribute("loginedMember");
		Applyment applyment = applymentService.getForPrintApplymentById(id);

		if (applymentService.actorCanToggle(loginedMember, applyment) == false) {
			return new ResultData("F-1", String.format("%d번 신청을 수정할 권한이 없습니다.", id));
		}

		ResultData rd = applymentService.changeHideStatus(id, value.trim().equals("hide"));

		return rd;
	}
}