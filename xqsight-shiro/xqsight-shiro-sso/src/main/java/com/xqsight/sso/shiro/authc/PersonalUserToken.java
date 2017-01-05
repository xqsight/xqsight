package com.xqsight.sso.shiro.authc;

import org.apache.shiro.authc.UsernamePasswordToken;

public class PersonalUserToken extends UsernamePasswordToken {

    /**
     * @param username
     * @param password
     * @param isRememberMe
     */
    public PersonalUserToken(String username, String password, boolean isRememberMe) {
        super(username, password, isRememberMe);
    }

    /**  */
    private static final long serialVersionUID = 2406006924437190528L;

}
