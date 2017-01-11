/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.controller;

import com.github.pagehelper.Page;
import com.xqsight.common.model.XqsightPage;
import com.xqsight.common.core.support.XqsightPageHelper;
import com.xqsight.common.support.MessageSupport;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.xqsight.system.model.SysQuickKey;
import com.xqsight.system.service.SysQuickKeyService;

/**
 * <p>快捷键表 controller</p>
 * <p>Table: sys_quick_key - 快捷键表</p>
 * @since 2017-01-07 11:57:57
 * @author wangganggang
 */
@RestController
@RequestMapping("/sys/quick/key/")
public class SysQuickKeyController{

	@Autowired
	private SysQuickKeyService sysQuickKeyService;

	@RequestMapping("save")
	@RequiresPermissions("sys:quick:key:save")
	public Object save(SysQuickKey sysQuickKey) {
		sysQuickKeyService.save(sysQuickKey,true);
		return MessageSupport.successMsg("保存成功");
	}
	
	@RequestMapping("update")
	@RequiresPermissions("sys:quick:key:update")
	public Object update(SysQuickKey sysQuickKey) {
		sysQuickKeyService.update(sysQuickKey, true);
		return MessageSupport.successMsg("修改成功");
	}
	
	@RequestMapping("delete")
	@RequiresPermissions("sys:quick:key:delete")
	public Object delete(Long id ) {
		sysQuickKeyService.delete(id );
		return MessageSupport.successMsg("删除成功");
	}

	@RequestMapping("logicDel")
	@RequiresPermissions("sys:quick:key:delete")
	public Object logicDel(Long id) {
		sysQuickKeyService.logicDel(id);
		return MessageSupport.successMsg("删除成功");
	}

	@RequestMapping("query")
	@RequiresPermissions("sys:quick:key:query")
	public Object query(XqsightPage xqsightPage) {
		Page page = XqsightPageHelper.startPageWithPageIndex(xqsightPage.getiDisplayStart(), xqsightPage.getiDisplayLength());
		List<SysQuickKey> sysQuickKeys = sysQuickKeyService.search(null);
		xqsightPage.setTotalCount(page.getTotal());
		return MessageSupport.successDataTableMsg(xqsightPage, sysQuickKeys);
    }

	@RequestMapping("querybyid")
	@RequiresPermissions("sys:quick:key:query")
	public Object queryById(Long id ) {
		SysQuickKey sysQuickKey = sysQuickKeyService.get(id );
		return MessageSupport.successDataMsg(sysQuickKey, "查询成功");
	}

	@RequestMapping("queryall")
	@RequiresPermissions("sys:quick:key:query")
	public Object queryall() {
		List<SysQuickKey> sysQuickKeys = sysQuickKeyService.search(null);
		return MessageSupport.successDataMsg(sysQuickKeys, "查询成功");
    }

}