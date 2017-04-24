package com.xqsight.authc.appcontroller;

import com.xqsight.sso.authc.SSOUsernamePasswordToken;
import com.xqsight.sso.enums.UserType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @version ShiroContoller.java, v 0.1 2015年7月3日 上午9:49:26
 */
@RestController
@RequestMapping("/login")
public class CommonLoginContoller extends AbstractLoginContoller {

	/**
	 *
	 */
	@Override
	protected SSOUsernamePasswordToken chooseTokenInstance(String username, String password, boolean isRememberMe) {
		return new SSOUsernamePasswordToken(username, password, isRememberMe, UserType.SYSTEM);
	}

}
