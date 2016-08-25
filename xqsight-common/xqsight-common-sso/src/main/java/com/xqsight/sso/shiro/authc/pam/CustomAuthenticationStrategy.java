/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.sso.shiro.authc.pam;

import java.util.Collection;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.AbstractAuthenticationStrategy;
import org.apache.shiro.authc.pam.AuthenticationStrategy;
import org.apache.shiro.realm.Realm;

import com.xqsight.sso.authc.CustomAuthenticationInfo;

/**
 * 
 * @author linhaoran
 * @version CustomAuthenticationStrategy.java, v 0.1 2015年9月29日 下午5:10:20 linhaoran
 */
public class CustomAuthenticationStrategy extends AbstractAuthenticationStrategy implements AuthenticationStrategy {

    @Override
    public AuthenticationInfo beforeAllAttempts(Collection<? extends Realm> realms, AuthenticationToken token) throws AuthenticationException {
        return new CustomAuthenticationInfo();
    }
}
