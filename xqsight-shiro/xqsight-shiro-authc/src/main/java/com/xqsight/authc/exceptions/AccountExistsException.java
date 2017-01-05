package com.xqsight.authc.exceptions;

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
