/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.model;

import com.xqsight.common.model.Model;

import java.io.Serializable;


/**
 * <p>组织机构表实体类</p>
 * <p>Table: sys_org - --> SysOrg</p>
 * <p>组织机构表</p>
 * @since 2017-01-05 06:11:00
 * @author wangganggang
 */
public class SysOrg extends Model{

	/** 主键 */
    private Long orgId;

    /** parent_id - 父级ID */
    private Long parentId;
    /** org_name - 组织名称 */
    private String orgName;
    /** org_type - 组织类型 */
    private String orgType;
    /** org_code - 组织编号 */
    private String orgCode;
    /** custom_code - 自定义编号 */
    private String customCode;
    /** sort - 排序 */
    private Integer sort;
    /** icon - 图标 */
    private String icon;

    public Long getOrgId(){
        return this.orgId;
    }
    public void setOrgId(Long orgId){
        this.orgId = orgId;
    }
	public Long getParentId(){
        return this.parentId;
    }
    public void setParentId(Long parentId){
        this.parentId = parentId;
    }
	public String getOrgName(){
        return this.orgName;
    }
    public void setOrgName(String orgName){
        this.orgName = orgName;
    }
	public String getOrgType(){
        return this.orgType;
    }
    public void setOrgType(String orgType){
        this.orgType = orgType;
    }
	public String getOrgCode(){
        return this.orgCode;
    }
    public void setOrgCode(String orgCode){
        this.orgCode = orgCode;
    }
	public String getCustomCode(){
        return this.customCode;
    }
    public void setCustomCode(String customCode){
        this.customCode = customCode;
    }
	public Integer getSort(){
        return this.sort;
    }
    public void setSort(Integer sort){
        this.sort = sort;
    }
	public String getIcon(){
        return this.icon;
    }
    public void setIcon(String icon){
        this.icon = icon;
    }

    @Override
    public Serializable getPK() {
        return this.orgId;
    }
}