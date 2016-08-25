/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.chronic.model;

import java.math.BigDecimal;
import java.util.Date;

import com.xqsight.common.model.BaseModel;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>计步器表实体类</p>
 * <p>Table: STEP_COUNTER - --> StepCounter</p>
 * <p>计步器表</p>
 * @since 2016-05-09 07:51:57
 */
public class StepCounter extends BaseModel {

	/** 主键 */
    private Long stepId;

    /** TEST_TIME - 测试时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date testTime;
	/** STEP_COUNT - 步数 */
    private long stepCount;
	/** KILOMERTRE_COUNT - 步行公里 */
    private BigDecimal kilomertreCount;
	/** ENERGY_COUNT - 消耗能量 */
    private BigDecimal energyCount;

    public Long getStepId(){
        return this.stepId;
    }

    public Date getTestTime() {
        return testTime;
    }

    public void setTestTime(Date testTime) {
        this.testTime = testTime;
    }

    public void setStepId(Long stepId){
        this.stepId = stepId;
    }
	public long getStepCount(){
        return this.stepCount;
    }
    public void setStepCount(Long stepCount){
        this.stepCount = stepCount;
    }
	public BigDecimal getKilomertreCount(){
        return this.kilomertreCount;
    }
    public void setKilomertreCount(BigDecimal kilomertreCount){
        this.kilomertreCount = kilomertreCount;
    }
	public BigDecimal getEnergyCount(){
        return this.energyCount;
    }
    public void setEnergyCount(BigDecimal energyCount){
        this.energyCount = energyCount;
    }
}