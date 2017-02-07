package com.xqsight.system.service;

import com.xqsight.common.model.UserBaseModel;
import com.xqsight.common.core.orm.MatchType;
import com.xqsight.common.core.orm.PropertyFilter;
import com.xqsight.common.core.orm.PropertyType;
import com.xqsight.common.core.orm.builder.PropertyFilterBuilder;
import com.xqsight.sso.authc.service.UserAuthcService;
import com.xqsight.system.model.SysLogin;
import com.xqsight.system.model.SysMenu;
import com.xqsight.system.model.SysRole;
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

    @Override
    public void correlationRoles(long id, Long... roleIds) {

    }

    @Override
    public void uncorrelationRoles(long id, Long... roleIds) {

    }

    @Override
    public UserBaseModel findByLoginId(String loginId) {
        List<PropertyFilter> propertyFilters = PropertyFilterBuilder.create().matchTye(MatchType.EQ)
                .propertyType(PropertyType.S).add("login_id", loginId).end();

        UserBaseModel userBaseModel = sysLoginService.search(propertyFilters).get(0);
        if (userBaseModel.getParentId() == 0)
            return userBaseModel;

        return sysLoginService.get(userBaseModel.getId());
    }

    @Override
    public Set<String> findRoles(long id) {
        return sysAuthService.queryRoleByUser(id).stream().map(SysRole::getRoleCode).collect(Collectors.toSet());
    }

    @Override
    public Set<String> findPermissions(long id) {
        return sysAuthService.queryMenuByUser(id).stream().map(SysMenu::getPermission).collect(Collectors.toSet());
    }

    @Override
    public void saveUser(UserBaseModel userBaseModel) {
        SysLogin sysLogin = new SysLogin();
        sysLogin.setLoginId(userBaseModel.getLoginId());
        sysLogin.setPassword(userBaseModel.getPassword());
        sysLogin.setLoginType(userBaseModel.getLoginType());
        sysLogin.setUserName(userBaseModel.getUserName());
        sysLogin.setSalt(userBaseModel.getSalt());
        sysLoginService.save(sysLogin, true);
    }
}
