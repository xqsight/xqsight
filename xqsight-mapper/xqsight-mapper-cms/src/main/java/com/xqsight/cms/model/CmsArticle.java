/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.cms.model;

import com.xqsight.common.model.Model;

import java.io.Serializable;


/**
 * <p>文章表实体类</p>
 * <p>Table: cms_article - --> CmsArticle</p>
 * <p>文章表</p>
 * @since 2017-02-23 04:52:03
 * @author wangganggang
 */
public class CmsArticle extends Model{

	/** 主键 */
    private Long articleId;

    /** model_id - 模块内码 */
    private Long modelId;
    /** article_img - 文章缩列图 */
    private String articleImg;
    /** article_title - 文章标题 */
    private String articleTitle;
    /** article_author - 文章作者 */
    private String articleAuthor;
    /** article_desp - 文章描述 */
    private String articleDesp;
    /** article_content - 文章内容 */
    private String articleContent;
    /** article_url - 文章访问URl */
    private String articleUrl;
    /** article_source - 文章来源 */
    private String articleSource;
    /** article_hit - 是否显示 0:显示-1:隐藏 */
    private Byte articleHit;

    public Long getArticleId(){
        return this.articleId;
    }
    public void setArticleId(Long articleId){
        this.articleId = articleId;
    }
	public Long getModelId(){
        return this.modelId;
    }
    public void setModelId(Long modelId){
        this.modelId = modelId;
    }
	public String getArticleImg(){
        return this.articleImg;
    }
    public void setArticleImg(String articleImg){
        this.articleImg = articleImg;
    }
	public String getArticleTitle(){
        return this.articleTitle;
    }
    public void setArticleTitle(String articleTitle){
        this.articleTitle = articleTitle;
    }
	public String getArticleAuthor(){
        return this.articleAuthor;
    }
    public void setArticleAuthor(String articleAuthor){
        this.articleAuthor = articleAuthor;
    }
	public String getArticleDesp(){
        return this.articleDesp;
    }
    public void setArticleDesp(String articleDesp){
        this.articleDesp = articleDesp;
    }
	public String getArticleContent(){
        return this.articleContent;
    }
    public void setArticleContent(String articleContent){
        this.articleContent = articleContent;
    }
	public String getArticleUrl(){
        return this.articleUrl;
    }
    public void setArticleUrl(String articleUrl){
        this.articleUrl = articleUrl;
    }
	public String getArticleSource(){
        return this.articleSource;
    }
    public void setArticleSource(String articleSource){
        this.articleSource = articleSource;
    }
	public Byte getArticleHit(){
        return this.articleHit;
    }
    public void setArticleHit(Byte articleHit){
        this.articleHit = articleHit;
    }

    @Override
    public Serializable getPK() {
        return this.articleId;
    }
}