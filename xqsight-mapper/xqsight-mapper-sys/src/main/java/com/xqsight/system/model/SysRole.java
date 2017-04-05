/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.model;

import com.xqsight.common.model.BaseModel;
import lombok.Data;

import java.io.Serializable;


/**
 * <p>角色信息表实体类</p>
 * <p>Table: sys_role - --> SysRole</p>
 * <p>角色信息表</p>
 * @since 2017-04-05 05:17:09
 * @author wangganggang
 */
@Data
public class SysRole extends BaseModel{

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

    @Override
    public Serializable getPK() {
        return this.roleId;
    }
}