/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.authc.exceptions;

import org.apache.shiro.authc.AuthenticationException;

/**
 * ValilCodeNoEqualException
 * 
 * @author wanggganggang
 *
 */
public class ValilCodeNoEqualException extends AuthenticationException {
    
    /**  */
    private static final long serialVersionUID = -5424079665393604208L;

    public ValilCodeNoEqualException() {
        super();
    }
    
    public ValilCodeNoEqualException(String message) {
        super(message);
    }

}
