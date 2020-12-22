package com.sbs.cwy.at.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbs.cwy.at.dto.Job;
import com.sbs.cwy.at.dto.Recruitment;

@Mapper
public interface RecruitmentDao {
	List<Recruitment> getForPrintRecruitments();

	Recruitment getForPrintRecruitmentById(@Param("id") int id);

	Recruitment getRecruitmentById(@Param("id") int id);

	void write(Map<String, Object> param);

	void modify(Map<String, Object> param);

	Job getJobByCode(String jobCode);
}
