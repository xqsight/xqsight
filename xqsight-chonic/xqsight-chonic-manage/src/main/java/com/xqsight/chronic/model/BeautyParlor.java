/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.chronic.model;

import com.xqsight.common.model.BaseModel;

/**
 * <p>美容院表实体类</p>
 * <p>Table: BEAUTY_PARLOR - --> BeautyParlor</p>
 * <p>美容院表</p>
 * @since 2016-05-09 07:48:43
 */
public class BeautyParlor extends BaseModel {

	/** 主键 */
    private Long beautyId;

	/** BEAUTY_NAME - 美容院名称 */
    private String beautyName;
    /** FILE_ID - 图片 */
    private String fileId;
	/** BEAUTY_ADDRESS - 美容院店址 */
    private String beautyAddress;
	/** BEAUTY_PHONE - 美容院电话 */
    private String beautyPhone;
	/** BEAUTY_QQ - 美容院qq */
    private String beautyQq;
	/** BEAUTY_DESCRIPT - 美容院描述 */
    private String beautyDescript;
	/** BEAUTY_LNG - 经度 */
    private String beautyLng;
	/** BEAUTY_LAT - 纬度 */
    private String beautyLat;
	/** COMMENT_HAS_PIC - 是否有图片
            0:有
            -1:没有 */
    private Integer commentHasPic;

    public Long getBeautyId(){
        return this.beautyId;
    }
    public void setBeautyId(Long beautyId){
        this.beautyId = beautyId;
    }
	public String getBeautyName(){
        return this.beautyName;
    }
    public void setBeautyName(String beautyName){
        this.beautyName = beautyName;
    }
	public String getBeautyAddress(){
        return this.beautyAddress;
    }
    public void setBeautyAddress(String beautyAddress){
        this.beautyAddress = beautyAddress;
    }
	public String getBeautyPhone(){
        return this.beautyPhone;
    }
    public void setBeautyPhone(String beautyPhone){
        this.beautyPhone = beautyPhone;
    }
	public String getBeautyQq(){
        return this.beautyQq;
    }
    public void setBeautyQq(String beautyQq){
        this.beautyQq = beautyQq;
    }
	public String getBeautyDescript(){
        return this.beautyDescript;
    }
    public void setBeautyDescript(String beautyDescript){
        this.beautyDescript = beautyDescript;
    }
	public String getBeautyLng(){
        return this.beautyLng;
    }
    public void setBeautyLng(String beautyLng){
        this.beautyLng = beautyLng;
    }
	public String getBeautyLat(){
        return this.beautyLat;
    }
    public void setBeautyLat(String beautyLat){
        this.beautyLat = beautyLat;
    }
	public Integer getCommentHasPic(){
        return this.commentHasPic;
    }
    public void setCommentHasPic(Integer commentHasPic){
        this.commentHasPic = commentHasPic;
    }
	/**
	 * getter method
	 * @return the fileId
	 */
	public String getFileId() {
		return fileId;
	}
	/**
	 * setter method
	 * @param fileId the fileId to set
	 */
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
    
}