/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.chronic.model;

import java.util.Date;

import com.xqsight.common.model.BaseModel;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>血压记录表实体类</p>
 * <p>Table: BOOLD_PRESSURE - --> BooldPressure</p>
 * <p>血压记录表</p>
 * @since 2016-05-09 07:50:31
 */
public class BooldPressure extends BaseModel {

	/** 主键 */
    private Long booldId;

	/** TEST_TIME - 测试时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date testTime;
	/** SYSTOLIC_PRESSURE - 收缩压 */
    private String systolicPressure;
	/** DISATOLIC_PRESSURE - 收缩压 */
    private String disatolicPressure;
	/** VENOUS_PRESSURE - 舒张压 */
    private String venousPressure;
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
	public String getSystolicPressure(){
        return this.systolicPressure;
    }
    public void setSystolicPressure(String systolicPressure){
        this.systolicPressure = systolicPressure;
    }
	public String getDisatolicPressure(){
        return this.disatolicPressure;
    }
    public void setDisatolicPressure(String disatolicPressure){
        this.disatolicPressure = disatolicPressure;
    }
	public String getVenousPressure(){
        return this.venousPressure;
    }
    public void setVenousPressure(String venousPressure){
        this.venousPressure = venousPressure;
    }
	public String getException(){
        return this.exception;
    }
    public void setException(String exception){
        this.exception = exception;
    }
}