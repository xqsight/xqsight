/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.controller;

import com.github.pagehelper.Page;
import com.xqsight.common.model.XqsightPage;
import com.xqsight.common.core.support.XqsightPageHelper;
import com.xqsight.common.support.MessageSupport;
import com.xqsight.sso.shiro.annotation.CurrentUser;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.xqsight.system.model.SysUser;
import com.xqsight.system.service.SysUserService;

/**
 * <p>用户信息表 controller</p>
 * <p>Table: sys_user - 用户信息表</p>
 * @since 2017-01-07 11:58:17
 * @author wangganggang
 */
@RestController
@RequestMapping("/sys/user/")
public class SysUserController{

	@Autowired
	private SysUserService sysUserService;

	@RequestMapping("save")
	@RequiresPermissions("sys:user:save")
	public Object save(SysUser sysUser) {
		sysUserService.save(sysUser,true);
		return MessageSupport.successMsg("保存成功");
	}
	
	@RequestMapping("update")
	@RequiresPermissions("sys:user:update")
	public Object update(SysUser sysUser) {
		sysUserService.update(sysUser, true);
		return MessageSupport.successMsg("修改成功");
	}
	
	@RequestMapping("delete")
	@RequiresPermissions("sys:user:delete")
	public Object delete(Long id) {
		sysUserService.delete(id);
		return MessageSupport.successMsg("删除成功");
	}

	@RequestMapping("logicDel")
	@RequiresPermissions("sys:user:delete")
	public Object logicDel(Long id) {
		sysUserService.logicDel(id);
		return MessageSupport.successMsg("删除成功");
	}

	@RequestMapping("query")
	@RequiresPermissions("sys:user:query")
	public Object query(XqsightPage xqsightPage) {
		Page page = XqsightPageHelper.startPageWithPageIndex(xqsightPage.getiDisplayStart(), xqsightPage.getiDisplayLength());
		List<SysUser> sysUsers = sysUserService.search(null);
		xqsightPage.setTotalCount(page.getTotal());
		return MessageSupport.successDataTableMsg(xqsightPage, sysUsers);
    }

	@RequestMapping("querybyid")
	@RequiresPermissions("sys:user:query")
	public Object queryById(Long id) {
		SysUser sysUser = sysUserService.get(id);
		return MessageSupport.successDataMsg(sysUser, "查询成功");
	}


	@RequestMapping("queryall")
	@RequiresPermissions("sys:user:query")
	public Object queryall() {
		List<SysUser> sysUsers = sysUserService.search(null);
		return MessageSupport.successDataMsg(sysUsers, "查询成功");
    }

	@RequestMapping("queryuserinfo")
	public Object queryUserInfo(@CurrentUser  Long id) {
		SysUser sysUser = sysUserService.get(id);
		return MessageSupport.successDataMsg(sysUser, "查询成功");
	}

}