/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.cms.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>数据库Mapper类</p>
 * <p>文章视图表</p>
 * @since 2016-05-07 08:00:35
 */
public interface CmsArticleViewMapper {

	@Select(" select * from view_article where model_id = #{modelId,jdbcType=NUMERIC} order by create_time desc")
	List<Map<String, Object>> queryCmsArticleViewByModelId(@Param("modelId") int modelId);

	@Select(" select * from view_article where model_id = #{modelId,jdbcType=NUMERIC} and article_title like '%${articleTitle}%' order by create_time desc")
	List<Map<String, Object>> queryCmsArticleViewByModelIdAndLikeArticleTitle(@Param("modelId") int modelId,@Param("articleTitle") String articleTitle);

	@Select(" select * from view_article where article_id = #{articleId,jdbcType=NUMERIC} order by create_time desc")
	Map<String, Object> queryCmsArticleViewByArticleId(@Param("articleId") Long articleId);

	@Select(" select * from view_article where model_id in (${modelIds,jdbcType=VARCHAR}) order by create_time desc")
	List<Map<String, Object>> queryCmsArticleViewByModelIds(@Param("modelIds") String modelIds);

	@Select(" select * from view_article where model_id = #{modelId,jdbcType=NUMERIC} and active = #{active,jdbcType=NUMERIC}  order by create_time desc")
	List<Map<String, Object>> queryCmsArticleViewByModelIdAndActive(@Param("modelId") int modelId, @Param("active") int active);

	@Select(" select * from view_article where model_id = #{modelId,jdbcType=NUMERIC} and create_user_id = #{createUserId,jdbcType=VARCHAR} order by create_time desc")
	List<Map<String, Object>> queryCmsArticleViewByModelIdAndCreateOprId(@Param("modelId") int modelId, @Param("createUserId") String createUserId);

	@Select(" select * from view_article where model_id = #{modelId,jdbcType=NUMERIC} and create_user_id = #{createUserId,jdbcType=VARCHAR} and active = #{active,jdbcType=NUMERIC} order by create_time desc")
	List<Map<String, Object>> queryCmsArticleViewByModelIdAndCreateOprIdAndActive(@Param("modelId") int modelId, @Param("createUserId") String createUserId, @Param("active") int active);

	@Select(" select var.*,ifnull(sf.file_url,' ') img_path from view_article var left join sys_file sf on sf.file_id = substring_index(var.file_id,',',1)  where var.model_id = #{modelId,jdbcType=NUMERIC} order by ${orderType} desc")
	List<Map<String, Object>> queryCmsArticleViewWithFirstPicByModelIdAndOrderBy(@Param("modelId") Long modelId, @Param("orderType") String orderType);

	@Select(" select var.*,ifnull(sf.file_url,' ') img_path from view_article var left join sys_file sf on sf.file_id = substring_index(var.file_id,',',1)  where var.model_id = #{modelId,jdbcType=NUMERIC} and var.create_user_id = #{createUserId,jdbcType=VARCHAR} order by var.create_time desc ")
	List<Map<String, Object>> queryCmsArticleViewWithFirstPicByModelIdAndUserId(@Param("modelId") Long modelId, @Param("createUserId") String createUserId);
}