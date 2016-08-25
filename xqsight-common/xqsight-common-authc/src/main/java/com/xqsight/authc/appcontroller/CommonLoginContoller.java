/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.authc.appcontroller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.xqsight.authc.exceptions.ValilCodeNoEqualException;
import com.xqsight.sso.authc.SSOUsernamePasswordToken;
import com.xqsight.sso.enums.UserType;
import com.xqsight.sso.subject.SSOSubject;
import com.xqsight.sso.utils.SSOUtils;

/**
 * 
 * @author linhaoran
 * @version ShiroContoller.java, v 0.1 2015年7月3日 上午9:49:26 linhaoran
 */
@Controller
@RequestMapping("/login")
public class CommonLoginContoller extends AbstractLoginContoller {

	@RequestMapping("/login")
	protected RedirectView login(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		SSOSubject currentUser = SSOUtils.getCurrentUserSubject();
		logger.info("we take u are: name={}, isAuthc={}, isRememberMe={}, isRunAs={}", currentUser.getPrincipal(), currentUser.isAuthenticated(), currentUser.isRemembered(), currentUser.isRunAs());

		if (!currentUser.isAuthenticated()) {
			// 判断验证码
			try {
				checkCode(request);
			} catch (ValilCodeNoEqualException e) {
				logger.error("验证码错误");
				throw new UnsupportedEncodingException();
			}
			currentUser.login(getUsernamePasswrdToken(request));
		}
		return getRedirectView(request);
	}

	/**
	 * @see com.saicfc.pmpf.sso.shiro.appcontroller.AbstractLoginContoller#chooseTokenInstance(String,
	 *      String, boolean)
	 */
	@Override
	protected SSOUsernamePasswordToken chooseTokenInstance(String username, String password, boolean isRememberMe) {
		return new SSOUsernamePasswordToken(username, password, isRememberMe, UserType.SYSTEM);
	}

}
