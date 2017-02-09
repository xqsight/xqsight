/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.controller;

import com.xqsight.common.core.orm.MatchType;
import com.xqsight.common.core.orm.PropertyFilter;
import com.xqsight.common.core.orm.PropertyType;
import com.xqsight.common.core.orm.Sort;
import com.xqsight.common.core.orm.builder.PropertyFilterBuilder;
import com.xqsight.common.core.orm.builder.SortBuilder;
import com.xqsight.common.model.constants.Constants;
import com.xqsight.common.support.MessageSupport;
import com.xqsight.common.support.TreeSupport;
import com.xqsight.sso.shiro.annotation.CurrentUserId;
import com.xqsight.system.model.SysRole;
import com.xqsight.system.service.SysRoleService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>角色信息表 controller</p>
 * <p>Table: sys_role - 角色信息表</p>
 *
 * @author wangganggang
 * @since 2017-01-07 11:58:03
 */
@RestController
@RequestMapping("/sys/role/")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @RequestMapping("save")
    @RequiresPermissions("sys:role:save")
    public Object save(SysRole sysRole) {
        SysRole parentRole = sysRoleService.get(Long.valueOf(sysRole.getParentId()));
        sysRole.setParentIds(parentRole.getParentIds() + parentRole.getRoleId() + Constants.COMMA_SIGN_SPLIT_NAME);
        sysRoleService.save(sysRole, true);
        return MessageSupport.successMsg("保存成功");
    }

    @RequestMapping("update")
    @RequiresPermissions("sys:role:update")
    public Object update(SysRole sysRole) {
        sysRoleService.update(sysRole, true);
        return MessageSupport.successMsg("修改成功");
    }

    @RequestMapping("delete")
    @RequiresPermissions("sys:role:delete")
    public Object delete(Long roleId) {
        sysRoleService.delete(roleId);
        return MessageSupport.successMsg("删除成功");
    }

    @RequestMapping("logicDel")
    @RequiresPermissions("sys:role:delete")
    public Object logicDel(Long roleId) {
        sysRoleService.logicDel(roleId);
        return MessageSupport.successMsg("删除成功");
    }

    @RequestMapping("query")
    @RequiresPermissions("sys:role:query")
    public Object query(String parentId, String roleName, String roleCode) {
        List<PropertyFilter> propertyFilters = PropertyFilterBuilder.create().matchTye(MatchType.LIKE)
                .propertyType(PropertyType.S).add("role_name", StringUtils.trimToEmpty(roleName))
                .add("role_code", StringUtils.trimToEmpty(roleCode)).matchTye(MatchType.EQ)
                .propertyType(PropertyType.L).add("parent_id", parentId).end();
        List<Sort> sorts = SortBuilder.create().addAsc("role_name").end();
        List<SysRole> sysRoles = sysRoleService.search(propertyFilters, sorts);
        return MessageSupport.successDataMsg(sysRoles, "查询成功");
    }

    @RequestMapping("querybyid")
    @RequiresPermissions("sys:role:query")
    public Object queryById(Long roleId) {
        SysRole sysRole = sysRoleService.get(roleId);
        return MessageSupport.successDataMsg(sysRole, "查询成功");
    }

    @RequestMapping("queryalltree")
    @RequiresPermissions("sys:department:query")
    public Object queryAllTotTree(String roleName, String roleCode,@CurrentUserId Long currentUserId) {
        List<PropertyFilter> propertyFilters = PropertyFilterBuilder.create().matchTye(MatchType.LIKE)
                .propertyType(PropertyType.S).add("role_name", StringUtils.trimToEmpty(roleName))
                .add("role_code", StringUtils.trimToEmpty(roleCode)).end();
        List<Sort> sorts = SortBuilder.create().addAsc("role_name").end();
        List<SysRole> sysRoles = sysRoleService.querySubByUserId(currentUserId, propertyFilters, sorts);
        SysRole sysRole = new TreeSupport<SysRole>().generateFullTree(sysRoles);
        return MessageSupport.successDataMsg(sysRole, "查询成功");
    }

}