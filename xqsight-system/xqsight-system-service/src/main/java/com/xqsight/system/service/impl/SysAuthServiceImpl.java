package com.xqsight.system.service.impl;

import com.xqsight.common.core.orm.*;
import com.xqsight.common.core.orm.builder.PropertyFilterBuilder;
import com.xqsight.system.mapper.SysAuthMapper;
import com.xqsight.system.mapper.SysLoginMapper;
import com.xqsight.system.mapper.SysMenuMapper;
import com.xqsight.system.mapper.SysRoleMapper;
import com.xqsight.system.model.SysLogin;
import com.xqsight.system.model.SysMenu;
import com.xqsight.system.model.SysRole;
import com.xqsight.system.service.SysAuthService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangganggang
 * @Description: TODO
 * @date 2016年1月8日 上午9:24:31
 */
@Service
@Transactional
public class SysAuthServiceImpl implements SysAuthService {

    @Autowired
    private SysAuthMapper sysAuthMapper;

    @Autowired
    private SysLoginMapper sysLoginMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;


    @Override
    public void saveUserRole(long roleId, Long... ids) {
        sysAuthMapper.deleUserRole(roleId);
        if (ids != null && !"".equals(ids)) {
            for (long id : ids) {
                sysAuthMapper.saveUserRole(id, roleId);
            }
        }
    }

    @Override
    public void saveMenuRole(long roleId, Long... menuIds) {
        sysAuthMapper.deleMenuRole(roleId);
        if (menuIds != null && !"".equals(menuIds)) {
            for (long menuId : menuIds) {
                sysAuthMapper.saveMenuRole(menuId, roleId);
            }
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<SysLogin> querAuthUserByRole(long roleId) {
        List<String> userIds = sysAuthMapper.queryUserIdByRole(roleId);
        StringBuffer userIdSb = new StringBuffer();
        userIds.stream().forEach(userId -> userIdSb.append(userId).append(","));

        List<PropertyFilter> propertyFilters = PropertyFilterBuilder.create().matchTye(MatchType.IN).propertyType(PropertyType.L)
                .add("id", StringUtils.substringBeforeLast(userIdSb.toString(), ",")).end();

        return sysLoginMapper.search(new Criterion(propertyFilters));
    }

    @Override
    @Transactional(readOnly = true)
    public List<SysMenu> querAuthMenuByRole(long roleId) {
        List<String> menuIds = sysAuthMapper.queryMenuIdByRole(roleId);
        if(menuIds.size() == 0) {
            return null;
        }

        StringBuffer menuIdSb = new StringBuffer();
        menuIds.stream().forEach(menuId ->  menuIdSb.append(menuId).append(",") );

        List<PropertyFilter> propertyFilters = PropertyFilterBuilder.create().matchTye(MatchType.IN).propertyType(PropertyType.L)
                .add("menu_id", StringUtils.substringBeforeLast(menuIdSb.toString(), ",")).end();

        return sysMenuMapper.search(new Criterion(propertyFilters));
    }

    @Override
    @Transactional(readOnly = true)
    public List<SysRole> queryRoleByUser(long userId) {
        List<String> roleIds = sysAuthMapper.queryRoleIdByuser(userId);

        StringBuffer roleIdSb = new StringBuffer();
        roleIds.stream().forEach(roleId ->  roleIdSb.append(roleId).append(",") );

        List<PropertyFilter> propertyFilters = PropertyFilterBuilder.create().matchTye(MatchType.IN).propertyType(PropertyType.L)
                .add("role_id", StringUtils.substringBeforeLast(roleIdSb.toString(), ",")).end();

        return sysRoleMapper.search(new Criterion(propertyFilters));
    }

    @Override
    @Transactional(readOnly = true)
    public List<SysMenu> queryMenuByUser(long userId, List<PropertyFilter> propertyFilters,List<Sort> sorts ) {
        List<String> menuIds = new ArrayList<>();
        List<String> roleIds = sysAuthMapper.queryRoleIdByuser(userId);

        roleIds.stream().forEach(roleId -> menuIds.addAll(sysAuthMapper.queryMenuIdByRole(Long.valueOf(roleId))));

        StringBuffer menuIdSb = new StringBuffer();
        menuIds.stream().forEach(menuId -> menuIdSb.append(menuId).append(",") );

        List<PropertyFilter> proFilters = PropertyFilterBuilder.create().matchTye(MatchType.IN).propertyType(PropertyType.L)
                .add("menu_id", StringUtils.substringBeforeLast(menuIdSb.toString(), ",")).end();

        if(propertyFilters == null) {
            propertyFilters = new ArrayList<>();
        }
        propertyFilters.addAll(proFilters);
        return sysMenuMapper.search(new Criterion(propertyFilters,sorts));
    }


}
