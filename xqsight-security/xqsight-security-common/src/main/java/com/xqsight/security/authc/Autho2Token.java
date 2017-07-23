package com.xqsight.security.authc;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author wangganggang
 * @date 2017年07月22日 上午8:57
 */
public class Autho2Token implements AuthenticationToken {

    private String toekn;

    public Autho2Token(String toekn) {
        this.toekn = toekn;
    }

    @Override
    public Object getPrincipal() {
        return toekn;
    }

    @Override
    public Object getCredentials() {
        return toekn;
    }
}
