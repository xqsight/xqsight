/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2016 All Rights Reserved.
 */
package com.xqsight.sso.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.subject.Subject;

import com.alibaba.fastjson.JSON;
import com.xqsight.sso.model.UserBaseModel;
import com.xqsight.sso.shiro.constants.WebConstants;

/**
 * @Description: TODO
 * @author wangganggang
 * @date 2016年1月20日 上午9:36:58
 *
 */
public class AuthorizationFilter extends org.apache.shiro.web.filter.authz.AuthorizationFilter {

	protected Logger logger = LogManager.getLogger(AuthorizationFilter.class);

	/**
	 * <p>  Title: isAccessAllowed </p>
	 * <p> Description:  </p>
	 * 
	 * @param request
	 * @param response
	 * @param mappedValue
	 * @return
	 * @throws Exception
	 * @see org.apache.shiro.web.filter.AccessControlFilter#isAccessAllowed(ServletRequest,
	 *      ServletResponse, Object)
	 */
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
		Subject subject = getSubject(request, response);
		if(subject.isAuthenticated()){
			String reqUrl = ((HttpServletRequest) request).getRequestURL().toString();
			logger.debug("request url:{}",reqUrl);
	        UserBaseModel userBaseModel = (UserBaseModel)subject.getSession().getAttribute(WebConstants.CURRENT_USER);
	        logger.debug("current user is:{}",JSON.toJSONString(userBaseModel));
	        return true;
		}
        return false;  
	}

}
