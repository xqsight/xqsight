package com.xqsight.authc.appcontroller;

import com.alibaba.fastjson.JSON;
import com.xqsight.sso.subject.SSOSubject;
import com.xqsight.sso.utils.SSOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @version SignUpController.java, v 0.1 2015年9月29日 下午11:20:53
 */
@Controller
@RequestMapping("/authc")
public class LogoutController {

	private final static Logger logger = LogManager.getLogger(LogoutController.class);

	@RequestMapping("/logout")
	public void logout(HttpServletRequest request) {
		SSOSubject sSOSubject = SSOUtils.getCurrentUserSubject();
		if(sSOSubject.isAuthenticated()){
			logger.debug("当前用户登出系统，用户名:{}", JSON.toJSONString(sSOSubject.getPrincipal()));
			sSOSubject.logout();
		}
	}
}
