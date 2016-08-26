package com.xqsight.sys.controller;

import com.github.pagehelper.Page;
import com.xqsight.common.model.XqsightPage;
import com.xqsight.common.support.MessageSupport;
import com.xqsight.common.support.XqsightPageHelper;
import com.xqsight.sso.utils.SSOUtils;
import com.xqsight.sys.model.SysLog;
import com.xqsight.sys.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>系统日志 controller</p>
 * <p>Table: sys_log - 系统日志</p>
 * @since 2016-08-24 05:17:09
 */
@RestController
@RequestMapping("/sys/log/")
public class SysLogController{

	@Autowired
	private SysLogService sysLogService;

	@RequestMapping("save")
	public Object saveSysLog(SysLog sysLog) {
		sysLog.setCreateOprId(SSOUtils.getCurrentUserId().toString());
		sysLogService.saveSysLog(sysLog);
		return MessageSupport.successMsg("保存成功");
	}
	
	@RequestMapping("update")
	public Object updateSysLog(SysLog sysLog) {
		sysLog.setUpdOprId(SSOUtils.getCurrentUserId().toString());
		sysLogService.updateSysLog(sysLog);
		return MessageSupport.successMsg("修改成功");
	}
	
	@RequestMapping("delete")
	public Object deleteSysLog(Long logId) {
		sysLogService.deleteSysLog(logId);
		return MessageSupport.successMsg("删除成功");
	}
	
	@RequestMapping("querybyid")
	public Object querySysLog(Long logId) {
		SysLog sysLog = sysLogService.querySysLogById(logId);
		return MessageSupport.successDataMsg(sysLog, "查询成功");
	}
	
	@RequestMapping("query")
	public Object querySysLog(XqsightPage xqsightPage) {
		Page<?> page = XqsightPageHelper.startPageWithPageIndex(xqsightPage.getiDisplayStart(), xqsightPage.getiDisplayLength());
		List<SysLog> sysLogs = sysLogService.querySysLog();
		xqsightPage.setTotalCount(page.getTotal());
        return MessageSupport.successDataTableMsg(xqsightPage, sysLogs);
	}
}