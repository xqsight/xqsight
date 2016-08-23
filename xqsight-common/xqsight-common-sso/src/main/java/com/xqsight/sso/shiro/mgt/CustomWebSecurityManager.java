/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.sso.shiro.mgt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;

import com.xqsight.sso.authc.CustomAuthenticationInfo;
import com.xqsight.sso.shiro.constants.WebConstants;

/**
 * 
 * @author linhaoran
 * @version CustomWebSecurityManager.java, v 0.1 2015年9月28日 上午10:51:50 linhaoran
 */
public class CustomWebSecurityManager extends DefaultWebSecurityManager {

    private Logger logger = LogManager.getLogger(getClass());

    /** 
     * @see org.apache.shiro.mgt.DefaultSecurityManager#login(Subject, AuthenticationToken)
     */
    @Override
    public Subject login(Subject subject, AuthenticationToken token) throws AuthenticationException {
        AuthenticationInfo info;
        try {
            info = authenticate(token);
        } catch (AuthenticationException ae) {
            try {
                onFailedLogin(token, ae, subject);
            } catch (Exception e) {
                logger.warn("onFailedLogin method threw an exception.  Logging and propagating original AuthenticationException.", e);
            }
            throw ae; //propagate
        }

        Subject loggedIn = createSubject(token, info, subject);
        CustomAuthenticationInfo cInfo = (CustomAuthenticationInfo) info;
        loggedIn.getSession().setAttribute(WebConstants.CURRENT_USER, cInfo.getUserBaseModel());

        onSuccessfulLogin(token, info, loggedIn);

        return loggedIn;
    }
}
