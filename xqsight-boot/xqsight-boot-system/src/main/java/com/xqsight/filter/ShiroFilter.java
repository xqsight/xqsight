package com.xqsight.filter;

import com.xqsight.sso.shiro.filter.PassThruAuthenticationWithGotoFilter;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author wangganggang
 * @date 2017/04/01
 */
public class ShiroFilter {

    @Autowired
    private SecurityManager securityManager;


    @Bean
    public ShiroFilterFactoryBean shirFilter() {
        System.out.println("ShiroConfiguration.shirFilter()");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        /** 必须设置 SecurityManager **/
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        Map<String, Filter> filters = new HashMap<>();
        filters.put("authc",getPassThruAuthenticationWithGotoFilter());
        shiroFilterFactoryBean.setFilters(filters);

        /** 拦截器 **/
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        filterChainDefinitionMap.put("/logout", "anon");
        filterChainDefinitionMap.put("/index", "anon");
        filterChainDefinitionMap.put("/", "anon");
        filterChainDefinitionMap.put("/**", "anon");

        /*// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");*/

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    @Bean
    public Filter getPassThruAuthenticationWithGotoFilter(){
        PassThruAuthenticationWithGotoFilter passThruAuthenticationWithGotoFilter = new PassThruAuthenticationWithGotoFilter();
        passThruAuthenticationWithGotoFilter.setPersonalLoginUrl("");
        passThruAuthenticationWithGotoFilter.setSystemLoginUrl("");
        return passThruAuthenticationWithGotoFilter;
    }
}
