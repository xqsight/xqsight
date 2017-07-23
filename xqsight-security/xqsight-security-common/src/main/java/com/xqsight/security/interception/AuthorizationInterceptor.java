package com.xqsight.security.interception;


import com.xqsight.common.exception.UnAuthcException;
import com.xqsight.common.exception.constants.ErrorMessageConstants;
import com.xqsight.common.model.shiro.UserToken;
import com.xqsight.common.utils.StringUtils;
import com.xqsight.security.annontation.AuthIgnore;
import com.xqsight.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wangganggang
 * @date 2017年07月21日
 */
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    public static final String LOGIN_USER_KEY = "LOGIN_USER_KEY";

    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        AuthIgnore annotation;
        if (handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(AuthIgnore.class);
        } else {
            return true;
        }

        //如果有@IgnoreAuth注解，则不验证token
        if (annotation != null) {
            return true;
        }

        //从header中获取token
        String token = request.getHeader("token");
        //如果header中不存在token，则从参数中获取token
        if (StringUtils.isBlank(token)) {
            token = request.getParameter("token");
        }

        //token为空
        if (StringUtils.isBlank(token)) {
            throw new UnAuthcException(ErrorMessageConstants.ERROR_40002, "token不能为空");
        }

        //查询token信息
        UserToken userToken = tokenService.queryByToken(token);
        if (userToken == null || userToken.getExpireTime().getTime() < System.currentTimeMillis()) {
            throw new UnAuthcException(ErrorMessageConstants.ERROR_40003, "token失效，请重新登录");
        }

        //设置userId到request里，后续根据userId，获取用户信息
        request.setAttribute(LOGIN_USER_KEY, userToken.getUserId());

        return true;
    }
}
