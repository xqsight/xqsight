/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 
package com.xqsight.cms.service.impl;

import java.util.List;

import com.xqsight.cms.model.vo.CmsArticleReportVo;
import com.xqsight.cms.mapper.CmsArticleReportMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.xqsight.cms.model.CmsArticleReport;
import com.xqsight.cms.service.CmsArticleReportService;


/**
 * <p>接口实现类service</p>
 * <p>Table: cms_article_report - </p>
 * @since 2016-09-08 08:42:11
 */
 @Service
public class CmsArticleReportServiceImpl implements CmsArticleReportService {

	private static Logger logger = LogManager.getLogger(CmsArticleReportServiceImpl.class); 
	
	@Autowired
	private CmsArticleReportMapper cmsArticleReportMapper;

	@Override
	public void saveCmsArticleReport(CmsArticleReport cmsArticleReport){
		logger.debug("出入参数:cmsArticleReport＝{}", JSON.toJSONString(cmsArticleReport));
		cmsArticleReportMapper.saveCmsArticleReport(cmsArticleReport);
	}
	
	@Override
	public void updateCmsArticleReport(CmsArticleReport cmsArticleReport) {
		logger.debug("出入参数:cmsArticleReport＝{}", JSON.toJSONString(cmsArticleReport));
		cmsArticleReportMapper.updateCmsArticleReport(cmsArticleReport);
	}
	
	@Override
	public void deleteCmsArticleReport(Long reportId) {
		logger.debug("出入参数:reportId＝{}", reportId);
		cmsArticleReportMapper.deleteCmsArticleReport(reportId);
	}
	
	@Override
	public CmsArticleReport queryCmsArticleReportById(Long reportId ){
		return cmsArticleReportMapper.queryCmsArticleReportById(reportId);
	}
	
	@Override
	public List<CmsArticleReportVo> queryCmsArticleReport(String reportContent) {
		return cmsArticleReportMapper.queryCmsArticleReport(reportContent);
	}
}