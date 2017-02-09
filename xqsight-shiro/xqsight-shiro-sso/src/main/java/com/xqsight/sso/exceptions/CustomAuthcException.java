package com.xqsight.sso.exceptions;

import org.apache.shiro.authc.AuthenticationException;

/**
 * Created by wangganggang on 2017/2/9.
 */
public class CustomAuthcException extends AuthenticationException {

    private int code;

    private String message;

    public CustomAuthcException() {
        super();
    }

    public CustomAuthcException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public CustomAuthcException(String message) {
        super(message);
        this.message = message;
    }

    public CustomAuthcException(Throwable cause) {
        super(cause);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
