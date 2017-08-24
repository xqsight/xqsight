package com.xqsight.security.realm;

import com.xqsight.common.exception.UnAuthcException;
import com.xqsight.common.exception.constants.ErrorMessageConstants;
import com.xqsight.common.model.shiro.BaseUserModel;
import com.xqsight.common.model.shiro.UserToken;
import com.xqsight.security.authc.Autho2Token;
import com.xqsight.security.service.UserAuthcService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

/**
 * @author wangganggang
 * @date 2017年07月22日 上午8:52
 */
@Component
public class Autho2Realm extends AuthorizingRealm {

    protected Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private UserAuthcService userAuthcService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof Autho2Token;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        BaseUserModel user = (BaseUserModel) principals.getPrimaryPrincipal();
        logger.info("doGetAuthorizationInfo(PrincipalCollection principals):" + user.toString());

        /* 登陆用户角色 */
        Set<String> userRoles = userAuthcService.findRoles(user.getId());

        /* 用户功能权限 */
        Set<String> permissions = userAuthcService.findPermissions(user.getId());

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        authorizationInfo.setRoles(userRoles);

        authorizationInfo.setStringPermissions(permissions);

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String accessToken = (String)token.getPrincipal();

        UserToken userToken = userAuthcService.queryByToken(accessToken);

        //token失效
        if(userToken == null || userToken.getExpireTime().compareTo(LocalDate.now()) < 0){
            throw new UnAuthcException(ErrorMessageConstants.ERROR_40002,"token失效，请重新登录");
        }

        BaseUserModel user = userAuthcService.queryUserById(userToken.getUserId());

        if(user.isUserLocked()){
            throw new UnAuthcException(ErrorMessageConstants.ERROR_40001,"账号已被锁定,请联系管理员");
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user,accessToken,getName());

        return authenticationInfo;
    }
}
