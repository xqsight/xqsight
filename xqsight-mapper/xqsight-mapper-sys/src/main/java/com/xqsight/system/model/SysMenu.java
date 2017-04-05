/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.model;

import com.xqsight.common.model.BaseModel;
import lombok.Data;

import java.io.Serializable;


/**
 * <p>菜单信息表实体类</p>
 * <p>Table: sys_menu - --> SysMenu</p>
 * <p>菜单信息表</p>
 * @since 2017-04-05 05:16:59
 * @author wangganggang
 */
@Data
public class SysMenu extends BaseModel{

	/** 主键 */
    private Long menuId;

    /** parent_id - 父级id */
    private Long parentId;
    /** parent_ids - 所有上级 */
    private String parentIds;
    /** menu_name - 菜单名称 */
    private String menuName;
    /** target_type - 跳转类型 */
    private String targetType;
    /** is_head - 是否是导航栏 0是 -1 否 */
    private Byte isHead;
    /** url - 菜单访问URL */
    private String url;
    /** icon - 图标 */
    private String icon;
    /** type - 类型 0：菜单1：功能 */
    private Byte type;
    /** permission - 允许字符串 */
    private String permission;
    /** sort - 排序 */
    private Short sort;

    @Override
    public Serializable getPK() {
        return this.menuId;
    }
}