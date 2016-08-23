/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.sso.shiro.realm;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xqsight.sso.authc.CustomAuthenticationInfo;
import com.xqsight.sso.authc.service.UserAuthcService;
import com.xqsight.sso.model.UserBaseModel;
import com.xqsight.sso.shiro.authc.PersonalUserToken;

/**
 * 
 * @author linhaoran
 * @version PersonalUserRealm.java, v 0.1 2015年8月3日 下午5:25:01 linhaoran
 */
public class PersonalUserRealm extends AuthorizingRealm {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource(name = "sysUserAuthcService")
	private UserAuthcService userAuthcService;

	@Override
	public boolean supports(AuthenticationToken token) {
		return token == null || token instanceof PersonalUserToken;
	};

	/**
	 * @see AuthorizingRealm#doGetAuthorizationInfo(PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String loginId = (String) principals.fromRealm(getName()).iterator().next();
		logger.info("doGetAuthorizationInfo(PrincipalCollection principals):" + loginId);
		UserBaseModel user = userAuthcService.findByLoginId(loginId);
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.setRoles(userAuthcService.findRoles(user.getId()));
		authorizationInfo.setStringPermissions(userAuthcService.findPermissions(user.getId()));
		return authorizationInfo;
	}

	/**
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		logger.info("doGetAuthorizationInfo(AuthenticationToken authcToken):" + authcToken);
		String loginId = (String) authcToken.getPrincipal();
		UserBaseModel user = userAuthcService.findByLoginId(loginId);

		if (user == null) {
			throw new UnknownAccountException("用户名没有找到");// 没找到帐号
		}

		if (user.isUserLocked()) {
			throw new LockedAccountException("您的用户已锁定"); // 帐号锁定
		}
		logger.info("From db Password={}, CredentialsSalt={}", user.getPassword(), user.getCredentialsSalt());
		// 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
		return new CustomAuthenticationInfo(user, user.getLoginId(), // 用户名
				user.getPassword(), // 密码
				ByteSource.Util.bytes(user.getCredentialsSalt()), // salt=loginId +salt
				getName() // realm name
		);
	}

	@Override
	protected Object getAuthorizationCacheKey(PrincipalCollection principals) {
		return principals != null ? principals.toString() : "";
	}

}
