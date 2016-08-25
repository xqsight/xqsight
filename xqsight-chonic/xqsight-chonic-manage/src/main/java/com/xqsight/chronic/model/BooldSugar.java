/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.chronic.model;

import java.util.Date;

import com.xqsight.common.model.BaseModel;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>血糖记录表实体类</p>
 * <p>Table: BOOLD_SUGAR - --> BooldSugar</p>
 * <p>血糖记录表</p>
 * @since 2016-05-09 07:50:39
 */
public class BooldSugar extends BaseModel {

	/** 主键 */
    private Long booldId;

	/** TEST_TIME - 测试时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date testTime;
	/** EMPTY - 空腹 */
    private String empty;
	/** TWO_HOURS - 两小时后 */
    private String twoHours;
	/** RANDOM - 随即 */
    private String random;
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
	public String getEmpty(){
        return this.empty;
    }
    public void setEmpty(String empty){
        this.empty = empty;
    }
	public String getTwoHours(){
        return this.twoHours;
    }
    public void setTwoHours(String twoHours){
        this.twoHours = twoHours;
    }
	public String getRandom(){
        return this.random;
    }
    public void setRandom(String random){
        this.random = random;
    }
	public String getException(){
        return this.exception;
    }
    public void setException(String exception){
        this.exception = exception;
    }
}