/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.authc.enums;

/**
 * 登陆账户类型
 * @author linhaoran
 * @version LoginTypeEnum.java, v 0.1 2015年9月24日 下午6:48:12 linhaoran
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
