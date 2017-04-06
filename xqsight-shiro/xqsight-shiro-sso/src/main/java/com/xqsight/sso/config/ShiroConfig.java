package com.xqsight.sso.config;

import com.xqsight.sso.shiro.authc.pam.CustomAuthenticationStrategy;
import com.xqsight.sso.shiro.authc.pam.SingleSupportModularRealmAuthenticator;
import com.xqsight.sso.shiro.mgt.CustomWebSecurityManager;
import com.xqsight.sso.shiro.realm.SysUserRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangganggang
 * @date 2017/03/31
 */
@Configuration
public class ShiroConfig {

    private static final int maxAge = 2592000;

    @Autowired
    private CacheManager cacheManager;

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        /** 散列算法:这里使用MD5算法; **/
        hashedCredentialsMatcher.setHashAlgorithmName("SHA-256");
        /** 散列的次数，比如散列两次，相当于 md5(md5("")); **/
        hashedCredentialsMatcher.setHashIterations(2);
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return hashedCredentialsMatcher;
    }

    @Bean
    public SimpleCookie simpleCookie() {
        SimpleCookie simpleCookie = new SimpleCookie();
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(maxAge);
        return simpleCookie;
    }

    @Bean
    public CookieRememberMeManager cookieRememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(simpleCookie());
        cookieRememberMeManager.setCipherKey("#{T(org.apache.shiro.codec.Base64).decode('4AvVhmFLUs0KTA3Kprsdag==')}".getBytes());
        return cookieRememberMeManager;
    }

    @Bean
    public SysUserRealm userShiroRealm() {
        SysUserRealm sysUserRealm = new SysUserRealm();
        sysUserRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return sysUserRealm;
    }

    @Bean
    public SingleSupportModularRealmAuthenticator singleSupportModularRealmAuthenticator() {
        SingleSupportModularRealmAuthenticator singleSupportModularRealmAuthenticator = new SingleSupportModularRealmAuthenticator();

        singleSupportModularRealmAuthenticator.setAuthenticationStrategy(customAuthenticationStrategy());
        return singleSupportModularRealmAuthenticator;
    }

    @Bean
    public CustomAuthenticationStrategy customAuthenticationStrategy() {
        return new CustomAuthenticationStrategy();
    }

    @Bean(name = "securityManager")
    public CustomWebSecurityManager securityManager() {
        CustomWebSecurityManager  securityManager = new CustomWebSecurityManager();
        /** 设置realm **/
        securityManager.setRealm(userShiroRealm());

        /** 注入缓存管理器
         *  这个如果执行多次，也是同样的一个对象;
         */
        securityManager.setCacheManager(cacheManager);
        securityManager.setSessionManager(defaultWebSessionManager());
        securityManager.setAuthenticator(singleSupportModularRealmAuthenticator());
        securityManager.setRememberMeManager(cookieRememberMeManager());

        return securityManager;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public DefaultWebSessionManager defaultWebSessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();

        sessionManager.setSessionDAO(enterpriseCacheSessionDAO());
        return sessionManager;
    }

    @Bean
    public EnterpriseCacheSessionDAO enterpriseCacheSessionDAO() {
        EnterpriseCacheSessionDAO enterpriseCacheSessionDAO = new EnterpriseCacheSessionDAO();
        enterpriseCacheSessionDAO.setCacheManager(cacheManager);
        return enterpriseCacheSessionDAO;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

}
