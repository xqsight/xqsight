/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.sso.authc.service;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.xqsight.sso.model.UserBaseModel;

/**
 * 
 * @author linhaoran
 * @version PasswordHelper.java, v 0.1 2015年8月5日 上午10:46:14 linhaoran
 */
public class PasswordHelper {
    
    private final static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    private final static String algorithmName  = "SHA-256";
    private final static int    hashIterations = 2;


    public static void encryptPassword(UserBaseModel user) {
        Assert.notNull(user, "user must not be null");
        Assert.notNull(user.getLoginId(), "user loginId must not be null");
        if(!StringUtils.hasLength(user.getSalt())){
            user.setSalt(randomNumberGenerator.nextBytes().toHex());
        }
        String newPassword = new SimpleHash(algorithmName, user.getPassword(), ByteSource.Util.bytes(user.getCredentialsSalt()), hashIterations).toHex();
        user.setPassword(newPassword);
    }
    
    public static boolean checkPassword(UserBaseModel user, String password) {
        Assert.notNull(user, "user must not be null");
        Assert.notNull(user.getLoginId(), "user loginId must not be null");
        Assert.notNull(user.getSalt(), "user salt must not be null");
        String newPassword = new SimpleHash(algorithmName, password, ByteSource.Util.bytes(user.getCredentialsSalt()), hashIterations).toHex();
        return newPassword.equals(user.getPassword());
    }
}
