/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.cms.service;

import java.util.List;

import com.xqsight.cms.model.CmsModel;

/**
 * <p>接口类service</p>
 * <p>Table: CMS_MODEL - 模块信息表</p>
 * @since 2016-05-07 08:00:53
 */
public interface CmsModelService {

	/**
	  *
	  * @Title: saveCmsModel
	  * @Description: TODO
	  * @param @param cmsModel    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	void saveCmsModel(CmsModel cmsModel);
	
	/**
	  *
	  * @Title: updateCmsModel
	  * @Description: TODO
	  * @param @param cmsModel    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	void updateCmsModel(CmsModel cmsModel);
	
	/**
	  *
	  * @Title: deleteCmsModel
	  * @Description: TODO
	  * @param @param modelId    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	void deleteCmsModel(Long modelId);
	
	/**
	 * 根据appId查询对应的模块
	 *
	 * @param appId
	 * @return
     */
	List<CmsModel> queryCmsModelByAppId(Long appId);
	
	/**
	  *
	  * @Title: queryCmsModelById
	  * @Description: TODO
	  * @param @param modelId
	  * @param @return    设定文件
	  * @return CmsModel    返回类型
	  * @throws
	 */
	CmsModel queryCmsModelById(Long modelId);
	
	/**
	  * 查询Code是否存在，存在返回主键
	  * 
	  * @Title: queryExistByCode
	  * @Description: TODO
	  * @param @param modelCode
	  * @param @return    设定文件
	  * @return Long    返回类型
	  * @throws
	 */
	Long queryExistByCode(String modelCode);

	CmsModel queryCmsModelToTree();

	List<CmsModel> queryCmsModelByModelNameAndModelCodeAndParentId(String modelName, String modelCode, Long parentId);
}