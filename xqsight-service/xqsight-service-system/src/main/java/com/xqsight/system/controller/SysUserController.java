/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.controller;

import com.github.pagehelper.Page;
import com.xqsight.common.core.orm.MatchType;
import com.xqsight.common.core.orm.PropertyFilter;
import com.xqsight.common.core.orm.PropertyType;
import com.xqsight.common.core.orm.Sort;
import com.xqsight.common.core.orm.builder.PropertyFilterBuilder;
import com.xqsight.common.core.orm.builder.SortBuilder;
import com.xqsight.common.model.XqsightPage;
import com.xqsight.common.core.support.XqsightPageHelper;
import com.xqsight.common.support.MessageSupport;
import com.xqsight.sso.shiro.annotation.CurrentUserId;
import com.xqsight.system.model.SysDepartment;
import com.xqsight.system.service.SysDepartmentService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import com.xqsight.system.model.SysUser;
import com.xqsight.system.service.SysUserService;

/**
 * <p>用户信息表 controller</p>
 * <p>Table: sys_user - 用户信息表</p>
 *
 * @author wangganggang
 * @since 2017-01-07 11:58:17
 */
@RestController
@RequestMapping("/sys/user/")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysDepartmentService sysDepartmentService;

    @RequestMapping("save")
    @RequiresPermissions("sys:user:save")
    public Object save(SysUser sysUser) {
        sysUserService.save(sysUser, true);
        return MessageSupport.successMsg("保存成功");
    }

    @RequestMapping("update")
    @RequiresPermissions("sys:user:update")
    public Object update(SysUser sysUser) {
        sysUserService.update(sysUser, true);
        return MessageSupport.successMsg("修改成功");
    }

    @RequestMapping("delete")
    @RequiresPermissions("sys:user:delete")
    public Object delete(Long id) {
        sysUserService.delete(id);
        return MessageSupport.successMsg("删除成功");
    }

    @RequestMapping("logicDel")
    @RequiresPermissions("sys:user:delete")
    public Object logicDel(Long id) {
        sysUserService.logicDel(id);
        return MessageSupport.successMsg("删除成功");
    }

    @RequestMapping("query")
    @RequiresPermissions("sys:user:query")
    public Object query(XqsightPage xqsightPage, Long departmentId, String userCode, String userName, String email, String cellPhone) {
        List<PropertyFilter> deptFilters = PropertyFilterBuilder.create().matchTye(MatchType.LIKE)
                .propertyType(PropertyType.S).add("parent_ids", "," + departmentId + ",").end();
        List<SysDepartment> sysDepartments = sysDepartmentService.search(deptFilters);
        String departmentIds = sysDepartments.stream()
                .map(SysDepartment::getDepartmentId)
                .map(x -> x.toString())
                .collect(Collectors.joining(","));
        Page page = XqsightPageHelper.startPageWithPageIndex(xqsightPage.getiDisplayStart(), xqsightPage.getiDisplayLength());
        List<PropertyFilter> propertyFilters = PropertyFilterBuilder.create().matchTye(MatchType.LIKE)
                .propertyType(PropertyType.S).add("user_code", StringUtils.trimToEmpty(userCode))
                .propertyType(PropertyType.S).add("user_name", StringUtils.trimToEmpty(userName))
                .propertyType(PropertyType.S).add("email", StringUtils.trimToEmpty(email))
                .propertyType(PropertyType.S).add("cell_phone", StringUtils.trimToEmpty(cellPhone))
                .matchTye(MatchType.IN).propertyType(PropertyType.L).add("department_id", departmentIds).end();
        List<Sort> sorts = SortBuilder.create().addAsc("user_name").end();
        List<SysUser> sysUsers = sysUserService.search(propertyFilters, sorts);
        xqsightPage.setTotalCount(page.getTotal());
        return MessageSupport.successDataTableMsg(xqsightPage, sysUsers);
    }

    @RequestMapping("querybyid")
    @RequiresPermissions("sys:user:query")
    public Object queryById(Long id) {
        SysUser sysUser = sysUserService.get(id);
        return MessageSupport.successDataMsg(sysUser, "查询成功");
    }

    @RequestMapping("queryuserinfo")
    public Object queryUserInfo(@CurrentUserId Long id) {
        SysUser sysUser = sysUserService.get(id);
        return MessageSupport.successDataMsg(sysUser, "查询成功");
    }

}