package com.xqsight.system.interceptor;

import com.xqsight.sso.utils.SSOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wangganggang on 2017/1/16.
 */
public class RequestParamsHandlerInterceptor implements HandlerInterceptor {

    protected Logger logger = LogManager.getLogger(getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.debug("before");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if(SSOUtils.isAuthenticated()){
            request.setAttribute("currentUserId",SSOUtils.getCurrentUser().getId());
            request.setAttribute("createUserId",SSOUtils.getCurrentUser().getId());
            request.setAttribute("updateUserId", SSOUtils.getCurrentUser().getId());
        }
        logger.debug(".....");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.debug("after");
    }
}
