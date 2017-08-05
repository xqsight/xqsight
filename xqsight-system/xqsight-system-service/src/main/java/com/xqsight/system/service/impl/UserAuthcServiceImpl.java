package com.xqsight.system.service.impl;

import com.xqsight.common.core.orm.MatchType;
import com.xqsight.common.core.orm.PropertyFilter;
import com.xqsight.common.core.orm.PropertyType;
import com.xqsight.common.core.orm.builder.PropertyFilterBuilder;
import com.xqsight.common.exception.ParamsException;
import com.xqsight.common.exception.constants.ErrorMessageConstants;
import com.xqsight.common.model.shiro.BaseUserModel;
import com.xqsight.common.model.shiro.UserToken;
import com.xqsight.security.service.UserAuthcService;
import com.xqsight.system.model.SysLogin;
import com.xqsight.system.model.SysMenu;
import com.xqsight.system.model.SysUser;
import com.xqsight.system.service.SysAuthService;
import com.xqsight.system.service.SysLoginService;
import com.xqsight.system.service.SysOfficeService;
import com.xqsight.system.service.SysUserService;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by wangganggang on 2017/1/9.
 */
@Service
public class UserAuthcServiceImpl implements UserAuthcService {

    @Autowired
    private SysAuthService sysAuthService;

    @Autowired
    private SysLoginService sysLoginService;

    @Autowired
    private SysOfficeService sysOfficeService;

    @Autowired
    private SysUserService sysUserService;

    @Override
    public BaseUserModel queryUserById(Long loginId) {
        List<PropertyFilter> propertyFilters = PropertyFilterBuilder.create().matchTye(MatchType.EQ)
                .propertyType(PropertyType.S).add("login_id", String.valueOf(loginId)).end();

        BaseUserModel userBaseModel = sysLoginService.getOneByFilters(propertyFilters);

        if (userBaseModel == null) {
            throw new UnknownAccountException("用户名没有找到");
        }
        if (userBaseModel.isUserLocked()) {
            throw new LockedAccountException("您的账户已锁定");
        }
        if (!userBaseModel.isActived()) {
            throw new ParamsException(ErrorMessageConstants.ERROR_10000,"你的账户未激活");
        }
        SysUser sysUser = sysUserService.getById(userBaseModel.getId());

        userBaseModel.setUserName(sysUser.getUserName());
        userBaseModel.setPassword(sysUser.getPassword());
        userBaseModel.setSalt(sysUser.getSalt());
        return userBaseModel;
    }

    @Override
    public Set<String> findRoles(Long id) {
        return sysAuthService.queryRoleByUser(id).stream().map(sysRole -> "" + sysRole.getRoleId()).collect(Collectors.toSet());
    }

    @Override
    public Set<String> findPermissions(Long id) {
        return sysAuthService.queryMenuByUser(id, null, null).stream().map(SysMenu::getPermission).collect(Collectors.toSet());
    }

    @Override
    public BaseUserModel queryUserByLoginId(String loginId) {
        return null;
    }

    @Override
    public UserToken queryByToken(String token) {
        return null;
    }

    @Override
    public void logout(Long userId) {

    }
}
