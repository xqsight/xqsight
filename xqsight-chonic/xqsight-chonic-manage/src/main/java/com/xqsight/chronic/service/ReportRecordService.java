/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.chronic.service;

import com.xqsight.chronic.model.ReportRecord;

import java.util.List;
import java.util.Map;

/**
 * <p>产品表接口类service</p>
 * <p>Table: PRODUCT - 产品表</p>
 * @since 2016-05-13 03:29:34
 */
public interface ReportRecordService {

	void saveReportRecord(ReportRecord reportRecord);
	

	void deleteReportRecord(Long reportId);

	List<ReportRecord> queryReportRecord();

	List<ReportRecord> queryReportRecordByUser(String createOprId);

	ReportRecord queryReportRecordById(Long reportId);


}