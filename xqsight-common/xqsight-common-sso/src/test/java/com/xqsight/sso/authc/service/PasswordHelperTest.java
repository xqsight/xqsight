package com.xqsight.sso.authc.service;


import java.text.ParseException;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;

import com.xqsight.sso.model.UserBaseModel;

/**
 * 
 * @author linhaoran
 * @version PasswordHelperTest.java, v 0.1 2015年9月25日 下午4:41:06 linhaoran
 */
public class PasswordHelperTest {

    /**
     * @throws ParseException
     */
    @Test
    public void testEncryptPassword() throws ParseException {        UserBaseModel user = new UserBaseModel();
    user.setId(1l);
    user.setPassword("123456");
    user.setLoginId("18621509689");
    user.setSalt("20b9b8a2295115cf419bad7b6cdc63fa");
    PasswordHelper.encryptPassword(user);
    System.out.println(user.getPassword());
    System.out.println(user.getSalt());}

    /**
     * @throws ParseException
     */
    @Test
    public void testCheckPassword() throws ParseException {
        UserBaseModel user = new UserBaseModel();
        user.setId(1l);
        user.setPassword("7944609c925d18fcddf7c2a81b11b46a8eb40ef17feeb0f04db69e353cb1b073");
        user.setSalt("20b9b8a2295115cf419bad7b6cdc63fa");
        user.setLoginId("18621509689");
        //assertTrue(PasswordHelper.checkPassword(user, "123456"));
    }

    @Test
    public void testGetPassword() throws ParseException {
        UserBaseModel user = new UserBaseModel();
        user.setPassword("111111");
        user.setSalt("201406e096abed6f1dfee2028f4ad914");
        String algorithmName  = "SHA-256";
        int    hashIterations = 2;
        String newPassword = new SimpleHash(algorithmName, "111111", ByteSource.Util.bytes(user.getSalt()), hashIterations).toHex();
        System.out.print(newPassword);
    }

}
