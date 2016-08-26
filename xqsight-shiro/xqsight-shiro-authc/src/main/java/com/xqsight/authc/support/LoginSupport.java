/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.authc.support;

import com.xqsight.authc.enums.LoginTypeEnum;
import org.springframework.util.Assert;

/**
 * 
 * @author linhaoran
 * @version LoginSupport.java, v 0.1 2015年9月24日 下午6:49:44 linhaoran
 */
public class LoginSupport {

    /**
     * 判断登陆id类别
     * @param loginId
     * @return
     */
    public static LoginTypeEnum judgeLoginType(String loginId) {
        Assert.notNull(loginId, "loginId must not be null");
        if(loginId.matches(".*\\@.*")){
            return LoginTypeEnum.EMAIL;
        }
        if(loginId.matches("1[\\d]{10}")){
            return LoginTypeEnum.CELLPHONE;
        }
        if(loginId.matches("[a-zA-Z0-9-_]+")){
            return LoginTypeEnum.USERID;
        }
        return LoginTypeEnum.NOTSURE;
    }
}
