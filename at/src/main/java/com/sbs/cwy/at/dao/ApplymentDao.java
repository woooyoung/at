package com.sbs.cwy.at.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbs.cwy.at.dto.Applyment;

@Mapper
public interface ApplymentDao {
	List<Applyment> getForPrintApplyments(Map<String, Object> param);

	void writeApplyment(Map<String, Object> param);

	void deleteApplyment(@Param("id") int id);

	Applyment getForPrintApplymentById(@Param("id") int id);

	void modifyApplyment(Map<String, Object> param);
}
