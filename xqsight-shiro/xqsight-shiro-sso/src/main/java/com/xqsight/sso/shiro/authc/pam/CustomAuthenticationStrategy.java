package com.xqsight.sso.shiro.authc.pam;

import com.xqsight.sso.authc.CustomAuthenticationInfo;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.AbstractAuthenticationStrategy;
import org.apache.shiro.authc.pam.AuthenticationStrategy;
import org.apache.shiro.realm.Realm;

import java.util.Collection;

public class CustomAuthenticationStrategy extends AbstractAuthenticationStrategy implements AuthenticationStrategy {

    @Override
    public AuthenticationInfo beforeAllAttempts(Collection<? extends Realm> realms, AuthenticationToken token) throws AuthenticationException {
        return new CustomAuthenticationInfo();
    }
}
