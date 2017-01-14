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

import com.xqsight.system.model.SysStation;
import com.xqsight.system.service.SysStationService;

/**
 * <p>岗位信息表 controller</p>
 * <p>Table: sys_station - 岗位信息表</p>
 *
 * @author wangganggang
 * @since 2017-01-07 11:58:11
 */
@RestController
@RequestMapping("/sys/station/")
public class SysStationController {

    @Autowired
    private SysStationService sysStationService;

    @Autowired
    private SysDepartmentService sysDepartmentService;

    @RequestMapping("save")
    @RequiresPermissions("sys:station:save")
    public Object save(SysStation sysStation) {
        sysStationService.save(sysStation, true);
        return MessageSupport.successMsg("保存成功");
    }

    @RequestMapping("update")
    @RequiresPermissions("sys:station:update")
    public Object update(SysStation sysStation) {
        sysStationService.update(sysStation, true);
        return MessageSupport.successMsg("修改成功");
    }

    @RequestMapping("delete")
    @RequiresPermissions("sys:station:delete")
    public Object delete(Long stationId) {
        sysStationService.delete(stationId);
        return MessageSupport.successMsg("删除成功");
    }

    @RequestMapping("logicDel")
    @RequiresPermissions("sys:station:delete")
    public Object logicDel(Long stationId) {
        sysStationService.logicDel(stationId);
        return MessageSupport.successMsg("删除成功");
    }

    @RequestMapping("query")
    @RequiresPermissions("sys:station:query")
    public Object query(XqsightPage xqsightPage, Long departmentId, String stationName, String stationCode) {
        List<SysDepartment> sysDepartments = sysDepartmentService.querySubById(departmentId, null, null);
        String departmentIds = sysDepartments.stream()
                .map(SysDepartment::getDepartmentId).distinct()
                .map(x -> x.toString())
                .collect(Collectors.joining(","));
        Page page = XqsightPageHelper.startPageWithPageIndex(xqsightPage.getiDisplayStart(), xqsightPage.getiDisplayLength());
        List<PropertyFilter> propertyFilters = PropertyFilterBuilder.create().matchTye(MatchType.LIKE)
                .propertyType(PropertyType.S).add("station_name", StringUtils.trimToEmpty(stationName)).add("station_code", StringUtils.trimToEmpty(stationCode))
                .matchTye(MatchType.IN).propertyType(PropertyType.L).add("department_id", departmentIds).end();
        List<Sort> sorts = SortBuilder.create().addAsc("station_name").end();
        List<SysStation> sysStations = sysStationService.search(propertyFilters,sorts);
        xqsightPage.setTotalCount(page.getTotal());
        return MessageSupport.successDataTableMsg(xqsightPage, sysStations);
    }

    @RequestMapping("querybyid")
    @RequiresPermissions("sys:station:query")
    public Object queryById(Long stationId) {
        SysStation sysStation = sysStationService.get(stationId);
        return MessageSupport.successDataMsg(sysStation, "查询成功");
    }

    @RequestMapping("queryall")
    @RequiresPermissions("sys:station:query")
    public Object queryall() {
        List<SysStation> sysStations = sysStationService.search(null);
        return MessageSupport.successDataMsg(sysStations, "查询成功");
    }

}