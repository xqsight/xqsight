/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.chronic.model;

import java.util.Date;

import com.xqsight.common.model.BaseModel;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>血氧记录表实体类</p>
 * <p>Table: OXYGEN - --> Oxygen</p>
 * <p>血氧记录表</p>
 * @since 2016-05-09 07:51:50
 */
public class Oxygen extends BaseModel {

	/** 主键 */
    private Long booldId;

	/** TEST_TIME - 测试时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date testTime;
	/** OXYGEN - 血氧 */
    private String oxygen;
	/** PULSE_RATE - 脉率 */
    private String pulseRate;
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
	public String getOxygen(){
        return this.oxygen;
    }
    public void setOxygen(String oxygen){
        this.oxygen = oxygen;
    }
	public String getPulseRate(){
        return this.pulseRate;
    }
    public void setPulseRate(String pulseRate){
        this.pulseRate = pulseRate;
    }
	public String getException(){
        return this.exception;
    }
    public void setException(String exception){
        this.exception = exception;
    }
}