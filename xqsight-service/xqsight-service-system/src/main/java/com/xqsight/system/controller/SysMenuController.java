/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.controller;

import com.xqsight.common.model.constants.Constants;
import com.xqsight.common.orm.MatchType;
import com.xqsight.common.orm.PropertyFilter;
import com.xqsight.common.orm.PropertyType;
import com.xqsight.common.orm.Sort;
import com.xqsight.common.orm.builder.PropertyFilterBuilder;
import com.xqsight.common.orm.builder.SortBuilder;
import com.xqsight.commons.support.MessageSupport;
import com.xqsight.commons.support.TreeSupport;
import com.xqsight.system.enums.MenuTypeEnum;
import com.xqsight.system.model.SysMenu;
import com.xqsight.system.service.SysMenuService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>菜单信息表 controller</p>
 * <p>Table: sys_menu - 菜单信息表</p>
 *
 * @author wangganggang
 * @since 2017-01-07 11:57:32
 */
@RestController
@RequestMapping("/sys/menu/")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @RequestMapping("save")
    @RequiresPermissions("sys:menu:save")
    public Object save(SysMenu sysMenu) {
        SysMenu parentMenu = sysMenuService.get(Long.valueOf(sysMenu.getParentId()));
        sysMenu.setParentIds(parentMenu.getMenuId() + Constants.COMMA_SIGN_SPLIT_NAME + parentMenu.getParentIds());
        sysMenuService.save(sysMenu, true);
        return MessageSupport.successMsg("保存成功");
    }

    @RequestMapping("update")
    @RequiresPermissions("sys:menu:update")
    public Object update(SysMenu sysMenu) {
        sysMenuService.update(sysMenu, true);
        return MessageSupport.successMsg("修改成功");
    }

    @RequestMapping("delete")
    @RequiresPermissions("sys:menu:delete")
    public Object delete(Long menuId) {
        sysMenuService.delete(menuId);
        return MessageSupport.successMsg("删除成功");
    }

    @RequestMapping("logicDel")
    @RequiresPermissions("sys:menu:delete")
    public Object logicDel(Long menuId) {
        sysMenuService.logicDel(menuId);
        return MessageSupport.successMsg("删除成功");
    }

    @RequestMapping("query")
    @RequiresPermissions("sys:menu:query")
    public Object query(String menuName, String parentId) {
        List<PropertyFilter> propertyFilters = PropertyFilterBuilder.create().matchTye(MatchType.LIKE)
                .propertyType(PropertyType.S).add("menu_name", menuName)
                .matchTye(MatchType.EQ).propertyType(PropertyType.L)
                .add("parent_id", parentId).end();
        List<Sort> sorts = SortBuilder.create().addAsc("sort").addAsc("menu_name").end();
        List<SysMenu> sysMenus = sysMenuService.search(propertyFilters, sorts);
        return MessageSupport.successDataMsg(sysMenus, "查询成功");
    }

    @RequestMapping("querybyid")
    @RequiresPermissions("sys:menu:query")
    public Object queryById(Long menuId) {
        SysMenu sysMenu = sysMenuService.get(menuId);
        return MessageSupport.successDataMsg(sysMenu, "查询成功");
    }


    @RequestMapping("querytree")
    @RequiresPermissions("sys:menu:query")
    public Object queryToTree(Long currentUserId) {
        List<PropertyFilter> propertyFilters = PropertyFilterBuilder.create().matchTye(MatchType.LIKE)
                .propertyType(PropertyType.I).add("type", "" + MenuTypeEnum.MENU.getValuel()).end();
        List<Sort> sorts = SortBuilder.create().addAsc("sort").end();
        List<SysMenu> sysMenus = sysMenuService.querySubByUserId(currentUserId,propertyFilters, sorts);
        SysMenu sysMenu = new TreeSupport<SysMenu>().generateFullTree(sysMenus);
        return MessageSupport.successDataMsg(sysMenu, "查询成功");
    }

    @RequestMapping("queryalltree")
    @RequiresPermissions("sys:menu:query")
    public Object queryAllToTree(Long currentUserId) {
        List<Sort> sorts = SortBuilder.create().addAsc("sort").end();
        List<SysMenu> sysMenus = sysMenuService.querySubByUserId(currentUserId,null, sorts);
        SysMenu sysMenu = new TreeSupport<SysMenu>().generateFullTree(sysMenus);
        return MessageSupport.successDataMsg(sysMenu, "查询成功");
    }
}