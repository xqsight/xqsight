/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 
package com.xqsight.cms.model;

import com.xqsight.common.model.Model;

import java.io.Serializable;

/**
 * <p>实体类</p>
 * <p>Table: cms_article_report - --> CmsArticleReport</p>
 * <p></p>
 * @since 2016-09-08 08:42:11
 */
public class CmsArticleReport extends Model{

	/** 主键 */
    private Long reportId;

	/** ASSOCICATION_ID - 收藏的ID */
    private Long associcationId;
	/** REPORT_TYPE - 举报类型 */
    private Integer reportType;
	/** DEAL_STATUS - 处理状态 0:已处理 -1:未处理 */
    private Integer dealStatus;
    /** REPORT_CONTENT - 举报内容 */
    private String reportContent;

    public Long getReportId(){
        return this.reportId;
    }
    public void setReportId(Long reportId){
        this.reportId = reportId;
    }
	public Long getAssocicationId(){
        return this.associcationId;
    }
    public void setAssocicationId(Long associcationId){
        this.associcationId = associcationId;
    }
	public Integer getReportType(){
        return this.reportType;
    }
    public void setReportType(Integer reportType){
        this.reportType = reportType;
    }
	public Integer getDealStatus(){
        return this.dealStatus;
    }
    public void setDealStatus(Integer dealStatus){
        this.dealStatus = dealStatus;
    }

    public String getReportContent() {
        return reportContent;
    }

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    @Override
    public Serializable getPK() {
        return this.reportId;
    }
}