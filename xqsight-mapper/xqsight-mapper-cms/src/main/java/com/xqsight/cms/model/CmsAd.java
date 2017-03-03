/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.cms.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xqsight.common.model.Model;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * <p>广告表实体类</p>
 * <p>Table: cms_ad - --> CmsAd</p>
 * <p>广告表</p>
 * @since 2017-02-23 04:51:56
 * @author wangganggang
 */
public class CmsAd extends Model{

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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime adBeginTime;
    /** ad_end_time - 结束时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime adEndTime;
    /** type - 类型 */
    private Byte type;
    /** sort - 排序 */
    private Short sort;

    public Long getAdId(){
        return this.adId;
    }
    public void setAdId(Long adId){
        this.adId = adId;
    }
	public Long getSiteId(){
        return this.siteId;
    }
    public void setSiteId(Long siteId){
        this.siteId = siteId;
    }
	public String getAdName(){
        return this.adName;
    }
    public void setAdName(String adName){
        this.adName = adName;
    }
	public String getAdUrl(){
        return this.adUrl;
    }
    public void setAdUrl(String adUrl){
        this.adUrl = adUrl;
    }
	public String getAdText(){
        return this.adText;
    }
    public void setAdText(String adText){
        this.adText = adText;
    }
	public String getAdScript(){
        return this.adScript;
    }
    public void setAdScript(String adScript){
        this.adScript = adScript;
    }
	public String getAdImage(){
        return this.adImage;
    }
    public void setAdImage(String adImage){
        this.adImage = adImage;
    }
	public String getAdFlash(){
        return this.adFlash;
    }
    public void setAdFlash(String adFlash){
        this.adFlash = adFlash;
    }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	public LocalDateTime getAdBeginTime(){
        return this.adBeginTime;
    }
    public void setAdBeginTime(LocalDateTime adBeginTime){
        this.adBeginTime = adBeginTime;
    }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	public LocalDateTime getAdEndTime(){
        return this.adEndTime;
    }
    public void setAdEndTime(LocalDateTime adEndTime){
        this.adEndTime = adEndTime;
    }
	public Byte getType(){
        return this.type;
    }
    public void setType(Byte type){
        this.type = type;
    }
	public Short getSort(){
        return this.sort;
    }
    public void setSort(Short sort){
        this.sort = sort;
    }

    @Override
    public Serializable getPK() {
        return this.adId;
    }
}