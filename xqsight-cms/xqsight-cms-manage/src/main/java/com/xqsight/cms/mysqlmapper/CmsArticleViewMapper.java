/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.cms.mysqlmapper;

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

	@Select(" SELECT * FROM VIEW_ARTICLE WHERE MODEL_ID = #{modelId,jdbcType=NUMERIC} ORDER BY CREATE_TIME DESC")
	List<Map<String, Object>> queryCmsArticleViewByModelId(@Param("modelId") int modelId);

	@Select(" SELECT * FROM VIEW_ARTICLE WHERE MODEL_ID = #{modelId,jdbcType=NUMERIC} AND ARTICLE_TITLE LIKE '%${articleTitle}%' ORDER BY CREATE_TIME DESC")
	List<Map<String, Object>> queryCmsArticleViewByModelIdAndLikeArticleTitle(@Param("modelId") int modelId,@Param("articleTitle") String articleTitle);

	@Select(" SELECT * FROM VIEW_ARTICLE WHERE ARTICLE_ID = #{articleId,jdbcType=NUMERIC} ORDER BY CREATE_TIME DESC")
	Map<String, Object> queryCmsArticleViewByArticleId(@Param("articleId") Long articleId);

	@Select(" SELECT * FROM VIEW_ARTICLE WHERE MODEL_ID IN (${modelIds,jdbcType=VARCHAR}) ORDER BY CREATE_TIME DESC")
	List<Map<String, Object>> queryCmsArticleViewByModelIds(@Param("modelIds") String modelIds);

	@Select(" SELECT * FROM VIEW_ARTICLE WHERE MODEL_ID = #{modelId,jdbcType=NUMERIC} AND ACTIVE = #{active,jdbcType=NUMERIC}  ORDER BY CREATE_TIME DESC")
	List<Map<String, Object>> queryCmsArticleViewByModelIdAndActive(@Param("modelId") int modelId, @Param("active") int active);

	@Select(" SELECT * FROM VIEW_ARTICLE WHERE MODEL_ID = #{modelId,jdbcType=NUMERIC} AND CREATE_OPR_ID = #{createOprId,jdbcType=VARCHAR} ORDER BY CREATE_TIME DESC")
	List<Map<String, Object>> queryCmsArticleViewByModelIdAndCreateOprId(@Param("modelId") int modelId, @Param("createOprId") String createOprId);

	@Select(" SELECT * FROM VIEW_ARTICLE WHERE MODEL_ID = #{modelId,jdbcType=NUMERIC} AND CREATE_OPR_ID = #{createOprId,jdbcType=VARCHAR} AND ACTIVE = #{active,jdbcType=NUMERIC} ORDER BY CREATE_TIME DESC")
	List<Map<String, Object>> queryCmsArticleViewByModelIdAndCreateOprIdAndActive(@Param("modelId") int modelId, @Param("createOprId") String createOprId, @Param("active") int active);

	@Select(" SELECT VAR.*,ifnull(SF.FILE_URL,' ') IMG_PATH FROM VIEW_ARTICLE VAR LEFT JOIN SYS_FILE SF ON SF.FILE_ID = SUBSTRING_INDEX(VAR.FILE_ID,',',1)  WHERE VAR.MODEL_ID = #{modelId,jdbcType=NUMERIC} ORDER BY ${orderType} DESC")
	List<Map<String, Object>> queryCmsArticleViewWithFirstPicByModelIdAndOrderBy(@Param("modelId") Long modelId, @Param("orderType") String orderType);

	@Select(" SELECT VAR.*,ifnull(SF.FILE_URL,' ') IMG_PATH FROM VIEW_ARTICLE VAR LEFT JOIN SYS_FILE SF ON SF.FILE_ID = SUBSTRING_INDEX(VAR.FILE_ID,',',1)  WHERE VAR.MODEL_ID = #{modelId,jdbcType=NUMERIC} AND VAR.CREATE_OPR_ID = #{createOprId,jdbcType=VARCHAR} ORDER BY VAR.CREATE_TIME DESC ")
	List<Map<String, Object>> queryCmsArticleViewWithFirstPicByModelIdAndUserId(@Param("modelId") Long modelId, @Param("createOprId") String createOprId);
}