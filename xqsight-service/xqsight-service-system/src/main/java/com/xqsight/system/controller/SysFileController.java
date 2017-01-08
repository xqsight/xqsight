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

import com.xqsight.system.model.SysFile;
import com.xqsight.system.service.SysFileService;

/**
 * <p>文件表 controller</p>
 * <p>Table: sys_file - 文件表</p>
 * @since 2017-01-07 11:57:19
 * @author wangganggang
 */
@RestController
@RequestMapping("/sys/file/")
public class SysFileController{

	@Autowired
	private SysFileService sysFileService;

	@RequestMapping("save")
	@RequiresPermissions("sys:file:save")
	public Object save(SysFile sysFile) {
		sysFileService.save(sysFile,true);
		return MessageSupport.successMsg("保存成功");
	}
	
	@RequestMapping("update")
	@RequiresPermissions("sys:file:update")
	public Object update(SysFile sysFile) {
		sysFileService.update(sysFile, true);
		return MessageSupport.successMsg("修改成功");
	}
	
	@RequestMapping("delete")
	@RequiresPermissions("sys:file:delete")
	public Object delete(Long fileId) {
		sysFileService.delete(fileId);
		return MessageSupport.successMsg("删除成功");
	}

	@RequestMapping("logicDel")
	@RequiresPermissions("sys:file:delete")
	public Object logicDel(Long fileId) {
		sysFileService.logicDel(fileId);
		return MessageSupport.successMsg("删除成功");
	}

	@RequestMapping("query")
	@RequiresPermissions("sys:file:query")
	public Object query(XqsightPage xqsightPage) {
		Page page = XqsightPageHelper.startPageWithPageIndex(xqsightPage.getiDisplayStart(), xqsightPage.getiDisplayLength());
		List<SysFile> sysFiles = sysFileService.search(null);
		xqsightPage.setTotalCount(page.getTotal());
		return MessageSupport.successDataTableMsg(xqsightPage, sysFiles);
    }

	@RequestMapping("querybyid")
	@RequiresPermissions("sys:file:query")
	public Object queryById(Long fileId) {
		SysFile sysFile = sysFileService.get(fileId);
		return MessageSupport.successDataMsg(sysFile, "查询成功");
	}

	@RequestMapping("queryall")
	@RequiresPermissions("sys:file:query")
	public Object queryall() {
		List<SysFile> sysFiles = sysFileService.search(null);
		return MessageSupport.successDataMsg(sysFiles, "查询成功");
    }

}