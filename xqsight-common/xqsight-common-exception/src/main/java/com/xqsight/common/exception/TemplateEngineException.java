package com.xqsight.common.exception;

import com.xqsight.common.exception.constants.ErrorMessageConstants;

/**
 * @author wangganggang
 * @date 2017/04/07
 */
public class TemplateEngineException extends GlobalException {

    public TemplateEngineException() {
        super(ErrorMessageConstants.ERROR_20001);
    }

    public TemplateEngineException(Throwable cause) {
        super(ErrorMessageConstants.ERROR_20001, cause);
    }
}
