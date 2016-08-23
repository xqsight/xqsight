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
import com.xqsight.cms.model.CmsComment;
import com.xqsight.cms.mysqlmapper.CmsCommentMapper;
import com.xqsight.cms.service.CmsCommentService;


/**
 * <p>接口实现类service</p>
 * <p>Table: CMS_COMMENT - 评论表</p>
 * @since 2016-05-07 08:02:56
 */
 @Service
public class CmsCommentServiceImpl implements CmsCommentService {

	private static Logger logger = LogManager.getLogger(CmsCommentServiceImpl.class); 
	
	@Autowired
	private CmsCommentMapper cmsCommentMapper;

	@Override
	public void saveCmsComment(CmsComment cmsComment){
		logger.debug("出入参数:cmsComment={}", JSON.toJSONString(cmsComment));
		cmsCommentMapper.saveCmsComment(cmsComment);
	}
	
	@Override
	public void updateCmsComment(CmsComment cmsComment) {
		logger.debug("出入参数:cmsComment={}", JSON.toJSONString(cmsComment));
		cmsCommentMapper.updateCmsComment(cmsComment);
	}
	
	@Override
	public void deleteCmsComment(Long commentId) {
		logger.debug("出入参数:commentId={}", commentId);
		cmsCommentMapper.deleteCmsComment(commentId);
	}
	
	@Override
	public List<CmsComment> queryCmsComment() {
		return cmsCommentMapper.queryCmsComment();
	}
	
	@Override
	public CmsComment queryCmsCommentByCommentId(Long commentId ){
		logger.debug("出入参数:commentId={}", commentId);
		return cmsCommentMapper.queryCmsCommentByCommentId(commentId);
	}

	@Override
	public void deleteCmsCommentByAssocicationId(Long associcationId) {
		logger.debug("出入参数:associcationId={}", associcationId);
		cmsCommentMapper.deleteCmsCommentByAssocicationId(associcationId);
	}

	@Override
	public void updateCmsCommentHitByCommentId(int commentHit, Long commentId) {
		logger.debug("出入参数:commentId={},commentHit={}", commentId,commentHit);
		cmsCommentMapper.updateCmsCommentHitByCommentId(commentHit,commentId);
	}

	@Override
	public void updateCmsCommentActiveByCommentId(int active, Long commentId) {
		logger.debug("出入参数:commentId={},active={}", commentId,active);
		cmsCommentMapper.updateCmsCommentActiveByCommentId(active,commentId);
	}

	@Override
	public List<CmsComment> queryCmsCommentByAssocicationId(Long associcationId) {
		logger.debug("出入参数:associcationId={}", associcationId);
		return cmsCommentMapper.queryCmsCommentByAssocicationId(associcationId);
	}

	@Override
	public List<Map<String,Object>> queryCmsCommentWithUserByAssocicationId(Long associcationId) {
		logger.debug("出入参数:associcationId={}", associcationId);
		return cmsCommentMapper.queryCmsCommentWithUserByAssocicationId(associcationId);
	}

	@Override
	public void updateCmsCommentHitByAssocicationId(int commentHit, Long associcationId) {
		logger.debug("出入参数:commentHit={},associcationId={}", commentHit,associcationId);
		cmsCommentMapper.updateCmsCommentHitByAssocicationId(commentHit,associcationId);
	}
}