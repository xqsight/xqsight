package com.xqsight.system.service;

import com.xqsight.common.core.orm.Criterion;
import com.xqsight.common.core.orm.MatchType;
import com.xqsight.common.core.orm.PropertyFilter;
import com.xqsight.common.core.orm.PropertyType;
import com.xqsight.common.core.orm.builder.PropertyFilterBuilder;
import com.xqsight.system.mapper.SysAuthMapper;
import com.xqsight.system.mapper.SysLoginMapper;
import com.xqsight.system.mapper.SysMenuMapper;
import com.xqsight.system.mapper.SysRoleMapper;
import com.xqsight.system.model.SysLogin;
import com.xqsight.system.model.SysMenu;
import com.xqsight.system.model.SysRole;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangganggang
 * @Description: TODO
 * @date 2016年1月8日 上午9:24:31
 */
@Service
public class SysAuthService {

    @Autowired
    private SysAuthMapper sysAuthMapper;

    @Autowired
    private SysLoginMapper sysLoginMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;


    /**
     * <p>Title: saveUserRole</p>
     * <p>Description: </p>
     *
     * @param roleId
     * @param ids
     */
    public void saveUserRole(long roleId, Long... ids) {
        sysAuthMapper.deleUserRole(roleId);
        if (ids != null && !"".equals(ids)) {
            for (long id : ids) {
                sysAuthMapper.saveUserRole(id, roleId);
            }
        }
    }

    /**
     * <p>Title: saveMenuRole</p>
     * <p>Description: </p>
     *
     * @param roleId
     * @param menuIds
     */
    public void saveMenuRole(long roleId, Long... menuIds) {
        sysAuthMapper.deleMenuRole(roleId);
        if (menuIds != null && !"".equals(menuIds)) {
            for (long menuId : menuIds) {
                sysAuthMapper.saveMenuRole(menuId, roleId);
            }
        }
    }

    /**
     * 根据角色查询当前角色下的用户
     *
     * @param roleId
     * @return
     */
    public List<SysLogin> querAuthUserByRole(long roleId) {
        List<String> userIds = sysAuthMapper.queryUserIdByRole(roleId);
        StringBuffer userIdSb = new StringBuffer();
        userIds.stream().forEach(userId -> {
            userIdSb.append(userId).append(",");
        });
        List<PropertyFilter> propertyFilters = PropertyFilterBuilder.create().matchTye(MatchType.IN).propertyType(PropertyType.L)
                .add("id", StringUtils.substringBeforeLast(userIdSb.toString(), ",")).end();

        return sysLoginMapper.search(new Criterion(propertyFilters));
    }

    /**
     * 根据角色查询当前角色下分配的菜单
     *
     * @param roleId
     * @return
     */
    public List<SysMenu> querAuthMenuByRole(long roleId) {
        List<String> menuIds = sysAuthMapper.queryMenuIdByRole(roleId);
        StringBuffer menuIdSb = new StringBuffer();
        menuIds.stream().forEach(menuId -> {
            menuIdSb.append(menuId).append(",");
        });
        List<PropertyFilter> propertyFilters = PropertyFilterBuilder.create().matchTye(MatchType.IN).propertyType(PropertyType.L)
                .add("menu_id", StringUtils.substringBeforeLast(menuIdSb.toString(), ",")).end();

        return sysMenuMapper.search(new Criterion(propertyFilters));
    }

    /**
     * 根据用户查询当前用户所在的角色
     *
     * @param userId
     * @return
     */
    public List<SysRole> queryRoleByUser(long userId) {
        List<String> roleIds = sysAuthMapper.queryRoleIdByuser(userId);

        StringBuffer roleIdSb = new StringBuffer();
        roleIds.stream().forEach(roleId -> {
            roleIdSb.append(roleId).append(",");
        });
        List<PropertyFilter> propertyFilters = PropertyFilterBuilder.create().matchTye(MatchType.IN).propertyType(PropertyType.L)
                .add("role_id", StringUtils.substringBeforeLast(roleIdSb.toString(), ",")).end();

        return sysRoleMapper.search(new Criterion(propertyFilters));
    }

    /**
     * 根据用户查询当前用户分配的菜单
     *
     * @param userId
     * @return
     */
    public List<SysMenu> queryMenuByUser(long userId) {
        List<String> menuIds = new ArrayList<>();
        List<String> roleIds = sysAuthMapper.queryRoleIdByuser(userId);
        roleIds.stream().forEach(roleId -> {
            menuIds.addAll(sysAuthMapper.queryMenuIdByRole(Long.valueOf(roleId)));
        });
        StringBuffer menuIdSb = new StringBuffer();
        menuIds.stream().forEach(menuId -> {
            menuIdSb.append(menuId).append(",");
        });
        List<PropertyFilter> propertyFilters = PropertyFilterBuilder.create().matchTye(MatchType.IN).propertyType(PropertyType.L)
                .add("menu_id", StringUtils.substringBeforeLast(menuIdSb.toString(), ",")).end();

        return sysMenuMapper.search(new Criterion(propertyFilters));
    }
}
