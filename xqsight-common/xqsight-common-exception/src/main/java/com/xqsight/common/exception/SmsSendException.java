package com.xqsight.common.exception;

/**
 * @author wangganggang
 * @date 2017年07月22日 上午12:31
 */
public class SmsSendException extends GlobalException {
    public SmsSendException(int code, String message) {
        super(code, message);
    }
}
