/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.cms.model;

import com.xqsight.common.model.TreeBaseModel;

import java.io.Serializable;


/**
 * <p>职位表实体类</p>
 * <p>Table: cms_position - --> CmsPosition</p>
 * <p>职位表</p>
 * @since 2017-02-23 04:52:22
 * @author wangganggang
 */
public class CmsPosition extends TreeBaseModel<CmsPosition>{

	/** 主键 */
    private Long positionId;

    /** parent_ids - 所有上级 */
    private String parentIds;
    /** position_name - 职位名称 */
    private String positionName;
    /** position_code - 职位编码 */
    private String positionCode;

    public Long getPositionId(){
        return this.positionId;
    }
    public void setPositionId(Long positionId){
        this.positionId = positionId;
        super.setId("" + positionId);
    }
	public String getParentIds(){
        return this.parentIds;
    }
    public void setParentIds(String parentIds){
        this.parentIds = parentIds;
    }
	public String getPositionName(){
        return this.positionName;
    }
    public void setPositionName(String positionName){
        this.positionName = positionName;
        super.setName(positionName);
    }
	public String getPositionCode(){
        return this.positionCode;
    }
    public void setPositionCode(String positionCode){
        this.positionCode = positionCode;
    }

    @Override
    public Serializable getPK() {
        return this.positionId;
    }
}