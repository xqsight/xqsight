package com.xqsight.system.service;

import com.xqsight.common.core.orm.*;
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
public interface SysAuthService {


    /**
     * <p>Title: saveUserRole</p>
     * <p>Description: </p>
     *
     * @param roleId
     * @param ids
     */
    void saveUserRole(long roleId, Long... ids);

    /**
     * <p>Title: saveMenuRole</p>
     * <p>Description: </p>
     *
     * @param roleId
     * @param menuIds
     */
    void saveMenuRole(long roleId, Long... menuIds);

    /**
     * 根据角色查询当前角色下的用户
     *
     * @param roleId
     * @return
     */
    List<SysLogin> querAuthUserByRole(long roleId);

    /**
     * 根据角色查询当前角色下分配的菜单
     *
     * @param roleId
     * @return
     */
    List<SysMenu> querAuthMenuByRole(long roleId);

    /**
     * 根据用户查询当前用户所在的角色
     *
     * @param userId
     * @return
     */
    List<SysRole> queryRoleByUser(long userId);

    /**
     * 根据用户查询当前用户分配的菜单
     *
     * @param userId
     * @return
     */
    List<SysMenu> queryMenuByUser(long userId, List<PropertyFilter> propertyFilters,List<Sort> sorts );
}
