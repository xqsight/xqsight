package com.xqsight.sys.controller;

import com.xqsight.common.model.constants.ErrorMessageConstants;
import com.xqsight.commons.support.MessageSupport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.session.UnknownSessionException;
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
    public Object dealWithUnauthorizedException(UnauthorizedException ex){
        logger.error("当前没有权限操作", ex.getMessage());
        ex.printStackTrace();
        return MessageSupport.errorMsg(ErrorMessageConstants.ERR_MSG_0001,"没有权限操作");
    }

    @ExceptionHandler(AuthorizationException.class)
    @ResponseBody
    public Object dealWithAuthorizationException (AuthorizationException ex){
        logger.error("当前没有权限操作", ex.getMessage());
        ex.printStackTrace();
        return MessageSupport.errorMsg(ErrorMessageConstants.ERR_MSG_0001,"没有权限操作");
    }

    @ExceptionHandler(UnauthenticatedException.class)
    @ResponseBody
    public Object dealWithUnauthenticatedException(UnauthenticatedException ex){
        logger.error("当前没有权限操作", ex.getMessage());
        ex.printStackTrace();
        return MessageSupport.errorMsg(ErrorMessageConstants.ERR_MSG_0001,"没有权限操作");
    }

    @ExceptionHandler(UnknownSessionException.class)
    @ResponseBody
    public Object dealWithUnknownSessionException(UnknownSessionException ex){
        logger.error("当前没有权限操作", ex.getMessage());
        ex.printStackTrace();
        return MessageSupport.errorMsg(ErrorMessageConstants.ERR_MSG_0001,"当前session不存在");
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public Object dealWithNullPointerException(NullPointerException ex){
        logger.error("请确认参数是否传递完整", ex.getMessage());
        ex.printStackTrace();
        return MessageSupport.errorMsg(ErrorMessageConstants.ERR_MSG_0004,"请确认参数是否传递完整");
    }

    @ExceptionHandler(ClassCastException.class)
    @ResponseBody
    public Object dealWithClassCastException(ClassCastException ex){
        logger.error("请确认你传递的参数是否正确", ex.getMessage());
        ex.printStackTrace();
        return MessageSupport.errorMsg(ErrorMessageConstants.ERR_MSG_0005,"请确认你传递的参数是否正确");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public Object dealWithIllegalArgumentException(IllegalArgumentException ex){
        logger.error("你传递参数不合法或不正确", ex.getMessage());
        ex.printStackTrace();
        return MessageSupport.errorMsg(ErrorMessageConstants.ERR_MSG_0006,"你传递参数不合法或不正确");
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseBody
    public Object dealWithIndexOutOfBoundsException(IndexOutOfBoundsException ex){
        logger.error("数组下标越界", ex.getMessage());
        ex.printStackTrace();
        return MessageSupport.errorMsg(ErrorMessageConstants.ERR_MSG_0007,"数组下标越界");
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseBody
    public Object dealWithIllegalStateException(IllegalStateException ex){
        logger.error("系统环境异常，请重试", ex.getMessage());
        ex.printStackTrace();
        return MessageSupport.errorMsg(ErrorMessageConstants.ERR_MSG_0008,"系统环境异常，请重试");
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object dealWitRuntimeException(Exception ex){
        logger.error("系统运行异常，请联系管理员", ex.getMessage());
        ex.printStackTrace();
        return MessageSupport.errorMsg(ErrorMessageConstants.ERR_MSG_0002,"系统运行异常，请联系管理员");
    }
}
