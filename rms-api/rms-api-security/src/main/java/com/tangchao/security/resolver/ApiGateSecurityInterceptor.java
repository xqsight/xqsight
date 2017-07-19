package com.tangchao.security.resolver;

import com.tangchao.security.annotation.ApiGateSecurity;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * api权限拦截器
 *
 * Created by wangganggang on 2017/7/17.
 */
public class ApiGateSecurityInterceptor extends HandlerInterceptorAdapter {
    private String authHost;
    private String tokenHead;

    /**
     * @param tokenHead 认证信息，默认access-token
     */
    public ApiGateSecurityInterceptor(String authHost, String tokenHead) {
        this.authHost = authHost;
        this.tokenHead = tokenHead;
    }

    /**
     * 默认access-token
     */
    public ApiGateSecurityInterceptor(String authHost) {
        this.authHost = authHost;
        this.tokenHead = "access-token";
    }

    @Override
    public boolean preHandle(HttpServletRequest httpRequest, HttpServletResponse httpResponse, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        ApiGateSecurity methodAnnotation = methodAnnotation = handlerMethod.getBeanType().getAnnotation(ApiGateSecurity.class);
        if (methodAnnotation == null)
            methodAnnotation = handlerMethod.getMethodAnnotation(ApiGateSecurity.class);
        String token = httpRequest.getHeader(tokenHead);
        if (methodAnnotation != null) {

        }
        return super.preHandle(httpRequest, httpResponse, handler);
    }


}
