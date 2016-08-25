/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 
package com.xqsight.sys.service.impl;


import com.alibaba.fastjson.JSON;
import com.xqsight.sys.model.SysLog;
import com.xqsight.sys.mysqlmapper.SysLogMapper;
import com.xqsight.sys.service.SysLogService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>系统日志接口实现类service</p>
 * <p>Table: sys_log - 系统日志</p>
 * @since 2016-08-24 05:17:09
 */
 @Service
public class SysLogServiceImpl implements SysLogService {

	private static Logger logger = LogManager.getLogger(SysLogServiceImpl.class); 
	
	@Autowired
	private SysLogMapper sysLogMapper;

	@Override
	public void saveSysLog(SysLog sysLog){
		logger.debug("出入参数:sysLog＝{}", JSON.toJSONString(sysLog));
		sysLogMapper.saveSysLog(sysLog);
	}
	
	@Override
	public void updateSysLog(SysLog sysLog) {
		logger.debug("出入参数:sysLog＝{}", JSON.toJSONString(sysLog));
		sysLogMapper.updateSysLog(sysLog);
	}
	
	@Override
	public void deleteSysLog(Long logId) {
		logger.debug("出入参数:logId＝{}", logId);
		sysLogMapper.deleteSysLog(logId);
	}
	
	@Override
	public SysLog querySysLogById(Long logId ){
		return sysLogMapper.querySysLogById(logId);
	}
	
	@Override
	public List<SysLog> querySysLog() {
		return sysLogMapper.querySysLog();
	}
}