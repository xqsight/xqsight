/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.controller.system;

import com.xqsight.common.core.orm.MatchType;
import com.xqsight.common.core.orm.PropertyFilter;
import com.xqsight.common.core.orm.PropertyType;
import com.xqsight.common.core.orm.Sort;
import com.xqsight.common.core.orm.builder.PropertyFilterBuilder;
import com.xqsight.common.core.orm.builder.SortBuilder;
import com.xqsight.common.model.constants.Constants;
import com.xqsight.common.support.MessageSupport;
import com.xqsight.common.support.TreeSupport;
import com.xqsight.system.model.SysArea;
import com.xqsight.system.service.SysAreaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>区域表 controller</p>
 * <p>Table: sys_area - 区域表</p>
 *
 * @author wangganggang
 * @since 2017-02-22 04:29:28
 */
@RestController
@RequestMapping("/sys/area/")
public class SysAreaController {

    @Autowired
    private SysAreaService sysAreaService;

    @RequestMapping("save")
    public Object save(SysArea sysArea) {
        if(StringUtils.equals(sysArea.getParentId(),"0")){
            sysArea.setParentIds(",0,");
        }else{
            SysArea parentArea = sysAreaService.get(Long.valueOf(sysArea.getParentId()));
            sysArea.setParentIds(parentArea.getParentIds() + parentArea.getAreaId() + Constants.COMMA_SIGN_SPLIT_NAME);
        }

        sysAreaService.save(sysArea, true);
        return MessageSupport.successMsg("保存成功");
    }

    @RequestMapping("update")
    public Object update(SysArea sysArea) {
        sysAreaService.update(sysArea, true);
        return MessageSupport.successMsg("修改成功");
    }

    @RequestMapping("delete")
    public Object delete(Long areaId) {
        sysAreaService.delete(areaId);
        return MessageSupport.successMsg("删除成功");
    }

    @RequestMapping("logicDel")
    public Object logicDel(Long areaId) {
        sysAreaService.logicDel(areaId);
        return MessageSupport.successMsg("删除成功");
    }

    @RequestMapping("query")
    public Object query(String areaName, String parentId) {
        List<PropertyFilter> propertyFilters = PropertyFilterBuilder.create().matchTye(MatchType.LIKE)
                .propertyType(PropertyType.S).add("area_name", StringUtils.trimToEmpty(areaName))
                .matchTye(MatchType.EQ).propertyType(PropertyType.L)
                .add("parent_id", parentId).end();
        List<Sort> sorts = SortBuilder.create().addAsc("sort").addAsc("area_name").end();
        List<SysArea> sysAreas = sysAreaService.search(propertyFilters,sorts);
        return MessageSupport.successDataMsg(sysAreas, "查询成功");
    }

    @RequestMapping("querybyid")
    public Object queryById(Long areaId) {
        SysArea sysArea = sysAreaService.get(areaId);
        return MessageSupport.successDataMsg(sysArea, "查询成功");
    }

    @RequestMapping("queryall")
    public Object queryall() {
        List<SysArea> sysAreas = sysAreaService.search(null);
        return MessageSupport.successDataMsg(sysAreas, "查询成功");
    }

    @RequestMapping("querytree")
    public Object queryTree() {
        List<SysArea> sysAreas = sysAreaService.search(null);
        sysAreas = new TreeSupport<SysArea>().generateTree(sysAreas);
        return MessageSupport.successDataMsg(sysAreas, "查询成功");
    }

}