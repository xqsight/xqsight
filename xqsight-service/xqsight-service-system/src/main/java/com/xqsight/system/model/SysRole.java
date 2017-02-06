/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.model;

import com.xqsight.common.model.TreeBaseModel;

import java.io.Serializable;


/**
 * <p>角色信息表实体类</p>
 * <p>Table: sys_role - --> SysRole</p>
 * <p>角色信息表</p>
 * @since 2017-01-07 11:58:03
 * @author wangganggang
 */
public class SysRole extends TreeBaseModel<SysRole>{

	/** 主键 */
    private Long roleId;

    /** parent_ids - 所有上级 */
    private String parentIds;
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
        super.setId("" + roleId);
    }
	public String getParentIds(){
        return this.parentIds;
    }
    public void setParentIds(String parentIds){
        this.parentIds = parentIds;
    }
	public String getRoleName(){
        return this.roleName;
    }
    public void setRoleName(String roleName){
        this.roleName = roleName;
        super.setName(roleName);
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