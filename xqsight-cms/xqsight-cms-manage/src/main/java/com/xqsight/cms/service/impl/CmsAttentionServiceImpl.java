/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.cms.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.xqsight.cms.model.CmsAttention;
import com.xqsight.cms.mysqlmapper.CmsAttentionMapper;
import com.xqsight.cms.service.CmsAttentionService;


/**
 * <p>接口实现类service</p>
 * <p>Table: CMS_ATTENTION - 用户收藏表</p>
 * @since 2016-05-07 08:02:51
 */
 @Service
public class CmsAttentionServiceImpl implements CmsAttentionService {

	private static Logger logger = LogManager.getLogger(CmsAttentionServiceImpl.class); 
	
	@Autowired
	private CmsAttentionMapper cmsAttentionMapper;

	/**
	 * 
	 * <p>Title: saveCmsAttention</p>
	 * <p>Description: </p>
	 * @param cmsAttention
	 * @see com.xqsight.cms.service.CmsAttentionService#saveCmsAttention(CmsAttention)
	 */
	@Override
	public void saveCmsAttention(CmsAttention cmsAttention){
		logger.debug("出入参数:cmsAttention={}", JSON.toJSONString(cmsAttention));
		cmsAttentionMapper.saveCmsAttention(cmsAttention);
	}
	
	/**
	 * 
	 * <p>Title: updateCmsAttention</p>
	 * <p>Description: </p>
	 * @param cmsAttention
	 * @see com.xqsight.cms.service.CmsAttentionService#updateCmsAttention(CmsAttention)
	 */
	@Override
	public void updateCmsAttention(CmsAttention cmsAttention) {
		logger.debug("出入参数:cmsAttention={}", JSON.toJSONString(cmsAttention));
		cmsAttentionMapper.updateCmsAttention(cmsAttention);
	}

	@Override
	public void deleteCmsAttentionByAssocicationIdAndAttentionType(Long associcationId, int attentionType) {
		logger.debug("出入参数:associcationId={},attentionType={}", associcationId,attentionType);
		cmsAttentionMapper.deleteCmsAttentionByAssocicationIdAndAttentionType(associcationId,attentionType);
	}

	@Override
	public List<Map<String, Object>> queryCmsAttentionToStoreByUser(String createOprId) {
		logger.debug("出入参数:createOprId={}", createOprId);
		return cmsAttentionMapper.queryCmsAttentionToStoreByUser(createOprId);
	}

	@Override
	public List<Map<String, Object>> queryCmsAttentionToAttenionByUser(String createOprId) {
		logger.debug("出入参数:createOprId={}", createOprId);
		return cmsAttentionMapper.queryCmsAttentionToAttenionByUser(createOprId);
	}

	@Override
	public void deleteCmsAttentionByAssocicationIdAndAttentionTypeAndCreateOprId(Long associcationId, int attentionType, String createOprId) {
		logger.debug("出入参数:associcationId={},attentionType={},createOprId={}", associcationId,attentionType,createOprId);
		cmsAttentionMapper.deleteCmsAttentionByAssocicationIdAndAttentionTypeAndCreateOprId(associcationId,attentionType,createOprId);
	}

	@Override
	public List<CmsAttention> queryCmsAttentionByAttentionType(int attentionType) {
		logger.debug("出入参数:attentionType={}", attentionType);
		return cmsAttentionMapper.queryCmsAttentionByAttentionType(attentionType);
	}

	@Override
	public CmsAttention queryCmsAttentionByAttentionId(Long attentionId) {
		logger.debug("出入参数:attentionId={}", attentionId);
		return cmsAttentionMapper.queryCmsAttentionByAttentionId(attentionId);
	}

	@Override
	public CmsAttention queryCmsAttentionByAssocicationIdAndAttentionTypeAndCreateOprId(Long associcationId, int attentionType, String createOprId) {
		logger.debug("出入参数:associcationId={},attentionType={},createOprId={}", associcationId,attentionType,createOprId);
		return cmsAttentionMapper.queryCmsAttentionByAssocicationIdAndAttentionTypeAndCreateOprId(associcationId,attentionType,createOprId);
	}
}
