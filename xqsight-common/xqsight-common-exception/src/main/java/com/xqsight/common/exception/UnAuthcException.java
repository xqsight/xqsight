package com.xqsight.common.exception;

import com.xqsight.common.model.constants.ErrorMessageConstants;

/**
 * @author wangganggang
 * @date 2017/04/07
 */
public class UnAuthcException extends GlobalException {

    public UnAuthcException() {
        super(ErrorMessageConstants.ERROR_10005);
    }

    public UnAuthcException(int code) {
        super(code);
    }

    public UnAuthcException(int code, String message) {
        super(code, message);
    }

    public UnAuthcException(Throwable cause) {
        super(ErrorMessageConstants.ERROR_10005, cause);
    }
}
