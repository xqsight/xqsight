/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.chronic.model;

import java.util.Date;

import com.xqsight.common.model.BaseModel;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>心电记录表实体类</p>
 * <p>Table: ECG - --> Ecg</p>
 * <p>心电记录表</p>
 * @since 2016-05-09 07:50:46
 */
public class Ecg extends BaseModel {

	/** 主键 */
    private Long booldId;

	/** TEST_TIME - 测试时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date testTime;
	/** HEART_RATE - 心率 */
    private String heartRate;
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
	public String getHeartRate(){
        return this.heartRate;
    }
    public void setHeartRate(String heartRate){
        this.heartRate = heartRate;
    }
	public String getException(){
        return this.exception;
    }
    public void setException(String exception){
        this.exception = exception;
    }
}