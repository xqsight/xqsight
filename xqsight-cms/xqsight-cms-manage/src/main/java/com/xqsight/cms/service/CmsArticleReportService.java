/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 
package com.xqsight.cms.service;

import java.util.List;

import com.xqsight.cms.model.CmsArticleReport;
import com.xqsight.cms.model.vo.CmsArticleReportVo;

/**
 * <p>接口类service</p>
 * <p>Table: cms_article_report - </p>
 * @since 2016-09-08 08:42:11
 */
public interface CmsArticleReportService {
	
	/** 保存 **/
	void saveCmsArticleReport(CmsArticleReport cmsArticleReport);
	
	/** 修改 **/
	void updateCmsArticleReport(CmsArticleReport cmsArticleReport);
	
	/** 删除 **/
	void deleteCmsArticleReport(Long reportId);
	
	/** 根据Id查询 **/
	CmsArticleReport queryCmsArticleReportById(Long reportId);
	
	/** 查询列表 **/
	List<CmsArticleReportVo> queryCmsArticleReport(String reportContent);
}