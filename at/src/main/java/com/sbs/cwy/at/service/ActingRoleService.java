package com.sbs.cwy.at.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.cwy.at.dao.ActingRoleDao;
import com.sbs.cwy.at.dto.ActingRole;
import com.sbs.cwy.at.dto.Artwork;
import com.sbs.cwy.at.dto.Member;
import com.sbs.cwy.at.util.Util;

@Service
public class ActingRoleService {
	@Autowired
	private ActingRoleDao actingRoleDao;

	public List<ActingRole> getRoles() {
		return actingRoleDao.getRoles();
	}

	public List<ActingRole> getForPrintRoles() {
		return actingRoleDao.getRoles();
	}

	public List<Artwork> getArtworks() {
		return actingRoleDao.getArtworks();
	}

	public int write(Map<String, Object> param) {
		actingRoleDao.write(param);
		int id = Util.getAsInt(param.get("id"));

		return id;
	}

	public ActingRole getForPrintActingRoleById(Member loginedMember, int id) {
		return actingRoleDao.getForPrintActingRoleById(id);
	}

	public void modify(Map<String, Object> param) {
		actingRoleDao.modify(param);
	}

	public void delete(int id) {
		actingRoleDao.delete(id);
	}

	public int writeArtwork(Map<String, Object> param) {
		actingRoleDao.writeArtwork(param);
		int id = Util.getAsInt(param.get("id"));

		return id;
	}

	public Artwork getForPrintArtworkById(Member loginedMember, int id) {
		return actingRoleDao.getForPrintArtworkById(id);
	}

	public void modifyArtwork(Map<String, Object> param) {
		actingRoleDao.modifyArtwork(param);
	}

	public void deleteArtwork(int id) {
		actingRoleDao.deleteArtwork(id);
	}

}
