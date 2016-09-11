/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.cms.service;

import java.util.List;
import java.util.Map;


import com.xqsight.cms.model.CmsArticle;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>接口类service</p>
 * <p>Table: CMS_ARTICLE - 文章表</p>
 * @since 2016-05-07 08:00:35
 */
public interface CmsArticleService {

	/**
	 * 
	  * 保存 文章
	  *
	  * @Title: saveCmsArticle
	  * @Description: TODO
	  * @param @param cmsArticle    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	void saveCmsArticle(CmsArticle cmsArticle, String img);
	
	/**
	 * 
	  * 修改 文章
	  *
	  * @Title: updateCmsArticle
	  * @Description: TODO
	  * @param @param cmsArticle    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	void updateCmsArticle(CmsArticle cmsArticle, String img);
	
	/**
	 * 
	  * 删除 文章
	  *
	  * @Title: deleteCmsArticle
	  * @Description: TODO
	  * @param @param articleId    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	void deleteCmsArticle(Long articleId);
	
	/**
	 * 
	  * 查询 文章
	  *
	  * @Title: queryCmsArticle
	  * @Description: TODO
	  * @param @return    设定文件
	  * @return List<CmsArticle>    返回类型
	  * @throws
	 */
	List<CmsArticle> queryCmsArticle();
	
	/**
	 * 
	  * 查询 文章
	  *
	  * @Title: queryCmsArticleById
	  * @Description: TODO
	  * @param @param articleId
	  * @param @return    设定文件
	  * @return CmsArticle    返回类型
	  * @throws
	 */
	CmsArticle queryCmsArticleById(Long articleId);

	/**
	 * 查询文章 根据模块
	 *
	 * @param modelId
	 * @return
     */
	List<CmsArticle> queryCmsArticleByModelId(Long modelId);

	/**
	 * 根据 多模块查询文章
	 *
	 * @param modelId
	 * @return
     */
	List<CmsArticle> queryCmsArticleByModelIds(String... modelId);

	/**
	 * 查询文章视图 根据 主键
	 * @param articleId
	 * @return
     */
	Map<String, Object> queryCmsArticleViewByArticleId(Long articleId);

	/**
	 * 查询文章视图 根据模块
	 *
	 * @param modelId
	 * @return
     */
	List<Map<String, Object>> queryCmsArticleViewByModelId(int modelId);

	/**
	 * 查询文章视图  根据多模块
	 *
	 * @param modelId
	 * @return
     */
	List<Map<String, Object>> queryCmsArticleViewByModelIds(String... modelId);

	/**
	 * 查询文章视图根据模块和有效
	 *
	 * @param modelId
	 * @param active
     * @return
     */
	List<Map<String, Object>> queryCmsArticleViewByModelIdAndActive(int modelId, int active);

	/**
	 * 查询文章根据模块和创建人
	 *
	 * @param modelId
	 * @param createOprId
     * @return
     */
	List<Map<String, Object>> queryCmsArticleViewByModelIdAndCreateOprId(int modelId, String createOprId);

	/**
	 * 查询文章根据模块和创建人以及有效性
	 *
	 * @param modelId
	 * @param createOprId
	 * @param active
     * @return
     */
	List<Map<String, Object>> queryCmsArticleViewByModelIdAndCreateOprIdAndActive(int modelId, String createOprId, int active);

	/**
	 * 查询文章视图 根据模块 根据指定字段排序 附带第一张图片 如果有
	 *
	 * @param modelId
	 * @param orderType
     * @return
     */
	List<Map<String, Object>> queryCmsArticleViewWithFirstPicByModelIdAndOrderBy(Long modelId, String orderType);

	/**
	 * 根据 模块和创建人查询文章视图 附带第一张图片 如果有
	 * @param modelId
	 * @param createOprId
     * @return
     */
	List<Map<String, Object>> queryCmsArticleViewWithFirstPicByModelIdAndUserId(Long modelId, String createOprId);


	/**
	 * 查询文章根据模块和标题
	 *
	 * @param modelId
	 * @param articleTitle
     * @return
     */
	List<Map<String, Object>> queryCmsArticleViewByModelIdAndLikeArticleTitle(int modelId, String articleTitle);

	/**
	 * 修改文章展示情况
	 *
	 * @param articleHit
	 * @param articleId
     */
	void updateCmsArticleHitByArticleId(int articleHit, Long articleId);

	/**
	 * 修改 文章有效行
	 *
	 * @param active
	 * @param articleId
     */
	void updateCmsArticleActiveByArticleId(int active, Long articleId);


}