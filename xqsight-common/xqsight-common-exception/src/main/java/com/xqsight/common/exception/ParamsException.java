package com.xqsight.common.exception;

import com.xqsight.common.model.constants.ErrorMessageConstants;

/**
 * @author wangganggang
 * @date 2017/04/07
 */
public class ParamsException extends GlobalException {

    public ParamsException() {
        super(ErrorMessageConstants.ERROR_20001);
    }

    public ParamsException(Throwable cause) {
        super(ErrorMessageConstants.ERROR_20001, cause);
    }
}
