/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.controller;

import com.github.pagehelper.Page;
import com.xqsight.common.model.XqsightPage;
import com.xqsight.common.orm.MatchType;
import com.xqsight.common.orm.PropertyFilter;
import com.xqsight.common.orm.PropertyType;
import com.xqsight.common.orm.builder.PropertyFilterBuilder;
import com.xqsight.common.support.XqsightPageHelper;
import com.xqsight.commons.support.MessageSupport;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.xqsight.system.model.SysRole;
import com.xqsight.system.service.SysRoleService;

/**
 * <p>角色信息表 controller</p>
 * <p>Table: sys_role - 角色信息表</p>
 * @since 2017-01-05 06:11:10
 * @author wangganggang
 */
@RestController
@RequestMapping("/sys/role/")
public class SysRoleController{

	@Autowired
	private SysRoleService sysRoleService;

	@RequestMapping("save")
	@RequiresPermissions("sys:role:save")
	public Object save(SysRole sysRole) {
		sysRoleService.save(sysRole);
		return MessageSupport.successMsg("保存成功");
	}
	
	@RequestMapping("update")
	@RequiresPermissions("sys:role:update")
	public Object update(SysRole sysRole) {
		sysRoleService.update(sysRole, true);
		return MessageSupport.successMsg("修改成功");
	}
	
	@RequestMapping("delete")
	@RequiresPermissions("sys:role:delete")
	public Object delete(Long roleId) {
		sysRoleService.delete(roleId);
		return MessageSupport.successMsg("删除成功");
	}

	@RequestMapping("logicDel")
	@RequiresPermissions("sys:role:delete")
	public Object logicDel(Long roleId) {
		sysRoleService.logicDel(roleId);
		return MessageSupport.successMsg("删除成功");
	}

	@RequestMapping("query")
	@RequiresPermissions("sys:role:query")
	public Object query(XqsightPage xqsightPage,String roleName) {
		Page page = XqsightPageHelper.startPageWithPageIndex(xqsightPage.getiDisplayStart(), xqsightPage.getiDisplayLength());
		List<PropertyFilter> propertyFilters = PropertyFilterBuilder.create().matchTye(MatchType.LIKE).propertyType(PropertyType.S)
				.add("role_name",roleName).end();
		List<SysRole> sysRoles = sysRoleService.search(propertyFilters);
		xqsightPage.setTotalCount(page.getTotal());
		return MessageSupport.successDataTableMsg(xqsightPage, sysRoles);
    }

	@RequestMapping("querybyid")
	@RequiresPermissions("sys:role:query")
	public Object queryById(Long roleId) {
		SysRole sysRole = sysRoleService.get(roleId);
		return MessageSupport.successDataMsg(sysRole, "查询成功");
	}

	@RequestMapping("queryall")
	@RequiresPermissions("sys:role:query")
	public Object queryall() {
		List<SysRole> sysRoles = sysRoleService.search(null);
		return MessageSupport.successDataMsg(sysRoles, "查询成功");
    }

}