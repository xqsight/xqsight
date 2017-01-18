/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.model;

import com.xqsight.common.model.TreeBaseModel;

import java.io.Serializable;


/**
 * <p>菜单信息表实体类</p>
 * <p>Table: sys_menu - --> SysMenu</p>
 * <p>菜单信息表</p>
 * @since 2017-01-07 11:57:32
 * @author wangganggang
 */
public class SysMenu extends TreeBaseModel<SysMenu> {

	/** 主键 */
    private Long menuId;

    /** parent_ids - 所有上级 */
    private String parentIds;
    /** menu_name - 菜单名称 */
    private String menuName;
    /** url - 菜单访问URL */
    private String url;
    /** icon - 图标 */
    private String icon;
    /** type - 类型 0：菜单1：功能 */
    private Integer type;
    /** permission - 允许字符串 */
    private String permission;
    /** sort - 排序 */
    private Integer sort;

    public Long getMenuId(){
        return this.menuId;
    }
    public void setMenuId(Long menuId){
        this.menuId = menuId;
        super.setId("" + menuId);
    }
	public String getParentIds(){
        return this.parentIds;
    }
    public void setParentIds(String parentIds){
        this.parentIds = parentIds;
    }
	public String getMenuName(){
        return this.menuName;
    }
    public void setMenuName(String menuName){
        this.menuName = menuName;
        super.setName(menuName);
    }
	public String getUrl(){
        return this.url;
    }
    public void setUrl(String url){
        this.url = url;
    }
	public String getIcon(){
        return this.icon;
    }
    public void setIcon(String icon){
        this.icon = icon;
    }
	public Integer getType(){
        return this.type;
    }
    public void setType(Integer type){
        this.type = type;
    }
	public String getPermission(){
        return this.permission;
    }
    public void setPermission(String permission){
        this.permission = permission;
    }
	public Integer getSort(){
        return this.sort;
    }
    public void setSort(Integer sort){
        this.sort = sort;
    }

    @Override
    public Serializable getPK() {
        return this.menuId;
    }
}