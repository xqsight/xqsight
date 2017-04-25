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
import java.time.LocalDateTime;


/**
 * <p>广告表实体类</p>
 * <p>Table: cms_ad - --> CmsAd</p>
 * <p>广告表</p>
 * @since 2017-02-23 04:51:56
 * @author wangganggang
 */
@Data
public class CmsAd extends BaseModel {

	/** 主键 */
    private Long adId;

    /** site_id - 站点内码 */
    private Long siteId;
    /** ad_name - 名称 */
    private String adName;
    /** ad_url - 广告url */
    private String adUrl;
    /** ad_text - 文字 */
    private String adText;
    /** ad_script - 代码 */
    private String adScript;
    /** ad_image - 图片 */
    private String adImage;
    /** ad_flash - flash */
    private String adFlash;
    /** ad_begin_time - 开始时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd",iso = DateTimeFormat.ISO.DATE)
    private LocalDate adBeginTime;
    /** ad_end_time - 结束时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd",iso = DateTimeFormat.ISO.DATE)
    private LocalDate adEndTime;
    /** type - 类型 */
    private Byte type;
    /** sort - 排序 */
    private Short sort;

    @JsonFormat(pattern="yyyy-MM-dd")
	public LocalDate getAdBeginTime(){
        return this.adBeginTime;
    }
    @JsonFormat(pattern="yyyy-MM-dd")
	public LocalDate getAdEndTime(){
        return this.adEndTime;
    }

    @Override
    public Serializable getPK() {
        return this.adId;
    }
}