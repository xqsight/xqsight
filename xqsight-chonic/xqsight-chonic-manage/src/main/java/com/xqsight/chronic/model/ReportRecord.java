/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.chronic.model;

import com.xqsight.common.model.BaseModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>报告记录实体类</p>
 * <p>Table: REPORT - --> Product</p>
 * <p>报告记录表</p>
 * @since 2016-05-13 03:29:34
 */
public class ReportRecord extends BaseModel {

	/** 主键 */
    private Long reportId;

    /** TEST_TIME - 测试时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date testTime =new Date();
	/** REPORT_NAME - 名称 */
    private String reportName;
	/** REPORT_DESCRIPTION - 描述 */
    private String reportDescription;
	/** REPORT_URL - URL */
    private String reportURL;

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getReportDescription() {
        return reportDescription;
    }

    public void setReportDescription(String reportDescription) {
        this.reportDescription = reportDescription;
    }

    public String getReportURL() {
        return reportURL;
    }

    public void setReportURL(String reportURL) {
        this.reportURL = reportURL;
    }

    public Date getTestTime() {
        return testTime;
    }

    public void setTestTime(Date testTime) {
        this.testTime = testTime;
    }
}