/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.chronic.controller;

import com.github.pagehelper.Page;
import com.xqsight.chronic.model.ReportRecord;
import com.xqsight.chronic.service.ReportRecordService;
import com.xqsight.common.model.XqsightPage;
import com.xqsight.common.support.MessageSupport;
import com.xqsight.common.support.XqsightPageHelper;
import com.xqsight.commons.web.WebUtils;
import com.xqsight.sso.utils.SSOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


/**
 * <p>检测记录表 controller</p>
 * <p>Table: REPORT_RECORD - 检测记录表</p>
 * @since 2016-05-13 03:29:34
 */
@RestController
@RequestMapping("/report/record/")
public class ReportRecordController{

	@Autowired
	private ReportRecordService reportRecordService;

	@RequestMapping("save")
	public Object saveProduct(HttpServletRequest request, ReportRecord	reportRecord) {
		if(!WebUtils.isMobile(request))
			reportRecord.setCreateOprId(SSOUtils.getCurrentUserId().toString());

		reportRecord.setTestTime(new Date());
		reportRecordService.saveReportRecord(reportRecord);
		return MessageSupport.successMsg("保存成功");
	}
	
	@RequestMapping("delete")
	public Object deleteReportRecord(Long reportId) {
		reportRecordService.deleteReportRecord(reportId);
		return MessageSupport.successMsg("删除成功");
	}
	
	@RequestMapping("query")
	public Object queryReportRecord(XqsightPage xqsightPage) {
		Page<?> page = XqsightPageHelper.startPageWithPageIndex(xqsightPage.getiDisplayStart(), xqsightPage.getiDisplayLength());
		List<ReportRecord> reportRecords = reportRecordService.queryReportRecord();
		xqsightPage.setTotalCount(page.getTotal());
		return MessageSupport.successDataTableMsg(xqsightPage, reportRecords);
	}

	@RequestMapping("querybyid")
	public Object queryReportRecordById(Long reportId) {
		ReportRecord reportRecord = reportRecordService.queryReportRecordById(reportId);
		return MessageSupport.successDataMsg(reportRecord,"查询成功");
	}

	@RequestMapping("querybyuser")
	public Object queryReportRecordByUser(String id) {
		List<ReportRecord> reportRecords = reportRecordService.queryReportRecordByUser(id);
		return MessageSupport.successDataMsg(reportRecords,"查询成功");
	}
}