/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.authc.exceptions;

/**
 * 
 * @author linhaoran
 * @version AccountExistsException.java, v 0.1 2015年9月30日 上午11:27:05 linhaoran
 */
public class AccountExistsException extends Exception {
    
    /**  */
    private static final long serialVersionUID = -5424079665393604208L;

    public AccountExistsException() {
        super();
    }
    
    public AccountExistsException(String message) {
        super(message);
    }

}
