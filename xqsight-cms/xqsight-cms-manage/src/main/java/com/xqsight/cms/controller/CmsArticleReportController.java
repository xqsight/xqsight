/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 
package com.xqsight.cms.controller;

import com.github.pagehelper.Page;
import com.xqsight.common.model.XqsightPage;
import com.xqsight.common.support.MessageSupport;
import com.xqsight.common.support.XqsightPageHelper;
import com.xqsight.commons.web.WebUtils;
import com.xqsight.sso.utils.SSOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xqsight.cms.model.CmsArticleReport;
import com.xqsight.cms.service.CmsArticleReportService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p> controller</p>
 * <p>Table: cms_article_report - </p>
 * @since 2016-09-08 08:42:11
 */
@RestController
@RequestMapping("/cms/article/report/")
public class CmsArticleReportController{

	@Autowired
	private CmsArticleReportService cmsArticleReportService;

	@RequestMapping("save")
	public Object saveCmsArticleReport(HttpServletRequest request, CmsArticleReport cmsArticleReport) {
		if(!WebUtils.isMobile(request))
			cmsArticleReport.setCreateOprId(SSOUtils.getCurrentUserId().toString());
		cmsArticleReportService.saveCmsArticleReport(cmsArticleReport);
		return MessageSupport.successMsg("保存成功");
	}
	
	@RequestMapping("update")
	public Object updateCmsArticleReport(CmsArticleReport cmsArticleReport) {
		cmsArticleReport.setUpdOprId(SSOUtils.getCurrentUserId().toString());
		cmsArticleReportService.updateCmsArticleReport(cmsArticleReport);
		return MessageSupport.successMsg("修改成功");
	}
	
	@RequestMapping("delete")
	public Object deleteCmsArticleReport(Long reportId) {
		cmsArticleReportService.deleteCmsArticleReport(reportId);
		return MessageSupport.successMsg("删除成功");
	}
	
	@RequestMapping("querybyid")
	public Object queryCmsArticleReport(Long reportId) {
		CmsArticleReport cmsArticleReport = cmsArticleReportService.queryCmsArticleReportById(reportId);
		return MessageSupport.successDataMsg(cmsArticleReport, "查询成功");
	}
	
	@RequestMapping("query")
	public Object queryCmsArticleReport(XqsightPage xqsightPage) {
		Page<?> page = XqsightPageHelper.startPageWithPageIndex(xqsightPage.getiDisplayStart(), xqsightPage.getiDisplayLength());
		List<CmsArticleReport> cmsArticleReports = cmsArticleReportService.queryCmsArticleReport();
		 xqsightPage.setTotalCount(page.getTotal());
        return MessageSupport.successDataTableMsg(xqsightPage, cmsArticleReports);
	}
}