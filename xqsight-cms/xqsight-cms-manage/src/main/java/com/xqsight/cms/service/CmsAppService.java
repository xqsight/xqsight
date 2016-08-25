/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.cms.service;

import java.util.List;

import com.xqsight.cms.model.CmsApp;

/**
 * <p>接口类service</p>
 * <p>Table: CMS_APP - 系统应用表</p>
 * @since 2016-05-07 08:00:44
 */
public interface CmsAppService {

	/**
	 * 
	  * @Title: saveCmsApp
	  * @Description: TODO
	  * @param @param cmsApp    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	void saveCmsApp(CmsApp cmsApp);
	
	/**
	 * 
	  * @Title: updateCmsApp
	  * @Description: TODO
	  * @param @param cmsApp    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	void updateCmsApp(CmsApp cmsApp);
	
	/**
	  *
	  * @Title: deleteCmsApp
	  * @Description: TODO
	  * @param @param appId    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	void deleteCmsApp(Long appId);
	
	/**
	  *
	  * @Title: queryCmsApp
	  * @Description: TODO
	  * @param @return    设定文件
	  * @return List<CmsApp>    返回类型
	  * @throws
	 */
	List<CmsApp> queryCmsApp();

	/**
	 * 根据appCode模糊查询
	 * @param appCode
	 * @return
     */
	List<CmsApp> queryCmsAppByLikeAppCode(String appCode);
	
	/**
	  *
	  * @Title: queryCmsAppById
	  * @Description: TODO
	  * @param @param appId
	  * @param @return    设定文件
	  * @return CmsApp    返回类型
	  * @throws
	 */
	CmsApp queryCmsAppById(Long appId);
	
	/**
	  * 查询code是否存在，存在返回主键
	  * 
	  * @Title: queryExistByCode
	  * @Description: TODO
	  * @param @param appCode
	  * @param @return    设定文件
	  * @return Long    返回类型
	  * @throws
	 */
	Long queryExistByCode(String appCode);
}