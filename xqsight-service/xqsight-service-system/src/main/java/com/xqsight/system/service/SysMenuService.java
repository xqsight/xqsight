/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.service;

import com.xqsight.common.core.dao.Dao;
import com.xqsight.common.core.orm.*;
import com.xqsight.common.core.orm.builder.PropertyFilterBuilder;
import com.xqsight.common.core.service.DefaultEntityService;
import com.xqsight.system.mapper.SysAuthMapper;
import com.xqsight.system.mapper.SysMenuMapper;
import com.xqsight.system.model.SysMenu;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>菜单信息表实现类service</p>
 * <p>Table: sys_menu - 菜单信息表</p>
 *
 * @author wangganggang
 * @since 2017-01-07 11:57:32
 */
@Service
public class SysMenuService extends DefaultEntityService<SysMenu, Long> {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysAuthMapper sysAuthMapper;

    @Override
    protected Dao<SysMenu, Long> getDao() {
        return sysMenuMapper;
    }

    /**
     *查询当前用户的所有菜单
     * @param userId
     * @param propertyFilters
     * @param sorts
     * @return
     */
    public List<SysMenu> querySubByUserId(Long userId, List<PropertyFilter> propertyFilters, List<Sort> sorts) {
        List<String> roleIds = sysAuthMapper.queryRoleIdByuser(userId);
        List<String> menuIds = new ArrayList<>();
        roleIds.stream().forEach(roleId -> {
            menuIds.addAll(sysAuthMapper.queryMenuIdByRole(Long.valueOf(roleId)));
        });

        StringBuffer menuIdSb = new StringBuffer();
        menuIds.stream().forEach(menuId -> {
            menuIdSb.append(menuId).append(",");
        });
        List<PropertyFilter> propertyFilterList = PropertyFilterBuilder.create().matchTye(MatchType.IN).propertyType(PropertyType.L)
                .add("menu_id", StringUtils.substringBeforeLast(menuIdSb.toString(), ",")).end();
        propertyFilterList.addAll(propertyFilters);
        return sysMenuMapper.search(new Criterion(propertyFilterList,sorts));
    }
}