/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.service;

import com.xqsight.common.dao.Dao;
import com.xqsight.common.service.DefaultEntityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xqsight.system.model.SysRole;
import com.xqsight.system.mapper.SysRoleMapper;
import org.springframework.transaction.annotation.Transactional;


/**
 * <p>角色信息表实现类service</p>
 * <p>Table: sys_role - 角色信息表</p>
 * @since 2017-01-05 06:11:10
 * @author wangganggang
 */
@Service
@Transactional
public class SysRoleService extends DefaultEntityService<SysRole, Long> {

	@Autowired
	private SysRoleMapper sysRoleMapper;

	@Override
	protected Dao<SysRole, Long> getDao() {
		return sysRoleMapper;
	}
}