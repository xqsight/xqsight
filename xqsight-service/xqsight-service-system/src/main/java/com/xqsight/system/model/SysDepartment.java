/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.model;

import com.xqsight.common.model.TreeBaseModel;

import java.io.Serializable;


/**
 * <p>部门表实体类</p>
 * <p>Table: sys_department - --> SysDepartment</p>
 * <p>部门表</p>
 * @since 2017-01-07 11:57:06
 * @author wangganggang
 */
public class SysDepartment extends TreeBaseModel<SysDepartment>{

	/** 主键 */
    private Long departmentId;

    /** parent_ids - 所有上级 */
    private String parentIds;
    /** department_name - 部门名称 */
    private String departmentName;
    /** department_type - 部门类型 */
    private String departmentType;
    /** department_code - 部门编号 */
    private String departmentCode;
    /** custom_code - 自定义编号 */
    private String customCode;
    /** icon - 图标 */
    private String icon;
    /** sort - 排序 */
    private Integer sort;

    public Long getDepartmentId(){
        return this.departmentId;
    }
    public void setDepartmentId(Long departmentId){
        this.departmentId = departmentId;
    }
	public String getParentIds(){
        return this.parentIds;
    }
    public void setParentIds(String parentIds){
        this.parentIds = parentIds;
    }
	public String getDepartmentName(){
        return this.departmentName;
    }
    public void setDepartmentName(String departmentName){
        this.departmentName = departmentName;
    }
	public String getDepartmentType(){
        return this.departmentType;
    }
    public void setDepartmentType(String departmentType){
        this.departmentType = departmentType;
    }
	public String getDepartmentCode(){
        return this.departmentCode;
    }
    public void setDepartmentCode(String departmentCode){
        this.departmentCode = departmentCode;
    }
	public String getCustomCode(){
        return this.customCode;
    }
    public void setCustomCode(String customCode){
        this.customCode = customCode;
    }
	public String getIcon(){
        return this.icon;
    }
    public void setIcon(String icon){
        this.icon = icon;
    }
	public Integer getSort(){
        return this.sort;
    }
    public void setSort(Integer sort){
        this.sort = sort;
    }

    @Override
    public Serializable getPK() {
        return this.departmentId;
    }
}