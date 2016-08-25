/**
 * Copyright (c) 1994-2015 All Rights Reserved.
 * 上海汽车集团财务有限责任公司
 */
package com.xqsight.sso.shiro.constants;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author linhaoran
 * @version WebConstants.java, v 0.1 2015年8月4日 下午4:59:29 linhaoran
 */
public class WebConstants {
    
    public final static String LOGIN_ID = "login_id";
    
    public final static String PASSWORD = "pass_wd";
    
    public final static String REMEMBER_ME = "rmb_m";
    
    public final static String VALIDATA_CODE = "vali_code";
    
    public final static String GO_TO = "goto";
    
    /** 是否启用aes加密控件 */
    public final static String IS_AES ="is_aes";
    
    /** session attribute 中的当前用户 */
    public final static String CURRENT_USER = "shiro.currUser";
    
    /** session attribute 中密码加密随机数 */
    public final static String SESSION_SECURE_RANDOM = "authc.srand";

    /** 普通个人用户角色代码 */
    public final static String NORMAL_PERSON_ROLL = "nml_per";
    /** 普通商家用户角色代码 */
    public final static String NORMAL_MERCHANT_ROLL = "nml_mer";
    
    /** 普通个人用户角色代码集合 */
    public final static Set<String> NORMAL_PERSON_ROLLS = Collections.unmodifiableSet(new HashSet<String>(Arrays.asList(new String[]{NORMAL_PERSON_ROLL})));
    /** 普通商家用户角色代码集合 */
    public final static Set<String> NORMAL_MERCHANT_ROLLS = Collections.unmodifiableSet(new HashSet<String>(Arrays.asList(new String[]{NORMAL_MERCHANT_ROLL})));
}

