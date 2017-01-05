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

import com.xqsight.system.model.SysOrg;
import com.xqsight.system.service.SysOrgService;

/**
 * <p>组织机构表 controller</p>
 * <p>Table: sys_org - 组织机构表</p>
 * @since 2017-01-05 06:11:00
 * @author wangganggang
 */
@RestController
@RequestMapping("/sys/org/")
public class SysOrgController{

	@Autowired
	private SysOrgService sysOrgService;

	@RequestMapping("save")
	@RequiresPermissions("sys:org:save")
	public Object save(SysOrg sysOrg) {
		sysOrgService.save(sysOrg);
		return MessageSupport.successMsg("保存成功");
	}
	
	@RequestMapping("update")
	@RequiresPermissions("sys:org:update")
	public Object update(SysOrg sysOrg) {
		sysOrgService.update(sysOrg, true);
		return MessageSupport.successMsg("修改成功");
	}
	
	@RequestMapping("delete")
	@RequiresPermissions("sys:org:delete")
	public Object delete(Long orgId) {
		sysOrgService.delete(orgId);
		return MessageSupport.successMsg("删除成功");
	}

	@RequestMapping("logicDel")
	@RequiresPermissions("sys:org:delete")
	public Object logicDel(Long orgId) {
		sysOrgService.logicDel(orgId);
		return MessageSupport.successMsg("删除成功");
	}

	@RequestMapping("query")
	@RequiresPermissions("sys:org:query")
	public Object query(XqsightPage xqsightPage) {
		Page page = XqsightPageHelper.startPageWithPageIndex(xqsightPage.getiDisplayStart(), xqsightPage.getiDisplayLength());
		List<SysOrg> sysOrgs = sysOrgService.search(null);
		xqsightPage.setTotalCount(page.getTotal());
		return MessageSupport.successDataTableMsg(xqsightPage, sysOrgs);
    }

	@RequestMapping("querybyid")
	@RequiresPermissions("sys:org:query")
	public Object queryById(Long orgId) {
		SysOrg sysOrg = sysOrgService.get(orgId);
		return MessageSupport.successDataMsg(sysOrg, "查询成功");
	}

	@RequestMapping("queryall")
	@RequiresPermissions("sys:org:query")
	public Object queryall() {
		List<SysOrg> sysOrgs = sysOrgService.search(null);
		return MessageSupport.successDataMsg(sysOrgs, "查询成功");
    }

}