/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.controller.system;

import com.github.pagehelper.Page;
import com.xqsight.common.core.orm.MatchType;
import com.xqsight.common.core.orm.PropertyFilter;
import com.xqsight.common.core.orm.PropertyType;
import com.xqsight.common.core.orm.Sort;
import com.xqsight.common.core.orm.builder.PropertyFilterBuilder;
import com.xqsight.common.core.orm.builder.SortBuilder;
import com.xqsight.common.core.support.XqsightPageHelper;
import com.xqsight.common.model.XqsightPage;
import com.xqsight.common.support.MessageSupport;
import com.xqsight.sso.shiro.annotation.CurrentUserId;
import com.xqsight.sso.utils.PasswordHelper;
import com.xqsight.sso.utils.SSOUtils;
import com.xqsight.system.model.SysLogin;
import com.xqsight.system.service.SysLoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>用户登陆表 controller</p>
 * <p>Table: sys_login - 用户登陆表</p>
 *
 * @author wangganggang
 * @since 2017-01-07 11:57:26
 */
@RestController
@RequestMapping("/sys/login/")
public class SysLoginController {

    @Autowired
    private SysLoginService sysLoginService;

    @RequestMapping("save")
    public Object save(SysLogin sysLogin) {
        sysLoginService.save(sysLogin, true);
        return MessageSupport.successMsg("保存成功");
    }

    @RequestMapping("update")
    public Object update(SysLogin sysLogin) {
        sysLoginService.update(sysLogin, true);
        return MessageSupport.successMsg("修改成功");
    }

    @RequestMapping("resetpwd")
    public Object resetPwd(long id) {
        SysLogin sysLogin = sysLoginService.get(id);
        sysLogin.setUpdateUserId(SSOUtils.getCurrentUserId().toString());
        sysLogin.setPassword("!password");
        PasswordHelper.encryptPassword(sysLogin);
        sysLoginService.update(sysLogin, true);
        return MessageSupport.successMsg("密码重置成功");
    }

    @RequestMapping("updpwd")
    public Object updPassword(@CurrentUserId long id, String oldPassword, String password) {
        SysLogin sysLogin = sysLoginService.get(id);
        if (sysLogin == null)
            return MessageSupport.failureMsg("当前用户不存在");

        if (!PasswordHelper.checkPassword(sysLogin, oldPassword))
            return MessageSupport.failureMsg("原始密码不正确");

        sysLogin.setPassword(password);
        PasswordHelper.encryptPassword(sysLogin);
        sysLoginService.update(sysLogin, true);
        return MessageSupport.successMsg("密码修改成功");
    }


    @RequestMapping("delete")
    public Object delete(Long id) {
        sysLoginService.delete(id);
        return MessageSupport.successMsg("删除成功");
    }

    @RequestMapping("logicDel")
    public Object logicDel(Long id) {
        sysLoginService.logicDel(id);
        return MessageSupport.successMsg("删除成功");
    }

    @RequestMapping("query")
    public Object query(XqsightPage xqsightPage, String loginId, String userName) {
        Page page = XqsightPageHelper.startPageWithPageIndex(xqsightPage.getiDisplayStart(), xqsightPage.getiDisplayLength());
        List<PropertyFilter> propertyFilters = PropertyFilterBuilder.create().matchTye(MatchType.LIKE)
                .propertyType(PropertyType.S).add("login_id", StringUtils.trimToEmpty(loginId))
                .add("user_name", StringUtils.trimToEmpty(userName)).end();
        List<Sort> sorts = SortBuilder.create().addAsc("user_name").end();
        List<SysLogin> sysLogins = sysLoginService.search(propertyFilters, sorts);
        xqsightPage.setTotalCount(page.getTotal());
        return MessageSupport.successDataTableMsg(xqsightPage, sysLogins);
    }

    @RequestMapping("querybyid")
    public Object queryById(Long id) {
        SysLogin sysLogin = sysLoginService.get(id);
        return MessageSupport.successDataMsg(sysLogin, "查询成功");
    }

}