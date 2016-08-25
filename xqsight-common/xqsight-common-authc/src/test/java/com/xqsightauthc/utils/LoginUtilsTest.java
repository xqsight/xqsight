/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsightauthc.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import com.xqsight.authc.support.LoginSupport;
import org.junit.Test;

import com.xqsight.authc.enums.LoginTypeEnum;

/**
 * 
 * @author linhaoran
 * @version LoginUtilsTest.java, v 0.1 2015年9月30日 上午8:51:58 linhaoran
 */
public class LoginUtilsTest {

    /**
     * Test method for {@link com.saicfc.pmpf.wallet.utils.LoginUtils#judgeLoginType(String)}.
     */
    @Test
    public void testJudgeLoginType() {
        assertEquals(LoginTypeEnum.EMAIL, LoginTypeEnum.EMAIL); 
        
        assertEquals(LoginTypeEnum.EMAIL, LoginSupport.judgeLoginType("fsfsf@fsfs.com"));
        assertNotEquals(LoginTypeEnum.EMAIL, LoginSupport.judgeLoginType("fsfsffsfs.com"));
        
        assertNotEquals(LoginTypeEnum.USERID, LoginSupport.judgeLoginType("fsfsffsfs.com"));
        assertEquals(LoginTypeEnum.USERID, LoginSupport.judgeLoginType("fsfsf_fsfscom"));
        assertEquals(LoginTypeEnum.USERID, LoginSupport.judgeLoginType("fsfsf-fsfscom"));
        
        assertEquals(LoginTypeEnum.CELLPHONE, LoginSupport.judgeLoginType("12345678901"));
        assertNotEquals(LoginTypeEnum.CELLPHONE, LoginSupport.judgeLoginType("123456789m"));
        
    }

}
