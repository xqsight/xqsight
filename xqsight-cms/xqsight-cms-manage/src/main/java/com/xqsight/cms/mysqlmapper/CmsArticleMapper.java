/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.cms.mysqlmapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.xqsight.cms.model.CmsArticle;
import com.xqsight.cms.mysqlmapper.builder.CmsArticleSQLBuilder;

/**
 * <p>数据库Mapper类</p>
 * <p>文章表</p>
 * @since 2016-05-07 08:00:35
 */
public interface CmsArticleMapper {

	@Insert(" INSERT INTO CMS_ARTICLE(MODEL_ID,ARTICLE_TITLE,FILE_ID,ARTICLE_AUTHOR,ARTICLE_CONTENT,ARTICLE_DESCRIPTION,ARTICLE_TYPE,ARTICLE_URL,ARTICLE_KEYWORD,ARTICLE_SOURCLE,ARTICLE_HIT,ARTICLE_HAS_PIC,ACTIVE,CREATE_TIME,CREATE_OPR_ID,REMARK)VALUES(#{modelId,jdbcType=NUMERIC},#{articleTitle,jdbcType=VARCHAR},#{fileId,jdbcType=VARCHAR},#{articleAuthor,jdbcType=VARCHAR},#{articleContent,jdbcType=VARCHAR},#{articleDescription,jdbcType=VARCHAR},#{articleType,jdbcType=VARCHAR},#{articleUrl,jdbcType=VARCHAR},#{articleKeyword,jdbcType=VARCHAR},#{articleSourcle,jdbcType=VARCHAR},#{articleHit,jdbcType=NUMERIC},#{articleHasPic,jdbcType=NUMERIC},#{active,jdbcType=NUMERIC},#{createTime,jdbcType=TIMESTAMP},#{createOprId,jdbcType=VARCHAR},#{remark,jdbcType=VARCHAR})")
	@Options(useGeneratedKeys = true, keyProperty = "articleId")
	void saveCmsArticle(CmsArticle cmsArticle);
	
	@Update(" UPDATE CMS_ARTICLE SET MODEL_ID=#{modelId,jdbcType=NUMERIC},ARTICLE_TITLE=#{articleTitle,jdbcType=VARCHAR},FILE_ID=#{fileId,jdbcType=VARCHAR},ARTICLE_AUTHOR=#{articleAuthor,jdbcType=VARCHAR},ARTICLE_CONTENT=#{articleContent,jdbcType=VARCHAR},ARTICLE_DESCRIPTION=#{articleDescription,jdbcType=VARCHAR},ARTICLE_TYPE=#{articleType,jdbcType=VARCHAR},ARTICLE_URL=#{articleUrl,jdbcType=VARCHAR},ARTICLE_KEYWORD=#{articleKeyword,jdbcType=VARCHAR},ARTICLE_SOURCLE=#{articleSourcle,jdbcType=VARCHAR},ARTICLE_HIT=#{articleHit,jdbcType=NUMERIC},ARTICLE_HAS_PIC=#{articleHasPic,jdbcType=NUMERIC},ACTIVE=#{active,jdbcType=NUMERIC},UPDATE_TIME=#{updateTime,jdbcType=TIMESTAMP},UPD_OPR_ID=#{updOprId,jdbcType=VARCHAR},REMARK=#{remark,jdbcType=VARCHAR} WHERE ARTICLE_ID=#{articleId,jdbcType=NUMERIC}")
	void updateCmsArticle(CmsArticle cmsArticle);
	
	@Delete(" DELETE FROM CMS_ARTICLE WHERE ARTICLE_ID=#{articleId,jdbcType=NUMERIC}")
	void deleteCmsArticle(@Param("articleId") Long articleId);
	
	@Select(" SELECT MODEL_ID,FILE_ID,ARTICLE_ID,ARTICLE_TITLE,ARTICLE_AUTHOR,ARTICLE_DESCRIPTION,ARTICLE_CONTENT,ARTICLE_TYPE,ARTICLE_URL,ARTICLE_KEYWORD,ARTICLE_SOURCLE,ARTICLE_HIT,ARTICLE_HAS_PIC,ACTIVE,CREATE_TIME,CREATE_OPR_ID,UPDATE_TIME,UPD_OPR_ID,REMARK FROM CMS_ARTICLE ORDER BY CREATE_TIME DESC")
	List<CmsArticle> queryCmsArticle();

	@Select(" SELECT MODEL_ID,FILE_ID,ARTICLE_ID,ARTICLE_TITLE,ARTICLE_AUTHOR,ARTICLE_DESCRIPTION,ARTICLE_CONTENT,ARTICLE_TYPE,ARTICLE_URL,ARTICLE_KEYWORD,ARTICLE_SOURCLE,ARTICLE_HIT,ARTICLE_HAS_PIC,ACTIVE,CREATE_TIME,CREATE_OPR_ID,UPDATE_TIME,UPD_OPR_ID,REMARK FROM CMS_ARTICLE WHERE MODEL_ID=#{modelId,jdbcType=NUMERIC} ORDER BY CREATE_TIME DESC")
	List<CmsArticle> queryCmsArticleByModelId(@Param("modelId") Long modelId);

	@Select(" SELECT MODEL_ID,FILE_ID,ARTICLE_ID,ARTICLE_TITLE,ARTICLE_AUTHOR,ARTICLE_DESCRIPTION,ARTICLE_CONTENT,ARTICLE_TYPE,ARTICLE_URL,ARTICLE_KEYWORD,ARTICLE_SOURCLE,ARTICLE_HIT,ARTICLE_HAS_PIC,ACTIVE,CREATE_TIME,CREATE_OPR_ID,UPDATE_TIME,UPD_OPR_ID,REMARK FROM CMS_ARTICLE WHERE MODEL_ID IN (${modelIds}) ORDER BY CREATE_TIME DESC")
	List<CmsArticle> queryCmsArticleByModelIds(@Param("modelIds") String modelIds);
	
	@Select(" SELECT MODEL_ID,FILE_ID,ARTICLE_ID,ARTICLE_TITLE,ARTICLE_AUTHOR,ARTICLE_DESCRIPTION,ARTICLE_CONTENT,ARTICLE_TYPE,ARTICLE_URL,ARTICLE_KEYWORD,ARTICLE_SOURCLE,ARTICLE_HIT,ARTICLE_HAS_PIC,ACTIVE,CREATE_TIME,CREATE_OPR_ID,UPDATE_TIME,UPD_OPR_ID,REMARK FROM CMS_ARTICLE WHERE ARTICLE_ID=#{articleId,jdbcType=NUMERIC} ORDER BY CREATE_TIME DESC")
	CmsArticle queryCmsArticleById(@Param("articleId") Long articleId);
	
	@Update(" UPDATE CMS_ARTICLE SET ARTICLE_HIT = #{articleHit,jdbcType=NUMERIC} WHERE ARTICLE_ID=#{articleId,jdbcType=NUMERIC}")
	void updateCmsArticleHitByArticleId(@Param("articleHit") int articleHit, @Param("articleId") Long articleId);

	@Update(" UPDATE CMS_ARTICLE SET ACTIVE = #{active,jdbcType=NUMERIC} WHERE ARTICLE_ID=#{articleId,jdbcType=NUMERIC}")
	void updateCmsArticleActiveByArticleId(@Param("active") int active, @Param("articleId") Long articleId);
}