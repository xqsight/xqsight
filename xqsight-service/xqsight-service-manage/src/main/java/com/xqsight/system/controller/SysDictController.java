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

import com.xqsight.system.model.SysDict;
import com.xqsight.system.service.SysDictService;

/**
 * <p>系统字典表 controller</p>
 * <p>Table: sys_dict - 系统字典表</p>
 * @since 2017-01-05 06:10:27
 * @author wangganggang
 */
@RestController
@RequestMapping("/sys/dict/")
public class SysDictController{

	@Autowired
	private SysDictService sysDictService;

	@RequestMapping("save")
	@RequiresPermissions("sys:dict:save")
	public Object save(SysDict sysDict) {
		sysDictService.save(sysDict);
		return MessageSupport.successMsg("保存成功");
	}
	
	@RequestMapping("update")
	@RequiresPermissions("sys:dict:update")
	public Object update(SysDict sysDict) {
		sysDictService.update(sysDict, true);
		return MessageSupport.successMsg("修改成功");
	}
	
	@RequestMapping("delete")
	@RequiresPermissions("sys:dict:delete")
	public Object delete(Long dictId) {
		sysDictService.delete(dictId);
		return MessageSupport.successMsg("删除成功");
	}

	@RequestMapping("logicDel")
	@RequiresPermissions("sys:dict:delete")
	public Object logicDel(Long dictId) {
		sysDictService.logicDel(dictId);
		return MessageSupport.successMsg("删除成功");
	}

	@RequestMapping("query")
	@RequiresPermissions("sys:dict:query")
	public Object query(XqsightPage xqsightPage) {
		Page page = XqsightPageHelper.startPageWithPageIndex(xqsightPage.getiDisplayStart(), xqsightPage.getiDisplayLength());
		List<SysDict> sysDicts = sysDictService.search(null);
		xqsightPage.setTotalCount(page.getTotal());
		return MessageSupport.successDataTableMsg(xqsightPage, sysDicts);
    }

	@RequestMapping("querybyid")
	@RequiresPermissions("sys:dict:query")
	public Object queryById(Long dictId) {
		SysDict sysDict = sysDictService.get(dictId);
		return MessageSupport.successDataMsg(sysDict, "查询成功");
	}

	@RequestMapping("queryall")
	@RequiresPermissions("sys:dict:query")
	public Object queryall() {
		List<SysDict> sysDicts = sysDictService.search(null);
		return MessageSupport.successDataMsg(sysDicts, "查询成功");
    }

}