package com.xqsight.common.exception.handler;

import com.xqsight.common.exception.GlobalException;
import com.xqsight.common.exception.support.Exceptions;
import com.xqsight.common.model.BaseResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wangganggang
 * @date 2017/04/07
 */
public class BaseGlobalExceptionHandler {

    protected final Logger logger = LogManager.getLogger(getClass());

    protected static final String DEFAULT_ERROR_MESSAGE = "系统忙，请稍后再试";

    protected Object handleError(HttpServletRequest req, Exception e, int status) {
        String errorMsg;
        if(e instanceof GlobalException){
            errorMsg = e.getMessage();
        }else if(e instanceof HttpRequestMethodNotSupportedException){
            errorMsg = "请求方法不支持";
        }else{
            errorMsg = DEFAULT_ERROR_MESSAGE;
        }

        String errorStack = Exceptions.getStackTraceAsString(e);
        return handleViewError(req.getRequestURL().toString(),req.getMethod(), errorStack, errorMsg, status);
    }

    protected Object handleViewError(String url,String method, String errorStack, String errorMessage, int status) {
        logger.error("request is exception url:{} ,method:{}, reason:{},exception stack:{}", url, method,errorMessage, errorStack);
        return new BaseResult(status, errorMessage);
    }


}
