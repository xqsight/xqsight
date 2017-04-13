/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.cms.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xqsight.common.model.BaseModel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


/**
 * <p>文章表实体类</p>
 * <p>Table: cms_article - --> CmsArticle</p>
 * <p>文章表</p>
 * @since 2017-02-28 03:25:22
 * @author wangganggang
 */
@Data
public class CmsArticle extends BaseModel {

	/** 主键 */
    private Long articleId;

    /** model_id - 模块内码 */
    private Long modelId;
    /** article_img - 文章缩列图 */
    private String articleImg;
    /** thumbnail_img - 文章缩列图 */
    private String thumbnailImg;
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
    /** department - 部门 */
    private String department;
    /** article_source - 文章来源 */
    private String articleSource;
    /** publish_time - 发布时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate publishTime;
    /** article_hit - 是否显示 0:显示-1:隐藏 */
    private Byte articleHit;

    /** scan_times - 浏览量 */
    private Long scanTimes;

    /** agree_times - 点赞量 */
    private Long agreeTimes;

    private List<String> tags;

    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	public LocalDate getPublishTime(){
        return this.publishTime;
    }

    @Override
    public Serializable getPK() {
        return this.articleId;
    }
}