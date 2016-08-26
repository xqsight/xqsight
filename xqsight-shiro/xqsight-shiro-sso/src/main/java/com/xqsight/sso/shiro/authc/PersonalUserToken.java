/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.sso.shiro.authc;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 
 * @author linhaoran
 * @version PersonalUserToken.java, v 0.1 2015年8月5日 下午3:04:15 linhaoran
 */
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
