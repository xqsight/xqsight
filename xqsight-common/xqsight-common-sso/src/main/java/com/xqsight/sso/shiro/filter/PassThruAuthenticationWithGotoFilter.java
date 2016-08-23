/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.sso.shiro.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.authc.PassThruAuthenticationFilter;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import com.xqsight.sso.shiro.constants.WebConstants;

/**
 * 
 * @author linhaoran
 * @version PassThruAuthenticationWithGotoFilter.java, v 0.1 2015年8月4日 下午6:14:45 linhaoran
 */
public class PassThruAuthenticationWithGotoFilter extends PassThruAuthenticationFilter {
    
    private static final Logger logger = LogManager.getLogger(PassThruAuthenticationWithGotoFilter.class);
    
    /** 个人用户登陆页面 */
    private String personalLoginUrl;
    
    
    /** 系统用户登陆页面 */
    private String systemLoginUrl;
    
    private final static String GO_TO_PARAM_PREFIX = WebConstants.GO_TO + "=";
    
    private final static String SYSTEM_URL_KEYWORD = "sys";
    
    @Override
    protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        String gotoUrl = getGotoUrl(request);
        SavedRequest savedRequest = WebUtils.getSavedRequest(request);
        if(savedRequest == null){
            logger.info("savedRequest is null");
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            logger.info("httpRequest: requestURI={}, QueryString={}, method={}", httpRequest.getRequestURI(), httpRequest.getQueryString(), httpRequest.getMethod()); 
            httpRequest.getPathTranslated();
        } else {
            logger.info("savedRequest: requestUrl={}, requestURI={}, QueryString={}, method={}", savedRequest.getRequestUrl(), savedRequest.getRequestURI(), savedRequest.getQueryString(), savedRequest.getMethod());            
        }
        WebUtils.issueRedirect(request, response, getRedirectUrl(request, gotoUrl));
    }
    
    
    /**
     * 得到用户原始访问路径，若是POST方式提交，则将参数组装到URL中
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     */
    private String getGotoUrl(ServletRequest request) throws UnsupportedEncodingException{
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        String queryString = httpRequest.getQueryString();
        StringBuffer basePath = httpRequest.getRequestURL();
        if(AccessControlFilter.POST_METHOD.equalsIgnoreCase(httpRequest.getMethod())){
            Enumeration<String> parameterNames = httpRequest.getParameterNames();
            if(parameterNames.hasMoreElements()){
                String firstName = parameterNames.nextElement();
                basePath.append("?").append(firstName).append("=").append(httpRequest.getParameter(firstName));
            }
            while(parameterNames.hasMoreElements()){
                String nextName = parameterNames.nextElement();
                basePath.append("&").append(nextName).append("=").append(httpRequest.getParameter(nextName));
            }
        } else {
            if(StringUtils.hasLength(queryString)){
                basePath.append("?").append(queryString);
            }
        }
        return URLEncoder.encode(basePath.toString(), "UTF-8");
    }
    
    /**
     * 得到重定向路径
     * @param request
     * @param gotoUrl
     * @return
     * @throws UnsupportedEncodingException
     */
    private String getRedirectUrl(ServletRequest request, String gotoUrl) throws UnsupportedEncodingException{
        String loginUrl = getLoginUrl(request, gotoUrl);
        if(loginUrl.endsWith("\\?")){
            return loginUrl + GO_TO_PARAM_PREFIX + gotoUrl;
        } else if(loginUrl.contains("\\?")) {
            return loginUrl + "&" + GO_TO_PARAM_PREFIX + gotoUrl;
        } else {
            return loginUrl + "?" + GO_TO_PARAM_PREFIX + gotoUrl;
        }
    }
    
    /**
     * 得到登陆Url
     * @param request 
     * @param gotoUrl
     * @return
     */
    public String getLoginUrl(ServletRequest request, String gotoUrl) {
        if(isSystemPath(request)){
        	return this.systemLoginUrl == null ? super.getLoginUrl() : this.systemLoginUrl;
        }
        
        return this.personalLoginUrl == null ? super.getLoginUrl() : this.personalLoginUrl;
    }
    
    
    /**
     * 判断用户的意图，是否要去一个系统连接
     * 判断依据是域名或者contextPath是否带有{@linkplain PassThruAuthenticationWithGotoFilter#SYSTEM_URL_KEYWORD SYSTEM_URL_KEYWORD}字样
     * @param request
     * @return
     */
    private boolean isSystemPath(ServletRequest request){
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        String serverName = httpRequest.getServerName();
        String ctxPath = httpRequest.getContextPath();
        return (serverName.contains(SYSTEM_URL_KEYWORD) || ctxPath.contains(SYSTEM_URL_KEYWORD));
    }


    /**
     * Setter method for property <tt>personalLoginUrl</tt>.
     * 
     * @param personalLoginUrl value to be assigned to property personalLoginUrl
     */
    public void setPersonalLoginUrl(String personalLoginUrl) {
        this.personalLoginUrl = personalLoginUrl;
    }


	/**
	 * setter method
	 * @param systemLoginUrl the systemLoginUrl to set
	 */
	public void setSystemLoginUrl(String systemLoginUrl) {
		this.systemLoginUrl = systemLoginUrl;
	}
    
    
}
