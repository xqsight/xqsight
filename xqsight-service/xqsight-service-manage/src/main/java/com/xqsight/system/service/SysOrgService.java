/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.service;

import com.xqsight.common.dao.Dao;
import com.xqsight.common.service.DefaultEntityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xqsight.system.model.SysOrg;
import com.xqsight.system.mapper.SysOrgMapper;


/**
 * <p>组织机构表实现类service</p>
 * <p>Table: sys_org - 组织机构表</p>
 * @since 2017-01-05 06:11:00
 * @author wangganggang
 */
@Service
public class SysOrgService extends DefaultEntityService<SysOrg, Long> {

	@Autowired
	private SysOrgMapper sysOrgMapper;

	@Override
	protected Dao<SysOrg, Long> getDao() {
		return sysOrgMapper;
	}
}