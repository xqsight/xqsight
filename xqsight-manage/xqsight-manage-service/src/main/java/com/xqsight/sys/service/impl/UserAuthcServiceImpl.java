package com.xqsight.sys.service.impl;

import com.xqsight.common.model.UserBaseModel;
import com.xqsight.sso.authc.service.UserAuthcService;
import com.xqsight.sys.model.SysLogin;
import com.xqsight.sys.model.SysMenu;
import com.xqsight.sys.service.SysAuthService;
import com.xqsight.sys.service.SysMenuService;
import com.xqsight.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by wangganggang on 2017/1/14.
 */
@Service
public class UserAuthcServiceImpl implements UserAuthcService {
    @Autowired
    private SysAuthService sysAuthService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysMenuService sysMenuService;

    @Override
    public void correlationRoles(long id, Long... roleIds) {

    }

    @Override
    public void uncorrelationRoles(long id, Long... roleIds) {

    }

    @Override
    public UserBaseModel findByLoginId(String loginId) {
        return sysUserService.querySingleUserByLoginId(loginId);
    }

    @Override
    public Set<String> findRoles(long id) {
        return null;
    }

    @Override
    public Set<String> findPermissions(long id) {
        return sysMenuService.querySysMenuByUser(id).stream().map(SysMenu::getPermission).collect(Collectors.toSet());
    }

    @Override
    public void saveUser(UserBaseModel userBaseModel) {
        SysLogin sysLogin = new SysLogin();
        sysLogin.setLoginId(userBaseModel.getLoginId());
        sysLogin.setPassword(userBaseModel.getPassword());
        sysLogin.setLoginType(userBaseModel.getLoginType());
        sysLogin.setUserName(userBaseModel.getUserName());
        sysLogin.setSalt(userBaseModel.getSalt());
        sysUserService.saveSysLogin(sysLogin);
    }
}
