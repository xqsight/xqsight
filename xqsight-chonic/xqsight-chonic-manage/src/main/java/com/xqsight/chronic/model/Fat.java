/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.chronic.model;

import java.util.Date;

import com.xqsight.common.model.BaseModel;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>脂肪记录表实体类</p>
 * <p>Table: FAT - --> Fat</p>
 * <p>脂肪记录表</p>
 * @since 2016-05-09 07:50:53
 */
public class Fat extends BaseModel {

	/** 主键 */
    private Long booldId;

	/** TEST_TIME - 测试时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date testTime;
	/** FAT_CONTENT - 脂肪含量 */
    private String fatContent;
	/** FAT_INDEX - 体质指数 */
    private String fatIndex;
	/** WATER_CONTENT - 水分含量 */
    private String waterContent;
	/** BODY_SHAPE - 体型 */
    private String bodyShape;
	/** FAT_RATE - 基础代谢率 */
    private String fatRate;
	/** EXCEPTION - 异常情况 */
    private String exception;

    public Long getBooldId(){
        return this.booldId;
    }
    public void setBooldId(Long booldId){
        this.booldId = booldId;
    }
	public Date getTestTime(){
        return this.testTime;
    }
    public void setTestTime(Date testTime){
        this.testTime = testTime;
    }
	public String getFatContent(){
        return this.fatContent;
    }
    public void setFatContent(String fatContent){
        this.fatContent = fatContent;
    }
	public String getFatIndex(){
        return this.fatIndex;
    }
    public void setFatIndex(String fatIndex){
        this.fatIndex = fatIndex;
    }
	public String getWaterContent(){
        return this.waterContent;
    }
    public void setWaterContent(String waterContent){
        this.waterContent = waterContent;
    }
	public String getBodyShape(){
        return this.bodyShape;
    }
    public void setBodyShape(String bodyShape){
        this.bodyShape = bodyShape;
    }
	public String getFatRate(){
        return this.fatRate;
    }
    public void setFatRate(String fatRate){
        this.fatRate = fatRate;
    }
	public String getException(){
        return this.exception;
    }
    public void setException(String exception){
        this.exception = exception;
    }
}