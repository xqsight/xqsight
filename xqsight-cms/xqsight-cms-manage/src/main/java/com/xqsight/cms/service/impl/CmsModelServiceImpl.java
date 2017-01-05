/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.cms.service.impl;

import java.util.List;

import com.xqsight.commons.support.TreeSupport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.xqsight.cms.model.CmsModel;
import com.xqsight.cms.mapper.CmsModelMapper;
import com.xqsight.cms.service.CmsModelService;


/**
 * <p>接口实现类service</p>
 * <p>Table: CMS_MODEL - 模块信息表</p>
 * @since 2016-05-07 08:03:01
 */
 @Service
public class CmsModelServiceImpl implements CmsModelService {

	private static Logger logger = LogManager.getLogger(CmsModelServiceImpl.class); 
	
	@Autowired
	private CmsModelMapper cmsModelMapper;

	/**
	 * 
	 * <p>Title: saveCmsModel</p>
	 * <p>Description: </p>
	 * @param cmsModel
	 * @see com.xqsight.cms.service.CmsModelService#saveCmsModel(CmsModel)
	 */
	@Override
	public void saveCmsModel(CmsModel cmsModel){
		logger.debug("出入参数:cmsModel={}", JSON.toJSONString(cmsModel));
		cmsModelMapper.saveCmsModel(cmsModel);
	}
	
	/**
	 * 
	 * <p>Title: updateCmsModel</p>
	 * <p>Description: </p>
	 * @param cmsModel
	 * @see com.xqsight.cms.service.CmsModelService#updateCmsModel(CmsModel)
	 */
	@Override
	public void updateCmsModel(CmsModel cmsModel) {
		logger.debug("出入参数:cmsModel={}", JSON.toJSONString(cmsModel));
		cmsModelMapper.updateCmsModel(cmsModel);
	}
	
	/**
	 * 
	 * <p>Title: deleteCmsModel</p>
	 * <p>Description: </p>
	 * @param modelId
	 * @see com.xqsight.cms.service.CmsModelService#deleteCmsModel(Long)
	 */
	@Override
	public void deleteCmsModel(Long modelId) {
		logger.debug("出入参数:modelId={}", modelId);
		cmsModelMapper.deleteCmsModel(modelId);
	}
	
	/**
	 * <p>Title: queryCmsModelByAppId</p>
	 * <p>Description: </p>
	 * @param appId
	 * @return
	 * @see com.xqsight.cms.service.CmsModelService#queryCmsModelByAppId(Long)
	 */
	@Override
	public List<CmsModel> queryCmsModelByAppId(Long appId) {
		logger.debug("出入参数:appId={}", appId);
		return cmsModelMapper.queryCmsModelByAppId(appId);
	}

	/**
	 * 
	 * <p>Title: queryCmsModelById</p>
	 * <p>Description: </p>
	 * @param modelId
	 * @return
	 * @see com.xqsight.cms.service.CmsModelService#queryCmsModelById(Long)
	 */
	@Override
	public CmsModel queryCmsModelById(Long modelId ){
		logger.debug("出入参数:modelId={}", modelId);
		return cmsModelMapper.queryCmsModelById(modelId);
	}

	/**
	 * <p>Title: queryExistByCode</p>
	 * <p>Description: </p>
	 * @param modelCode
	 * @return
	 * @see com.xqsight.cms.service.CmsModelService#queryExistByCode(String)
	 */
	@Override
	public Long queryExistByCode(String modelCode) {
		logger.debug("出入参数:modelCode={}", modelCode);
		return cmsModelMapper.queryExistByCode(modelCode);
	}

	@Override
	public CmsModel queryCmsModelToTree() {
		List<CmsModel> cmsModels = cmsModelMapper.queryCmsModel();
		return new TreeSupport<CmsModel>().generateFullTree(cmsModels);
	}

	@Override
	public List<CmsModel> queryCmsModelByModelNameAndModelCodeAndParentId(String modelName, String modelCode, Long parentId) {
		logger.debug("出入参数:modelName={},modelCode={},parentId={}", modelName,modelCode,parentId);
		return cmsModelMapper.queryCmsModelByModelNameAndModelCodeAndParentId(modelName,modelCode,parentId);
	}
}