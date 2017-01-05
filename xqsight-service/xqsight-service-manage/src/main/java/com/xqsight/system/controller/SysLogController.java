/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.controller;

import com.github.pagehelper.Page;
import com.xqsight.common.model.XqsightPage;
import com.xqsight.common.support.XqsightPageHelper;
import com.xqsight.commons.support.MessageSupport;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.xqsight.system.model.SysLog;
import com.xqsight.system.service.SysLogService;

/**
 * <p>系统日志 controller</p>
 * <p>Table: sys_log - 系统日志</p>
 * @since 2017-01-05 06:10:44
 * @author wangganggang
 */
@RestController
@RequestMapping("/sys/log/")
public class SysLogController{

	@Autowired
	private SysLogService sysLogService;

	@RequestMapping("save")
	@RequiresPermissions("sys:log:save")
	public Object save(SysLog sysLog) {
		sysLogService.save(sysLog);
		return MessageSupport.successMsg("保存成功");
	}
	
	@RequestMapping("update")
	@RequiresPermissions("sys:log:update")
	public Object update(SysLog sysLog) {
		sysLogService.update(sysLog, true);
		return MessageSupport.successMsg("修改成功");
	}
	
	@RequestMapping("delete")
	@RequiresPermissions("sys:log:delete")
	public Object delete(Long logId) {
		sysLogService.delete(logId);
		return MessageSupport.successMsg("删除成功");
	}

	@RequestMapping("logicDel")
	@RequiresPermissions("sys:log:delete")
	public Object logicDel(Long logId) {
		sysLogService.logicDel(logId);
		return MessageSupport.successMsg("删除成功");
	}

	@RequestMapping("query")
	@RequiresPermissions("sys:log:query")
	public Object query(XqsightPage xqsightPage) {
		Page page = XqsightPageHelper.startPageWithPageIndex(xqsightPage.getiDisplayStart(), xqsightPage.getiDisplayLength());
		List<SysLog> sysLogs = sysLogService.search(null);
		xqsightPage.setTotalCount(page.getTotal());
		return MessageSupport.successDataTableMsg(xqsightPage, sysLogs);
    }

	@RequestMapping("querybyid")
	@RequiresPermissions("sys:log:query")
	public Object queryById(Long logId) {
		SysLog sysLog = sysLogService.get(logId);
		return MessageSupport.successDataMsg(sysLog, "查询成功");
	}

	@RequestMapping("queryall")
	@RequiresPermissions("sys:log:query")
	public Object queryall() {
		List<SysLog> sysLogs = sysLogService.search(null);
		return MessageSupport.successDataMsg(sysLogs, "查询成功");
    }

}