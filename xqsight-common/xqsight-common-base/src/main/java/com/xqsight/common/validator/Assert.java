package com.xqsight.common.validator;

import com.xqsight.common.exception.ParamsException;
import com.xqsight.common.exception.constants.ErrorMessageConstants;
import com.xqsight.common.utils.StringUtils;

public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new ParamsException(ErrorMessageConstants.ERROR_10000,message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new ParamsException(ErrorMessageConstants.ERROR_10000,message);
        }
    }
}