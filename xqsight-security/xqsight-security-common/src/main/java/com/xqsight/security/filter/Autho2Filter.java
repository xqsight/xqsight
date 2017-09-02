package com.xqsight.security.filter;

import com.alibaba.fastjson.JSON;
import com.xqsight.common.model.BaseResult;
import com.xqsight.common.model.constants.Constants;
import com.xqsight.common.utils.CookieUtils;
import com.xqsight.common.utils.StringUtils;
import com.xqsight.security.authc.Autho2Token;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wangganggang
 * @date 2017年07月22日 上午9:37
 */
public class Autho2Filter extends AuthenticatingFilter {

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        String accessToken = httpServletRequest.getHeader(Constants.ACCESS_TOKEN);

        if (StringUtils.isBlank(accessToken)) {
            accessToken = httpServletRequest.getParameter(Constants.ACCESS_TOKEN);
        }

        if (StringUtils.isBlank(accessToken)) {
            CookieUtils.getCookieValue((HttpServletRequest) request, Constants.ACCESS_TOKEN);
        }

        return new Autho2Token(accessToken);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        String accessToken = httpServletRequest.getHeader(Constants.ACCESS_TOKEN);

        if (StringUtils.isBlank(accessToken)) {
            accessToken = httpServletRequest.getParameter(Constants.ACCESS_TOKEN);
        }

        if (StringUtils.isBlank(accessToken)) {
            CookieUtils.getCookieValue((HttpServletRequest) request, Constants.ACCESS_TOKEN);
        }

        //获取请求token，如果token不存在，直接返回401
        if (StringUtils.isBlank(accessToken)) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            BaseResult baseResult = new BaseResult(HttpStatus.UNAUTHORIZED.value(), "invalid token");
            String result = JSON.toJSON(baseResult).toString();
            httpResponse.getWriter().print(result);
            return false;
        }
        return executeLogin(request, response);
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setContentType("application/json;charset=utf-8");
        try {
            //处理登录失败的异常
            Throwable throwable = e.getCause() == null ? e : e.getCause();
            BaseResult baseResult = new BaseResult(HttpStatus.UNAUTHORIZED.value(), throwable.getMessage());
            String result = JSON.toJSON(baseResult).toString();
            httpResponse.getWriter().print(result);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return false;
    }

}
