/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.controller.system;

import com.xqsight.common.base.controller.BaseTreeController;
import com.xqsight.common.core.orm.MatchType;
import com.xqsight.common.core.orm.PropertyFilter;
import com.xqsight.common.core.orm.PropertyType;
import com.xqsight.common.core.orm.Sort;
import com.xqsight.common.core.orm.builder.PropertyFilterBuilder;
import com.xqsight.common.core.orm.builder.SortBuilder;
import com.xqsight.common.model.BaseResult;
import com.xqsight.common.model.support.TreeSupport;
import com.xqsight.system.model.SysMenu;
import com.xqsight.system.service.SysMenuService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>菜单信息表 controller</p>
 * <p>Table: sys_menu - 菜单信息表</p>
 * @since 2017-04-05 05:21:26
 * @author wangganggang
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends BaseTreeController<SysMenuService,SysMenu,Long> {


    @RequestMapping(value = "/tree/{type}", method = RequestMethod.GET)
    public Object getTreeByType(@PathVariable String type) {
        List<PropertyFilter> propertyFilters = PropertyFilterBuilder.create().matchTye(MatchType.EQ)
                .propertyType(PropertyType.I).add("type", type).end();

        List<Sort> sorts = SortBuilder.create().addAsc("sort").end();
        List<SysMenu> sysMenus = service.getByFilters(propertyFilters, sorts);
        sysMenus = new TreeSupport<SysMenu>().generateTree(sysMenus);
        return new BaseResult(sysMenus);
    }
}