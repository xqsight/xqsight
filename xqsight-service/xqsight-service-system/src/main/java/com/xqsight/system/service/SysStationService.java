/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.service;

import com.xqsight.common.core.dao.Dao;
import com.xqsight.common.core.service.DefaultEntityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xqsight.system.model.SysStation;
import com.xqsight.system.mapper.SysStationMapper;


/**
 * <p>岗位信息表实现类service</p>
 * <p>Table: sys_station - 岗位信息表</p>
 * @since 2017-02-22 04:31:36
 * @author wangganggang
 */
@Service
public class SysStationService extends DefaultEntityService<SysStation, Long> {

	@Autowired
	private SysStationMapper sysStationMapper;

	@Override
	protected Dao<SysStation, Long> getDao() {
		return sysStationMapper;
	}
}