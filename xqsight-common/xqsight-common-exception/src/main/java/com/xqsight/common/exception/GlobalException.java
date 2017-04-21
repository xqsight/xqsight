package com.xqsight.common.exception;

import com.xqsight.common.model.constants.ErrorMessageConstants;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author wangganggang
 * @date 2017/04/07
 */
public class GlobalException extends RuntimeException {

    protected final Logger logger = LogManager.getLogger(getClass());

    @Setter
    @Getter
    private int code;

    public GlobalException(int code) {
        super(ErrorMessageConstants.getValue("" + code));
    }

    public GlobalException(int code,String message) {
        super(message);
        this.code = code;
    }


    public GlobalException(int code, Throwable cause) {
        super(ErrorMessageConstants.getValue("" + code), cause);
        this.code = code;
    }
}
