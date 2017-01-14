/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.controller;

import com.xqsight.common.model.constants.Constants;
import com.xqsight.common.core.orm.MatchType;
import com.xqsight.common.core.orm.PropertyFilter;
import com.xqsight.common.core.orm.PropertyType;
import com.xqsight.common.core.orm.Sort;
import com.xqsight.common.core.orm.builder.PropertyFilterBuilder;
import com.xqsight.common.core.orm.builder.SortBuilder;
import com.xqsight.common.support.MessageSupport;
import com.xqsight.common.support.TreeSupport;
import com.xqsight.system.model.SysDepartment;
import com.xqsight.system.model.SysLogin;
import com.xqsight.system.service.SysDepartmentService;
import com.xqsight.system.service.SysLoginService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>部门表 controller</p>
 * <p>Table: sys_department - 部门表</p>
 *
 * @author wangganggang
 * @since 2017-01-07 11:57:06
 */
@RestController
@RequestMapping("/sys/department/")
public class SysDepartmentController {

    @Autowired
    private SysDepartmentService sysDepartmentService;

    @Autowired
    private SysLoginService sysLoginService;

    @RequestMapping("save")
    @RequiresPermissions("sys:department:save")
    public Object save(SysDepartment sysDepartment) {
        List<PropertyFilter> propertyFilters = PropertyFilterBuilder.create().matchTye(MatchType.EQ)
                .propertyType(PropertyType.S).add("department_code", sysDepartment.getDepartmentCode()).end();
        List<SysDepartment> sysDepartments = sysDepartmentService.search(propertyFilters);
        if (sysDepartments != null || sysDepartments.size() > 0)
            return MessageSupport.failureMsg("部门编号[" + sysDepartment.getDepartmentCode() + "]已经存在,请重新修改");
        SysDepartment parentDep = sysDepartmentService.get(Long.valueOf(sysDepartment.getParentId()));
        sysDepartment.setParentIds(parentDep.getDepartmentId() + Constants.COMMA_SIGN_SPLIT_NAME + parentDep.getParentIds());
        sysDepartmentService.save(sysDepartment, true);
        return MessageSupport.successMsg("保存成功");
    }

    @RequestMapping("update")
    @RequiresPermissions("sys:department:update")
    public Object update(SysDepartment sysDepartment) {
        sysDepartmentService.update(sysDepartment, true);
        return MessageSupport.successMsg("修改成功");
    }

    @RequestMapping("delete")
    @RequiresPermissions("sys:department:delete")
    public Object delete(Long departmentId) {
        List<PropertyFilter> propertyFilters = PropertyFilterBuilder.create().matchTye(MatchType.EQ)
                .propertyType(PropertyType.L).add("parent_id", "" + departmentId).end();
        List<SysDepartment> sysDepartments = sysDepartmentService.search(propertyFilters);
        if (sysDepartments != null || sysDepartments.size() > 0)
            return MessageSupport.failureMsg("该部门还有下级部门不可删除");
        sysDepartmentService.delete(departmentId);
        return MessageSupport.successMsg("删除成功");
    }

    @RequestMapping("logicDel")
    @RequiresPermissions("sys:department:delete")
    public Object logicDel(Long departmentId) {
        sysDepartmentService.logicDel(departmentId);
        return MessageSupport.successMsg("删除成功");
    }

    @RequestMapping("query")
    @RequiresPermissions("sys:department:query")
    public Object query(String departmentName, String departmentCode, String customCode, String parentId) {
        List<PropertyFilter> propertyFilters = PropertyFilterBuilder.create().matchTye(MatchType.LIKE)
                .propertyType(PropertyType.S).add("department_name", StringUtils.trimToEmpty(departmentName))
                .add("department_code", StringUtils.trimToEmpty(departmentCode)).add("custom_code", StringUtils.trimToEmpty(customCode))
                .matchTye(MatchType.EQ).propertyType(PropertyType.L).add("parent_id", parentId).end();
        List<Sort> sorts = SortBuilder.create().addAsc("sort").addAsc("department_name").end();
        List<SysDepartment> sysDepartments = sysDepartmentService.search(propertyFilters, sorts);
        return MessageSupport.successDataMsg(sysDepartments, "查询成功");
    }

    @RequestMapping("querybyid")
    @RequiresPermissions("sys:department:query")
    public Object queryById(Long departmentId) {
        SysDepartment sysDepartment = sysDepartmentService.get(departmentId);
        return MessageSupport.successDataMsg(sysDepartment, "查询成功");
    }

    @RequestMapping("queryalltree")
    @RequiresPermissions("sys:department:query")
    public Object queryAllTotTree(String departmentName, String departmentCode, Long currentUserId) {
        SysLogin sysLogin = sysLoginService.get(currentUserId);
        List<PropertyFilter> propertyFilters = PropertyFilterBuilder.create().matchTye(MatchType.LIKE)
                .propertyType(PropertyType.S).add("department_name", StringUtils.trimToEmpty(departmentName))
                .add("department_code", StringUtils.trimToEmpty(departmentCode)).end();
        List<Sort> sorts = SortBuilder.create().addAsc("sort").end();
        List<SysDepartment> sysDepartments = sysDepartmentService.querySubById(sysLogin.getDepartmentId(), propertyFilters, sorts);
        SysDepartment sysDepartment = new TreeSupport<SysDepartment>().generateFullTree(sysDepartments);
        return MessageSupport.successDataMsg(sysDepartment, "查询成功");
    }

}