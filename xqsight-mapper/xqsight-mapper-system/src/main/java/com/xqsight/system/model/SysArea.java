/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.model;

import com.xqsight.common.model.AbstractTreeModel;

import java.io.Serializable;


/**
 * <p>区域表实体类</p>
 * <p>Table: sys_area - --> SysArea</p>
 * <p>区域表</p>
 * @since 2017-02-22 04:29:28
 * @author wangganggang
 */
public class SysArea extends AbstractTreeModel<SysArea> {

	/** 主键 */
    private Long areaId;

    /** parent_ids - 所有上级 */
    private String parentIds;
    /** area_name - 区域名称 */
    private String areaName;
    /** area_code - 区域编码 */
    private String areaCode;
    /** area_type - 区域类型 */
    private Byte areaType;

    public Long getAreaId(){
        return this.areaId;
    }
    public void setAreaId(Long areaId){
        this.areaId = areaId;
        super.setId("" + areaId);
    }
	public String getParentIds(){
        return this.parentIds;
    }
    public void setParentIds(String parentIds){
        this.parentIds = parentIds;
    }
	public String getAreaName(){
        return this.areaName;
    }
    public void setAreaName(String areaName){
        this.areaName = areaName;
        super.setName(areaName);
    }
	public String getAreaCode(){
        return this.areaCode;
    }
    public void setAreaCode(String areaCode){
        this.areaCode = areaCode;
    }
	public Byte getAreaType(){
        return this.areaType;
    }
    public void setAreaType(Byte areaType){
        this.areaType = areaType;
    }

    @Override
    public Serializable getPK() {
        return this.areaId;
    }
}