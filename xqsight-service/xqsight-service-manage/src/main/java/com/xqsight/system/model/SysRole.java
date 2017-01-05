/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.model;

import com.xqsight.common.model.Model;

import java.io.Serializable;


/**
 * <p>角色信息表实体类</p>
 * <p>Table: sys_role - --> SysRole</p>
 * <p>角色信息表</p>
 * @since 2017-01-05 06:11:10
 * @author wangganggang
 */
public class SysRole extends Model{

	/** 主键 */
    private Long roleId;

    /** role_name - 角色名称 */
    private String roleName;
    /** role_code - 角色编号 */
    private String roleCode;
    /** role_type - 角色类型 */
    private String roleType;

    public Long getRoleId(){
        return this.roleId;
    }
    public void setRoleId(Long roleId){
        this.roleId = roleId;
    }
	public String getRoleName(){
        return this.roleName;
    }
    public void setRoleName(String roleName){
        this.roleName = roleName;
    }
	public String getRoleCode(){
        return this.roleCode;
    }
    public void setRoleCode(String roleCode){
        this.roleCode = roleCode;
    }
	public String getRoleType(){
        return this.roleType;
    }
    public void setRoleType(String roleType){
        this.roleType = roleType;
    }

    @Override
    public Serializable getPK() {
        return this.roleId;
    }
}