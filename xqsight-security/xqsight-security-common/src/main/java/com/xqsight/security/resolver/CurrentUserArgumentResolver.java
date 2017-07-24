package com.xqsight.security.resolver;

import com.xqsight.common.model.constants.Constants;
import com.xqsight.common.model.shiro.BaseUserModel;
import com.xqsight.security.annontation.CurrentUser;
import com.xqsight.security.interception.AuthorizationInterceptor;
import com.xqsight.security.service.UserAuthcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author wangganggang
 * @date 2017年07月22日 下午4:24
 */
public class CurrentUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private UserAuthcService userAuthcService;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().isAssignableFrom(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {

        //获取用户ID
        Object object = nativeWebRequest.getAttribute(Constants.LOGIN_USER_KEY, RequestAttributes.SCOPE_REQUEST);

        if(object == null){
            return null;
        }

        //获取用户信息
        BaseUserModel user = userAuthcService.queryUserById((Long)object);

        return user;

    }
}
