package com.xqsight.sso.filter;

import com.xqsight.common.exception.UnAuthcException;
import com.xqsight.data.ehcache.core.CacheTemplate;
import com.xqsight.sso.authc.service.UserAuthcService;
import com.xqsight.sso.shiro.constants.WebConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author wangganggang
 * @date 2017/04/26
 */
public class AuthcFilter implements Filter {


    @Autowired
    private CacheTemplate cacheTemplate;


    @Autowired
    private UserAuthcService userAuthcService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String sessionId = request.getSession().getId();
        if(!StringUtils.contains(request.getRequestURI(),"login")){
            String value = (String) cacheTemplate.get(sessionId);
            if(!StringUtils.equals(value,"admin")){
                PrintWriter printWriter = response.getWriter();
                printWriter.write("{code:-1,message:\"unlogin\"}");
                printWriter.flush();
                return;
            }
        }else {
            String loginId = request.getParameter(WebConstants.LOGIN_ID);
            String password = request.getParameter(WebConstants.PASSWORD);
            if(StringUtils.equals(loginId,"admin") && StringUtils.equals(password,"admin")){
                cacheTemplate.put(sessionId,loginId);
            }else {
                PrintWriter printWriter = response.getWriter();
                printWriter.write("{code:-1,message:\"用户名或密码不多\"}");
                printWriter.flush();
                return;
            }
        }
        chain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
