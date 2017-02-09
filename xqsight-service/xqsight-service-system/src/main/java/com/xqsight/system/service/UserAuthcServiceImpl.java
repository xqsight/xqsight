package com.xqsight.system.service;

import com.xqsight.authc.enums.LoginTypeEnum;
import com.xqsight.authc.support.LoginSupport;
import com.xqsight.common.core.orm.MatchType;
import com.xqsight.common.core.orm.PropertyFilter;
import com.xqsight.common.core.orm.PropertyType;
import com.xqsight.common.core.orm.builder.PropertyFilterBuilder;
import com.xqsight.common.model.UserBaseModel;
import com.xqsight.sso.authc.service.UserAuthcService;
import com.xqsight.sso.exceptions.CustomAuthcException;
import com.xqsight.system.mapper.SysUserMapper;
import com.xqsight.system.model.*;
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
    private SysDepartmentService sysDepartmentService;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public UserBaseModel findByLoginId(String loginId) {
        List<PropertyFilter> propertyFilters = PropertyFilterBuilder.create().matchTye(MatchType.EQ)
                .propertyType(PropertyType.S).add("login_id", loginId).end();

        UserBaseModel userBaseModel = sysLoginService.search(propertyFilters).get(0);
        if (userBaseModel == null)
            throw new UnknownAccountException("用户名没有找到");

        if (userBaseModel.isUserLocked())
            throw new LockedAccountException("您的账户已锁定");

        if (userBaseModel.isNoActive())
            throw new CustomAuthcException("你的账户未激活");

        if (userBaseModel.getParentId() == 0)
            return userBaseModel;

        return sysLoginService.search(propertyFilters).get(0);
    }

    @Override
    public Set<String> findRoles(long id) {
        return sysAuthService.queryRoleByUser(id).stream().map(SysRole::getRoleCode).collect(Collectors.toSet());
    }

    @Override
    public Set<String> findPermissions(long id) {
        return sysAuthService.queryMenuByUser(id, null, null).stream().map(SysMenu::getPermission).collect(Collectors.toSet());
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

        saveSysUser(userBaseModel);
    }

    private void saveSysUser(UserBaseModel userBaseModel) {
        List<PropertyFilter> propertyFilters = PropertyFilterBuilder.create().matchTye(MatchType.EQ)
                .propertyType(PropertyType.S).add("department_code", userBaseModel.getDepartmentCode()).end();
        SysDepartment sysDepartment = sysDepartmentService.search(propertyFilters).get(0);
        if (sysDepartment == null)
            throw new CustomAuthcException("部门编号不存在");

        SysUser sysUser = new SysUser();
        sysUser.setId(userBaseModel.getId());
        sysUser.setUserName(userBaseModel.getUserName());
        sysUser.setDepartmentId(sysDepartment.getDepartmentId());
        LoginTypeEnum loginType = LoginSupport.judgeLoginType(userBaseModel.getLoginId());
        switch (loginType) {
            case CELLPHONE:
                sysUser.setCellPhone(userBaseModel.getLoginId());
                break;
            case EMAIL:
                sysUser.setEmail(userBaseModel.getLoginId());
                break;
            default:
                sysUser.setUserCode(userBaseModel.getLoginId());
        }
        sysUserMapper.insertSelective(sysUser);
    }

}
