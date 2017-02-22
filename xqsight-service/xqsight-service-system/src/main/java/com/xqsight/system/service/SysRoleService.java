/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.service;

import com.xqsight.common.core.dao.Dao;
import com.xqsight.common.core.service.DefaultEntityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xqsight.system.model.SysRole;
import com.xqsight.system.mapper.SysRoleMapper;


/**
 * <p>角色信息表实现类service</p>
 * <p>Table: sys_role - 角色信息表</p>
 * @since 2017-02-22 04:30:04
 * @author wangganggang
 */
@Service
public class SysRoleService extends DefaultEntityService<SysRole, Long> {

	@Autowired
	private SysRoleMapper sysRoleMapper;

	@Override
	protected Dao<SysRole, Long> getDao() {
		return sysRoleMapper;
	}
}