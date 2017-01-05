package com.xqsight.authc.support;

import com.xqsight.authc.enums.LoginTypeEnum;
import org.springframework.util.Assert;

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
