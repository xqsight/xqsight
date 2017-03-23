/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.model;

import com.xqsight.common.model.BaseModel;

import java.io.Serializable;


/**
 * <p>岗位信息表实体类</p>
 * <p>Table: sys_station - --> SysStation</p>
 * <p>岗位信息表</p>
 * @since 2017-02-22 04:31:36
 * @author wangganggang
 */
public class SysStation extends BaseModel {

	/** 主键 */
    private Long stationId;

    /** office_id - 机构内码 */
    private Long officeId;
    /** station_name - 岗位名称 */
    private String stationName;
    /** station_code - 岗位编号 */
    private String stationCode;
    /** station_type - 岗位类型 */
    private Byte stationType;

    public Long getStationId(){
        return this.stationId;
    }
    public void setStationId(Long stationId){
        this.stationId = stationId;
    }
	public Long getOfficeId(){
        return this.officeId;
    }
    public void setOfficeId(Long officeId){
        this.officeId = officeId;
    }
	public String getStationName(){
        return this.stationName;
    }
    public void setStationName(String stationName){
        this.stationName = stationName;
    }
	public String getStationCode(){
        return this.stationCode;
    }
    public void setStationCode(String stationCode){
        this.stationCode = stationCode;
    }
	public Byte getStationType(){
        return this.stationType;
    }
    public void setStationType(Byte stationType){
        this.stationType = stationType;
    }

    @Override
    public Serializable getPK() {
        return this.stationId;
    }
}