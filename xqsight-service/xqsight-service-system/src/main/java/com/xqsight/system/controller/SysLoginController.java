/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.controller;

import com.github.pagehelper.Page;
import com.xqsight.common.model.XqsightPage;
import com.xqsight.common.core.orm.MatchType;
import com.xqsight.common.core.orm.PropertyFilter;
import com.xqsight.common.core.orm.PropertyType;
import com.xqsight.common.core.orm.Sort;
import com.xqsight.common.core.orm.builder.PropertyFilterBuilder;
import com.xqsight.common.core.orm.builder.SortBuilder;
import com.xqsight.common.core.support.XqsightPageHelper;
import com.xqsight.common.support.MessageSupport;
import com.xqsight.system.model.SysDepartment;
import com.xqsight.system.service.SysDepartmentService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import com.xqsight.system.model.SysLogin;
import com.xqsight.system.service.SysLoginService;

/**
 * <p>用户登陆表 controller</p>
 * <p>Table: sys_login - 用户登陆表</p>
 *
 * @author wangganggang
 * @since 2017-01-07 11:57:26
 */
@RestController
@RequestMapping("/sys/login/")
public class SysLoginController {

    @Autowired
    private SysLoginService sysLoginService;

    @Autowired
    private SysDepartmentService sysDepartmentService;

    @RequestMapping("save")
    @RequiresPermissions("sys:login:save")
    public Object save(SysLogin sysLogin) {
        sysLoginService.save(sysLogin, true);
        return MessageSupport.successMsg("保存成功");
    }

    @RequestMapping("update")
    @RequiresPermissions("sys:login:update")
    public Object update(SysLogin sysLogin) {
        sysLoginService.update(sysLogin, true);
        return MessageSupport.successMsg("修改成功");
    }

    @RequestMapping("delete")
    @RequiresPermissions("sys:login:delete")
    public Object delete(Long id) {
        sysLoginService.delete(id);
        return MessageSupport.successMsg("删除成功");
    }

    @RequestMapping("logicDel")
    @RequiresPermissions("sys:login:delete")
    public Object logicDel(Long id) {
        sysLoginService.logicDel(id);
        return MessageSupport.successMsg("删除成功");
    }

    @RequestMapping("query")
    @RequiresPermissions("sys:login:query")
    public Object query(XqsightPage xqsightPage, Long departmentId, String loginId, String userName) {
        List<SysDepartment> sysDepartments = sysDepartmentService.querySubById(departmentId, null, null);
        String departmentIds = sysDepartments.stream()
                .map(SysDepartment::getDepartmentId)
                .map(x -> x.toString())
                .collect(Collectors.joining(","));
        Page page = XqsightPageHelper.startPageWithPageIndex(xqsightPage.getiDisplayStart(), xqsightPage.getiDisplayLength());
        List<PropertyFilter> propertyFilters = PropertyFilterBuilder.create().matchTye(MatchType.LIKE)
                .propertyType(PropertyType.S).add("login_id", StringUtils.trimToEmpty(loginId)).add("user_name", StringUtils.trimToEmpty(userName))
                .matchTye(MatchType.IN).propertyType(PropertyType.L).add("department_id", departmentIds).end();
        List<Sort> sorts = SortBuilder.create().addAsc("user_name").end();
        List<SysLogin> sysLogins = sysLoginService.search(propertyFilters, sorts);
        xqsightPage.setTotalCount(page.getTotal());
        return MessageSupport.successDataTableMsg(xqsightPage, sysLogins);
    }

    @RequestMapping("querybyid")
    @RequiresPermissions("sys:login:query")
    public Object queryById(Long id) {
        SysLogin sysLogin = sysLoginService.get(id);
        return MessageSupport.successDataMsg(sysLogin, "查询成功");
    }

    @RequestMapping("queryall")
    @RequiresPermissions("sys:login:query")
    public Object queryall() {
        List<SysLogin> sysLogins = sysLoginService.search(null);
        return MessageSupport.successDataMsg(sysLogins, "查询成功");
    }

}