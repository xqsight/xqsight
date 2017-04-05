package com.xqsight.sso.utils;

import com.xqsight.common.model.shiro.BaseUserModel;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 *密码处理
 */
public class PasswordHelper {
    
    private final static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    private final static String algorithmName  = "SHA-256";
    private final static int    hashIterations = 2;


    public static void encryptPassword(BaseUserModel user) {
        Assert.notNull(user, "user must not be null");
        if(!StringUtils.hasLength(user.getSalt())){
            user.setSalt(randomNumberGenerator.nextBytes().toHex());
        }
        String newPassword = new SimpleHash(algorithmName, user.getPassword(), ByteSource.Util.bytes(user.getCredentialsSalt()), hashIterations).toHex();
        user.setPassword(newPassword);
    }
    
    public static boolean checkPassword(BaseUserModel user, String password) {
        Assert.notNull(user, "user must not be null");
        Assert.notNull(user.getSalt(), "user salt must not be null");
        String newPassword = new SimpleHash(algorithmName, password, ByteSource.Util.bytes(user.getCredentialsSalt()), hashIterations).toHex();
        return newPassword.equals(user.getPassword());
    }
}
