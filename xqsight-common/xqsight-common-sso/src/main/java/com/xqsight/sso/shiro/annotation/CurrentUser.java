/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.sso.shiro.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author linhaoran
 * @version CurrentUser.java, v 0.1 2015年8月4日 下午9:04:57 linhaoran
 */
@Retention(RetentionPolicy.RUNTIME)   
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface CurrentUser {

}
