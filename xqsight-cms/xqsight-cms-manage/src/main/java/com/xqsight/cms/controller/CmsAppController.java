/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.cms.controller;

import com.github.pagehelper.Page;
import com.xqsight.cms.model.CmsApp;
import com.xqsight.cms.service.CmsAppService;
import com.xqsight.common.model.XqsightPage;
import com.xqsight.common.support.MessageSupport;
import com.xqsight.common.support.XqsightPageHelper;
import com.xqsight.sso.utils.SSOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * <p>controller</p>
 * <p>Table: CMS_APP - 系统应用表</p>
 * @since 2016-05-07 08:02:36
 */
@RestController
@RequestMapping("/cms/app/")
public class CmsAppController{

	@Autowired
	private CmsAppService cmsAppService;

	@RequestMapping("save")
	public Object saveCmsApp(CmsApp cmsApp) {
		cmsApp.setCreateUserId(SSOUtils.getCurrentUserId().toString());
		Long appId = cmsAppService.queryExistByCode(cmsApp.getAppCode());
		if(appId != null && appId > 0){
			return MessageSupport.failureMsg("应用编号已经存在");
		}
			
		cmsAppService.saveCmsApp(cmsApp);
		return MessageSupport.successMsg("保存成功");
	}
	
	@RequestMapping("update")
	public Object updateCmsApp(CmsApp cmsApp) {
		cmsApp.setUpdateUserId(SSOUtils.getCurrentUserId().toString());
		Long appId = cmsAppService.queryExistByCode(cmsApp.getAppCode());
		if(appId != null && appId != cmsApp.getAppId()){
			return MessageSupport.failureMsg("应用编号已经存在");
		}
		cmsAppService.updateCmsApp(cmsApp);
		return MessageSupport.successMsg("修改成功");
	}
	
	@RequestMapping("delete")
	public Object deleteCmsApp(Long appId) {
		cmsAppService.deleteCmsApp(appId);
		return MessageSupport.successMsg("删除成功");
	}
	
	@RequestMapping("query")
	public Object queryCmsApp(XqsightPage xqsightPage) {
		// 分页处理
		Page<?> page = XqsightPageHelper.startPageWithPageIndex(xqsightPage.getiDisplayStart(), xqsightPage.getiDisplayLength());
		List<CmsApp> cmsApps = cmsAppService.queryCmsApp();
		xqsightPage.setTotalCount(page.getTotal());
		return MessageSupport.successDataTableMsg(xqsightPage, cmsApps);
	}
	
	@RequestMapping("queryall")
	public Object queryCmsAppAll() {
		List<CmsApp> cmsApps = cmsAppService.queryCmsApp();
		return MessageSupport.successDataMsg(cmsApps, "查询成功");
	}

	@RequestMapping("querybyid")
	public Object queryCmsApp(Long appId) {
		CmsApp cmsApp = cmsAppService.queryCmsAppById(appId);
		return MessageSupport.successDataMsg(cmsApp, "查询成功");
	}
}