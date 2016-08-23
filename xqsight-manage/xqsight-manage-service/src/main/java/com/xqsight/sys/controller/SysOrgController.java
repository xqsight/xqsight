/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.sys.controller;

import com.github.pagehelper.Page;
import com.xqsight.common.model.XqsightPage;
import com.xqsight.common.support.MessageSupport;
import com.xqsight.common.support.XqsightPageHelper;
import com.xqsight.sso.utils.SSOUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xqsight.sys.model.SysOrg;
import com.xqsight.sys.service.SysOrgService;

import java.util.List;

/**
 * <p>组织机构表 controller</p>
 * <p>Table: sys_org - 组织机构表</p>
 * @since 2016-06-20 07:55:50
 */
@RestController
@RequestMapping("/sys/org/")
public class SysOrgController{

	@Autowired
	private SysOrgService sysOrgService;

	@RequestMapping("save")
	public Object saveSysOrg(SysOrg sysOrg) {
		sysOrg.setCreateOprId(SSOUtils.getCurrentUserId().toString());
		sysOrgService.saveSysOrg(sysOrg);
		return MessageSupport.successMsg("保存成功");
	}
	
	@RequestMapping("update")
	public Object updateSysOrg(SysOrg sysOrg) {
		sysOrg.setUpdOprId(SSOUtils.getCurrentUserId().toString());
		sysOrgService.updateSysOrg(sysOrg);
		return MessageSupport.successMsg("修改成功");
	}
	
	@RequestMapping("delete")
	public Object deleteSysOrg(Long orgId) {
		sysOrgService.deleteSysOrg(orgId);
		return MessageSupport.successMsg("删除成功");
	}
	
	@RequestMapping("querybyid")
	public Object querySysOrgById(Long orgId) {
		SysOrg sysOrg = sysOrgService.querySysOrgById(orgId);
		return MessageSupport.successDataMsg(sysOrg, "查询成功");
	}

	@RequestMapping("query")
	public Object querySysOrg(String orgName,String orgCode,String customCode,Long parentId) {
		List<SysOrg> sysOrgs = sysOrgService.querySysOrgByOrgNameAndOrgCodeAndCustomCodeAndParentId(orgName,orgCode,customCode,parentId);
		return MessageSupport.successDataMsg(sysOrgs, "查询成功");
	}

	@RequestMapping("querytree")
	public Object querySysOrgToTree() {
		SysOrg sysOrg = sysOrgService.querySysOrgToTree();
		return MessageSupport.successDataMsg(sysOrg, "查询成功");
	}



}