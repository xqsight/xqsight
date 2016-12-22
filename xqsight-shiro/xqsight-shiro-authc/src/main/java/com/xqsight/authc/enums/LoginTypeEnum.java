package com.xqsight.authc.enums;

/**
 * 登陆账户类型
 * @version LoginTypeEnum.java, v 0.1 2015年9月24日 下午6:48:12
 */
public enum LoginTypeEnum {

    USERID(1),
    
    EMAIL(2),
    
    CELLPHONE(3),
    
    NOTSURE(-1);
    
    LoginTypeEnum(int loginType) {
        this.loginType = loginType;
    }
    
    private int loginType;
    
    public int value(){
        return this.loginType;
    }
}
