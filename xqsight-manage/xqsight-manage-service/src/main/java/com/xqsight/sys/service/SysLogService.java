package com.xqsight.sys.service;

import com.xqsight.sys.model.SysLog;

import java.util.List;

/**
 * <p>系统日志接口类service</p>
 * <p>Table: sys_log - 系统日志</p>
 * @since 2016-08-24 05:17:09
 */
public interface SysLogService {
	
	/** 保存 **/
	void saveSysLog(SysLog sysLog);
	
	/** 修改 **/
	void updateSysLog(SysLog sysLog);
	
	/** 删除 **/
	void deleteSysLog(Long logId);
	
	/** 根据Id查询 **/
	SysLog querySysLogById(Long logId);
	
	/** 查询列表 **/
	List<SysLog> querySysLog();
}