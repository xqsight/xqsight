package com.xqsight.sys.controller;

import com.xqsight.common.constants.ErrorMessageConstants;
import com.xqsight.common.support.MessageSupport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by user on 2016/5/25.
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    private Logger logger = LogManager.getLogger(getClass());

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public Object dealWitUnauthorizedException(UnauthorizedException ex){
        logger.error("当前没有权限操作", ex.getMessage());
        return MessageSupport.errorMsg(ErrorMessageConstants.ERR_MSG_0001,
                ErrorMessageConstants.getValue(ErrorMessageConstants.ERR_MSG_0001));
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public Object dealWitRuntimeException(NullPointerException ex){
        logger.error("请确认参数是否传递完整", ex.getMessage());
        return MessageSupport.errorMsg(ErrorMessageConstants.ERR_MSG_0004,
                ErrorMessageConstants.getValue(ErrorMessageConstants.ERR_MSG_0004));
    }

    @ExceptionHandler(ClassCastException.class)
    @ResponseBody
    public Object dealWitRuntimeException(ClassCastException ex){
        logger.error("请确认你传递的参数是否正确", ex.getMessage());
        return MessageSupport.errorMsg(ErrorMessageConstants.ERR_MSG_0005,
                ErrorMessageConstants.getValue(ErrorMessageConstants.ERR_MSG_0005));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public Object dealWitRuntimeException(IllegalArgumentException ex){
        logger.error("你传递参数不合法或不正确", ex.getMessage());
        return MessageSupport.errorMsg(ErrorMessageConstants.ERR_MSG_0006,
                ErrorMessageConstants.getValue(ErrorMessageConstants.ERR_MSG_0006));
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseBody
    public Object dealWitRuntimeException(IndexOutOfBoundsException ex){
        logger.error("数组下标越界", ex.getMessage());
        return MessageSupport.errorMsg(ErrorMessageConstants.ERR_MSG_0007,
                ErrorMessageConstants.getValue(ErrorMessageConstants.ERR_MSG_0007));
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object dealWitRuntimeException(Exception ex){
        logger.error("系统运行异常，请联系管理员", ex.getMessage());
        return MessageSupport.errorMsg(ErrorMessageConstants.ERR_MSG_0002,
                ErrorMessageConstants.getValue(ErrorMessageConstants.ERR_MSG_0002));
    }
}
