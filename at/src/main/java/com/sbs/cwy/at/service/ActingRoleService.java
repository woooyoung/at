package com.sbs.cwy.at.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.cwy.at.dto.ActingRole;
import com.sbs.cwy.at.dao.ActingRoleDao;

@Service
public class ActingRoleService {
	@Autowired
	private ActingRoleDao actingRoleDao;

	public List<ActingRole> getRoles() {
		return actingRoleDao.getRoles();
	}

}
