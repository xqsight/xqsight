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
import com.xqsight.system.model.SysOffice;
import com.xqsight.system.service.SysOfficeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>机构表 controller</p>
 * <p>Table: sys_office - 机构表</p>
 *
 * @author wangganggang
 * @since 2017-02-22 04:29:58
 */
@RestController
@RequestMapping("/sys/office/")
public class SysOfficeController {

    @Autowired
    private SysOfficeService sysOfficeService;

    @RequestMapping("save")
    public Object save(SysOffice sysOffice) {
        SysOffice parentOffice = sysOfficeService.get(Long.valueOf(sysOffice.getParentId()));
        sysOffice.setParentIds(parentOffice.getParentIds() + parentOffice.getOfficeId() + Constants.COMMA_SIGN_SPLIT_NAME);

        sysOfficeService.save(sysOffice, true);
        return MessageSupport.successMsg("保存成功");
    }

    @RequestMapping("update")
    public Object update(SysOffice sysOffice) {
        sysOfficeService.update(sysOffice, true);
        return MessageSupport.successMsg("修改成功");
    }

    @RequestMapping("delete")
    public Object delete(Long officeId) {
        sysOfficeService.delete(officeId);
        return MessageSupport.successMsg("删除成功");
    }

    @RequestMapping("logicDel")
    public Object logicDel(Long officeId) {
        sysOfficeService.logicDel(officeId);
        return MessageSupport.successMsg("删除成功");
    }

    @RequestMapping("query")
    public Object query(String officeName, String parentId) {
        List<PropertyFilter> propertyFilters = PropertyFilterBuilder.create().matchTye(MatchType.LIKE)
                .propertyType(PropertyType.S).add("office_name", StringUtils.trimToEmpty(officeName))
                .matchTye(MatchType.EQ).propertyType(PropertyType.L)
                .add("parent_id", parentId).end();
        List<Sort> sorts = SortBuilder.create().addAsc("sort").addAsc("office_name").end();
        List<SysOffice> sysOffices = sysOfficeService.search(propertyFilters, sorts);
        return MessageSupport.successDataMsg(sysOffices, "查询成功");
    }

    @RequestMapping("querybyid")
    public Object queryById(Long officeId) {
        SysOffice sysOffice = sysOfficeService.get(officeId);
        return MessageSupport.successDataMsg(sysOffice, "查询成功");
    }

    @RequestMapping("queryall")
    public Object queryall() {
        List<SysOffice> sysOffices = sysOfficeService.search(null);
        return MessageSupport.successDataMsg(sysOffices, "查询成功");
    }

    @RequestMapping("querytree")
    public Object queryTree() {
        List<SysOffice> sysOffices = sysOfficeService.search(null);
        SysOffice sysOffice = new TreeSupport<SysOffice>().generateFullTree(sysOffices);
        return MessageSupport.successDataMsg(sysOffice, "查询成功");
    }

}