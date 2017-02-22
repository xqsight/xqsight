/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.service;

import com.xqsight.common.core.dao.Dao;
import com.xqsight.common.core.service.DefaultEntityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xqsight.system.model.SysOffice;
import com.xqsight.system.mapper.SysOfficeMapper;


/**
 * <p>机构表实现类service</p>
 * <p>Table: sys_office - 机构表</p>
 * @since 2017-02-22 04:29:58
 * @author wangganggang
 */
@Service
public class SysOfficeService extends DefaultEntityService<SysOffice, Long> {

	@Autowired
	private SysOfficeMapper sysOfficeMapper;

	@Override
	protected Dao<SysOffice, Long> getDao() {
		return sysOfficeMapper;
	}
}