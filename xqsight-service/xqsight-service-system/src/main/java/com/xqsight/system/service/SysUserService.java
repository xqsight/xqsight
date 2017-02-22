/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.service;

import com.xqsight.common.core.dao.Dao;
import com.xqsight.common.core.service.DefaultEntityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xqsight.system.model.SysUser;
import com.xqsight.system.mapper.SysUserMapper;


/**
 * <p>用户信息表实现类service</p>
 * <p>Table: sys_user - 用户信息表</p>
 * @since 2017-02-22 04:31:43
 * @author wangganggang
 */
@Service
public class SysUserService extends DefaultEntityService<SysUser, Long> {

	@Autowired
	private SysUserMapper sysUserMapper;

	@Override
	protected Dao<SysUser, Long> getDao() {
		return sysUserMapper;
	}
}