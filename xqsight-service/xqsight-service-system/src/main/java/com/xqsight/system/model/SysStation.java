/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.model;

import com.xqsight.common.model.Model;

import java.io.Serializable;


/**
 * <p>岗位信息表实体类</p>
 * <p>Table: sys_station - --> SysStation</p>
 * <p>岗位信息表</p>
 * @since 2017-01-07 11:58:11
 * @author wangganggang
 */
public class SysStation extends Model{

	/** 主键 */
    private Long stationId;

    /** department_id - 部门内码 */
    private Long departmentId;
    /** station_name - 岗位名称 */
    private String stationName;
    /** station_code - 岗位编号 */
    private String stationCode;
    /** station_type - 岗位类型 */
    private String stationType;

    public Long getStationId(){
        return this.stationId;
    }
    public void setStationId(Long stationId){
        this.stationId = stationId;
    }
	public Long getDepartmentId(){
        return this.departmentId;
    }
    public void setDepartmentId(Long departmentId){
        this.departmentId = departmentId;
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
	public String getStationType(){
        return this.stationType;
    }
    public void setStationType(String stationType){
        this.stationType = stationType;
    }

    @Override
    public Serializable getPK() {
        return this.stationId;
    }
}