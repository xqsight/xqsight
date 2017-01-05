/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xqsight.cms.model.CmsArticle;

/**
 * <p>数据库Mapper类</p>
 * <p>文章表</p>
 * @since 2016-05-07 08:00:35
 */
public interface CmsArticleMapper {

	@Insert(" insert into cms_article(model_id,article_title,file_id,article_author,article_content,article_description,article_type,article_url,article_keyword,article_source,article_hit,article_has_pic,active,create_time,create_user_id,remark)values(#{modelId,jdbcType=NUMERIC},#{articleTitle,jdbcType=VARCHAR},#{fileId,jdbcType=VARCHAR},#{articleAuthor,jdbcType=VARCHAR},#{articleContent,jdbcType=VARCHAR},#{articleDescription,jdbcType=VARCHAR},#{articleType,jdbcType=VARCHAR},#{articleUrl,jdbcType=VARCHAR},#{articleKeyword,jdbcType=VARCHAR},#{articleSource,jdbcType=VARCHAR},#{articleHit,jdbcType=NUMERIC},#{articleHasPic,jdbcType=NUMERIC},#{active,jdbcType=NUMERIC},#{createTime,jdbcType=TIMESTAMP},#{createUserId,jdbcType=VARCHAR},#{remark,jdbcType=VARCHAR})")
	@Options(useGeneratedKeys = true, keyProperty = "articleId")
	void saveCmsArticle(CmsArticle cmsArticle);
	
	@Update(" update cms_article set model_id=#{modelId,jdbcType=NUMERIC},article_title=#{articleTitle,jdbcType=VARCHAR},file_id=#{fileId,jdbcType=VARCHAR},article_author=#{articleAuthor,jdbcType=VARCHAR},article_content=#{articleContent,jdbcType=VARCHAR},article_description=#{articleDescription,jdbcType=VARCHAR},article_type=#{articleType,jdbcType=VARCHAR},article_url=#{articleUrl,jdbcType=VARCHAR},article_keyword=#{articleKeyword,jdbcType=VARCHAR},article_source=#{articleSource,jdbcType=VARCHAR},article_hit=#{articleHit,jdbcType=NUMERIC},article_has_pic=#{articleHasPic,jdbcType=NUMERIC},active=#{active,jdbcType=NUMERIC},update_time=#{updateTime,jdbcType=TIMESTAMP},upd_opr_id=#{updateUserId,jdbcType=VARCHAR},remark=#{remark,jdbcType=VARCHAR} where article_id=#{articleId,jdbcType=NUMERIC}")
	void updateCmsArticle(CmsArticle cmsArticle);
	
	@Delete(" delete from cms_article where article_id=#{articleId,jdbcType=NUMERIC}")
	void deleteCmsArticle(@Param("articleId") Long articleId);
	
	@Select(" select model_id,file_id,article_id,article_title,article_author,article_description,article_content,article_type,article_url,article_keyword,article_source,article_hit,article_has_pic,active,create_time,create_user_id,update_time,upd_opr_id,remark from cms_article order by create_time desc")
	List<CmsArticle> queryCmsArticle();

	@Select(" select model_id,file_id,article_id,article_title,article_author,article_description,article_content,article_type,article_url,article_keyword,article_source,article_hit,article_has_pic,active,create_time,create_user_id,update_time,upd_opr_id,remark from cms_article where model_id=#{modelId,jdbcType=NUMERIC} order by create_time desc")
	List<CmsArticle> queryCmsArticleByModelId(@Param("modelId") Long modelId);

	@Select(" select model_id,file_id,article_id,article_title,article_author,article_description,article_content,article_type,article_url,article_keyword,article_source,article_hit,article_has_pic,active,create_time,create_user_id,update_time,upd_opr_id,remark from cms_article where model_id in (${modelIds}) order by create_time desc")
	List<CmsArticle> queryCmsArticleByModelIds(@Param("modelIds") String modelIds);
	
	@Select(" select model_id,file_id,article_id,article_title,article_author,article_description,article_content,article_type,article_url,article_keyword,article_source,article_hit,article_has_pic,active,create_time,create_user_id,update_time,upd_opr_id,remark from cms_article where article_id =#{articleId,jdbcType=NUMERIC} order by create_time desc")
	CmsArticle queryCmsArticleById(@Param("articleId") Long articleId);
	
	@Update(" update cms_article set article_hit = #{articleHit,jdbcType=NUMERIC} where article_id =#{articleId,jdbcType=NUMERIC}")
	void updateCmsArticleHitByArticleId(@Param("articleHit") int articleHit, @Param("articleId") Long articleId);

	@Update(" update cms_article set active = #{active,jdbcType=NUMERIC} where article_id =#{articleId,jdbcType=NUMERIC}")
	void updateCmsArticleActiveByArticleId(@Param("active") int active, @Param("articleId") Long articleId);
}