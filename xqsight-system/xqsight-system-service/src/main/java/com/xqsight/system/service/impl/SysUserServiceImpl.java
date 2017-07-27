/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.service.impl;

import com.xqsight.common.base.service.AbstractCrudService;
import com.xqsight.common.model.constants.Constants;
import com.xqsight.security.utils.PasswordHelper;
import com.xqsight.system.mapper.SysUserMapper;
import com.xqsight.system.model.SysLogin;
import com.xqsight.system.model.SysUser;
import com.xqsight.system.service.SysLoginService;
import com.xqsight.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>用户信息表实现类 service</p>
 * <p>Table: sys_user - 用户信息表</p>
 * @since 2017-04-05 05:17:20
 * @author wangganggang
 */
@Service
public class SysUserServiceImpl extends AbstractCrudService<SysUserMapper,SysUser, Long> implements SysUserService {

    @Autowired
    protected SysLoginService sysLoginService;

    @Override
    @Transactional
    public int add(SysUser record) {
        record.setPassword("!password");
        PasswordHelper.encryptPassword(record);
        super.add(record);
        saveSysLogin(record);
        return 1;
    }

    protected void saveSysLogin(SysUser sysUser){
        List<SysLogin> sysLogins = new ArrayList<>();
        SysLogin sysLogin = new SysLogin();
        sysLogin.setId(sysUser.getId());
        sysLogin.setLoginId(sysUser.getUserCode());
        sysLogin.setLocked(Constants.ACTIVE);
        sysLogin.setStatus((byte) 0);
        sysLogins.add(sysLogin);

        SysLogin sysLoginCell = new SysLogin();
        sysLoginCell.setId(sysUser.getId());
        sysLoginCell.setLoginId(sysUser.getCellPhone());
        sysLoginCell.setLocked(Constants.ACTIVE);
        sysLoginCell.setStatus((byte) 0);
        sysLogins.add(sysLoginCell);

        SysLogin sysLoginEmail = new SysLogin();
        sysLoginEmail.setId(sysUser.getId());
        sysLoginEmail.setLoginId(sysUser.getEmail());
        sysLoginEmail.setLocked(Constants.ACTIVE);
        sysLoginEmail.setStatus((byte) 0);
        sysLogins.add(sysLoginEmail);

        sysLoginService.batchAdd(sysLogins);
    }
}