/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.service;

import com.xqsight.common.dao.Dao;
import com.xqsight.common.service.DefaultEntityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xqsight.system.model.SysLog;
import com.xqsight.system.mapper.SysLogMapper;


/**
 * <p>系统日志实现类service</p>
 * <p>Table: sys_log - 系统日志</p>
 * @since 2017-01-05 06:10:44
 * @author wangganggang
 */
@Service
public class SysLogService extends DefaultEntityService<SysLog, Long> {

	@Autowired
	private SysLogMapper sysLogMapper;

	@Override
	protected Dao<SysLog, Long> getDao() {
		return sysLogMapper;
	}
}