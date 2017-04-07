package com.xqsight.common.exception.handler;

import com.xqsight.common.exception.GlobalException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wangganggang
 * @date 2017/04/07
 */
@ControllerAdvice
public class GlobalExceptionHandler extends BaseGlobalExceptionHandler {

    /** 比如404的异常就会被这个方法捕获 **/
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public Object handle404Error(HttpServletRequest req, Exception e) throws Exception {
        return handleError(req, e, HttpStatus.NOT_FOUND.value());
    }

    /** 500的异常会被这个方法捕获 **/
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object handleError(HttpServletRequest req, Exception e) throws Exception {
        return handleError(req, e, HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public Object handleError(HttpServletRequest req, NullPointerException e) throws Exception {
        return handleError(req, e, HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    /** 系统内部异常 **/
    @ExceptionHandler(GlobalException.class)
    @ResponseBody
    public Object handleError(HttpServletRequest req, GlobalException e) throws Exception {
        return handleError(req, e, e.getCode());
    }



}
