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

import com.xqsight.system.model.SysMenu;
import com.xqsight.system.service.SysMenuService;

/**
 * <p>菜单信息表 controller</p>
 * <p>Table: sys_menu - 菜单信息表</p>
 * @since 2017-01-05 06:10:54
 * @author wangganggang
 */
@RestController
@RequestMapping("/sys/menu/")
public class SysMenuController{

	@Autowired
	private SysMenuService sysMenuService;

	@RequestMapping("save")
	@RequiresPermissions("sys:menu:save")
	public Object save(SysMenu sysMenu) {
		sysMenuService.save(sysMenu);
		return MessageSupport.successMsg("保存成功");
	}
	
	@RequestMapping("update")
	@RequiresPermissions("sys:menu:update")
	public Object update(SysMenu sysMenu) {
		sysMenuService.update(sysMenu, true);
		return MessageSupport.successMsg("修改成功");
	}
	
	@RequestMapping("delete")
	@RequiresPermissions("sys:menu:delete")
	public Object delete(Long menuId) {
		sysMenuService.delete(menuId);
		return MessageSupport.successMsg("删除成功");
	}

	@RequestMapping("logicDel")
	@RequiresPermissions("sys:menu:delete")
	public Object logicDel(Long menuId) {
		sysMenuService.logicDel(menuId);
		return MessageSupport.successMsg("删除成功");
	}

	@RequestMapping("query")
	@RequiresPermissions("sys:menu:query")
	public Object query(XqsightPage xqsightPage) {
		Page page = XqsightPageHelper.startPageWithPageIndex(xqsightPage.getiDisplayStart(), xqsightPage.getiDisplayLength());
		List<SysMenu> sysMenus = sysMenuService.search(null);
		xqsightPage.setTotalCount(page.getTotal());
		return MessageSupport.successDataTableMsg(xqsightPage, sysMenus);
    }

	@RequestMapping("querybyid")
	@RequiresPermissions("sys:menu:query")
	public Object queryById(Long menuId) {
		SysMenu sysMenu = sysMenuService.get(menuId);
		return MessageSupport.successDataMsg(sysMenu, "查询成功");
	}

	@RequestMapping("queryall")
	@RequiresPermissions("sys:menu:query")
	public Object queryall() {
		List<SysMenu> sysMenus = sysMenuService.search(null);
		return MessageSupport.successDataMsg(sysMenus, "查询成功");
    }

}