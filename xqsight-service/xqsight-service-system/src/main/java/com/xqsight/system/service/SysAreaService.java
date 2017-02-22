/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.service;

import com.xqsight.common.core.dao.Dao;
import com.xqsight.common.core.service.DefaultEntityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xqsight.system.model.SysArea;
import com.xqsight.system.mapper.SysAreaMapper;


/**
 * <p>区域表实现类service</p>
 * <p>Table: sys_area - 区域表</p>
 * @since 2017-02-22 04:29:28
 * @author wangganggang
 */
@Service
public class SysAreaService extends DefaultEntityService<SysArea, Long> {

	@Autowired
	private SysAreaMapper sysAreaMapper;

	@Override
	protected Dao<SysArea, Long> getDao() {
		return sysAreaMapper;
	}
}