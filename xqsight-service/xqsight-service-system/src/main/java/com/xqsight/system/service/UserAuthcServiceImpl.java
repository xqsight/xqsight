package com.xqsight.system.service;

import com.xqsight.authc.enums.LoginTypeEnum;
import com.xqsight.authc.support.LoginSupport;
import com.xqsight.common.core.orm.MatchType;
import com.xqsight.common.core.orm.PropertyFilter;
import com.xqsight.common.core.orm.PropertyType;
import com.xqsight.common.core.orm.builder.PropertyFilterBuilder;
import com.xqsight.common.model.shiro.BaseUserModel;
import com.xqsight.sso.authc.service.UserAuthcService;
import com.xqsight.sso.exceptions.CustomAuthcException;
import com.xqsight.system.model.SysLogin;
import com.xqsight.system.model.SysMenu;
import com.xqsight.system.model.SysUser;
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
    public BaseUserModel findByLoginId(String loginId) {
        List<PropertyFilter> propertyFilters = PropertyFilterBuilder.create().matchTye(MatchType.EQ)
                .propertyType(PropertyType.S).add("login_id", loginId).end();

        BaseUserModel userBaseModel = sysLoginService.search(propertyFilters).get(0);
        if (userBaseModel == null) {
            throw new UnknownAccountException("用户名没有找到");
        }
        if (userBaseModel.isUserLocked()) {
            throw new LockedAccountException("您的账户已锁定");
        }
        if (userBaseModel.isNoActive()) {
            throw new CustomAuthcException("你的账户未激活");
        }
        SysUser sysUser = sysUserService.get(userBaseModel.getId());

        userBaseModel.setUserName(sysUser.getUserName());
        userBaseModel.setPassword(sysUser.getPassword());
        userBaseModel.setSalt(sysUser.getSalt());
        return userBaseModel;
    }

    @Override
    public Set<String> findRoles(long id) {
        return sysAuthService.queryRoleByUser(id).stream().map(sysRole -> "" + sysRole.getRoleId()).collect(Collectors.toSet());
    }

    @Override
    public Set<String> findPermissions(long id) {
        return sysAuthService.queryMenuByUser(id, null, null).stream().map(SysMenu::getPermission).collect(Collectors.toSet());
    }

    @Override
    public void saveUser(BaseUserModel userBaseModel) {
        SysLogin sysLogin = new SysLogin();
        sysLogin.setLoginId(userBaseModel.getLoginId());
        sysLogin.setPassword(userBaseModel.getPassword());
        sysLogin.setLoginType(userBaseModel.getLoginType());
        sysLogin.setUserName(userBaseModel.getUserName());
        sysLogin.setSalt(userBaseModel.getSalt());

        saveSysUser(userBaseModel);

        sysLoginService.save(sysLogin, true);
    }

    private void saveSysUser(BaseUserModel userBaseModel) {
        List<PropertyFilter> propertyFilters = PropertyFilterBuilder.create().matchTye(MatchType.EQ)
                .propertyType(PropertyType.S).add("department_code", userBaseModel.getDepartmentCode()).end();
      /*  SysDepartment sysDepartment = sysDepartmentService.search(propertyFilters).get(0);
        if (sysDepartment == null)
            throw new CustomAuthcException("部门编号不存在");*/

        SysUser sysUser = new SysUser();
        sysUser.setUserName(userBaseModel.getUserName());
        //sysUser.setDepartmentId(sysDepartment.getDepartmentId());
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
        sysUserService.save(sysUser, true);
        userBaseModel.setId(sysUser.getId());
    }

}
