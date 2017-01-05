package com.xqsight.sso.shiro.authc;

import org.apache.shiro.authc.UsernamePasswordToken;

public class SysUserToken extends UsernamePasswordToken {

    /**
	 * @Fields serialVersionUID : TODO（用一句话描述这个变量表示什么）
	 */
	private static final long serialVersionUID = 6243237132140752606L;

	/**
     * @param username
     * @param password
     * @param isRememberMe
     */
    public SysUserToken(String username, String password, boolean isRememberMe) {
        super(username, password, isRememberMe);
    }

}
