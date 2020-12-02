package com.sbs.cwy.at.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbs.cwy.at.dto.Member;

@Mapper
public interface MemberDao {

	Member getMemberById(@Param("id") int id);

}
