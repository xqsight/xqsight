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
