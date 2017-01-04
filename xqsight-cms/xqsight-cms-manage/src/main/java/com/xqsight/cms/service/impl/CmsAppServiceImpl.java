/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.cms.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.xqsight.cms.model.CmsApp;
import com.xqsight.cms.mapper.CmsAppMapper;
import com.xqsight.cms.service.CmsAppService;


/**
 * <p>接口实现类service</p>
 * <p>Table: CMS_APP - 系统应用表</p>
 * @since 2016-05-07 08:02:36
 */
 @Service
public class CmsAppServiceImpl implements CmsAppService {

	private static Logger logger = LogManager.getLogger(CmsAppServiceImpl.class); 
	
	@Autowired
	private CmsAppMapper cmsAppMapper;

	/**
	 * 
	 * <p>Title: saveCmsApp</p>
	 * <p>Description: </p>
	 * @param cmsApp
	 * @see com.xqsight.cms.service.CmsAppService#saveCmsApp(CmsApp)
	 */
	@Override
	public void saveCmsApp(CmsApp cmsApp){
		logger.debug("出入参数:cmsApp={}", JSON.toJSONString(cmsApp));
		cmsAppMapper.saveCmsApp(cmsApp);
	}
	
	/**
	 * 
	 * <p>Title: updateCmsApp</p>
	 * <p>Description: </p>
	 * @param cmsApp
	 * @see com.xqsight.cms.service.CmsAppService#updateCmsApp(CmsApp)
	 */
	@Override
	public void updateCmsApp(CmsApp cmsApp) {
		logger.debug("出入参数:cmsApp={}", JSON.toJSONString(cmsApp));
		cmsAppMapper.updateCmsApp(cmsApp);
	}
	
	/**
	 * 
	 * <p>Title: deleteCmsApp</p>
	 * <p>Description: </p>
	 * @param appId
	 * @see com.xqsight.cms.service.CmsAppService#deleteCmsApp(Long)
	 */
	@Override
	public void deleteCmsApp(Long appId) {
		logger.debug("出入参数:appId={}", appId);
		cmsAppMapper.deleteCmsApp(appId);
	}
	
	/**
	 * 
	 * <p>Title: queryCmsApp</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.xqsight.cms.service.CmsAppService#queryCmsApp()
	 */
	@Override
	public List<CmsApp> queryCmsApp() {
		return cmsAppMapper.queryCmsApp();
	}
	
	/**
	 * 
	 * <p>Title: queryCmsAppById</p>
	 * <p>Description: </p>
	 * @param appId
	 * @return
	 * @see com.xqsight.cms.service.CmsAppService#queryCmsAppById(Long)
	 */
	@Override
	public CmsApp queryCmsAppById(Long appId ){
		logger.debug("出入参数:appId={}", appId);
		return cmsAppMapper.queryCmsAppById(appId);
	}

	/**
	 * <p>Title: queryExistByCode</p>
	 * <p>Description: </p>
	 * @param appCode
	 * @return
	 * @see com.xqsight.cms.service.CmsAppService#queryExistByCode(String)
	 */
	@Override
	public Long queryExistByCode(String appCode) {
		logger.debug("出入参数:appCode={}", appCode);
		return cmsAppMapper.queryExistByCode(appCode);
	}

	@Override
	public List<CmsApp> queryCmsAppByLikeAppCode(String appCode) {
		logger.debug("出入参数:appCode={}", appCode);
		return cmsAppMapper.queryCmsAppByLikeAppCode(appCode);
	}
}