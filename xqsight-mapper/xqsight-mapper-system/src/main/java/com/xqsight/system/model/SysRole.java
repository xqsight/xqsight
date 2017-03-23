/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.model;

import com.xqsight.common.model.BaseModel;

import java.io.Serializable;


/**
 * <p>角色信息表实体类</p>
 * <p>Table: sys_role - --> SysRole</p>
 * <p>角色信息表</p>
 * @since 2017-02-22 04:30:04
 * @author wangganggang
 */
public class SysRole extends BaseModel {

	/** 主键 */
    private Long roleId;

    /** office_id - 归属机构 */
    private Long officeId;
    /** role_name - 角色名称 */
    private String roleName;
    /** role_enname - 英文名称 */
    private String roleEnname;
    /** role_type - 角色类型 */
    private String roleType;
    /** sys_flag - 是否系统数据 0:是-1:否 */
    private Byte sysFlag;

    public Long getRoleId(){
        return this.roleId;
    }
    public void setRoleId(Long roleId){
        this.roleId = roleId;
    }
	public Long getOfficeId(){
        return this.officeId;
    }
    public void setOfficeId(Long officeId){
        this.officeId = officeId;
    }
	public String getRoleName(){
        return this.roleName;
    }
    public void setRoleName(String roleName){
        this.roleName = roleName;
    }
	public String getRoleEnname(){
        return this.roleEnname;
    }
    public void setRoleEnname(String roleEnname){
        this.roleEnname = roleEnname;
    }
	public String getRoleType(){
        return this.roleType;
    }
    public void setRoleType(String roleType){
        this.roleType = roleType;
    }
	public Byte getSysFlag(){
        return this.sysFlag;
    }
    public void setSysFlag(Byte sysFlag){
        this.sysFlag = sysFlag;
    }

    @Override
    public Serializable getPK() {
        return this.roleId;
    }
}