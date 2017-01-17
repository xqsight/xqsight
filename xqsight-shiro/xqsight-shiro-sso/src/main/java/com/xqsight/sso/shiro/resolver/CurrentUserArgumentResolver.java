package com.xqsight.sso.shiro.resolver;

import com.xqsight.common.model.Model;
import com.xqsight.sso.shiro.annotation.CurrentUser;
import com.xqsight.sso.utils.SSOUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Created by wangganggang on 2017/1/17.
 */
public class CurrentUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CurrentUser.class)||
                Model.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        long currentUserId = 0;
        if (SSOUtils.isAuthenticated())
            currentUserId = SSOUtils.getCurrentUserId();

        /*** 注解获取当前用户ID **/
        if (parameter.hasParameterAnnotation(CurrentUser.class)) {
            return currentUserId;
        }
        /** 给当前对象注入用户登录信息 **/
        else if (Model.class.isAssignableFrom(parameter.getParameterType())) {
            Object target = mavContainer.getModel().getClass();

            return target;
        }
        return null;
    }
}