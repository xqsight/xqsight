package com.xqsight.sso.shiro.resolver;

import com.xqsight.common.model.Model;
import com.xqsight.sso.shiro.annotation.CurrentUserId;
import com.xqsight.sso.utils.SSOUtils;
import org.springframework.beans.*;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangganggang on 2017/1/17.
 */
public class CurrentUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CurrentUserId.class) || Model.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        long currentUserId = SSOUtils.isAuthenticated() ? SSOUtils.getCurrentUserId() : 0;

        /*** 注解获取当前用户ID **/
        if (parameter.hasParameterAnnotation(CurrentUserId.class)) {
            return currentUserId;
        }
        /** 给当前对象注入用户登录信息 **/
        else if (Model.class.isAssignableFrom(parameter.getParameterType())) {
            String name = parameter.getParameterName();

            // 查找是否已有名为name的参数值的映射，如果没有则创建一个
            Object attribute = mavContainer.containsAttribute(name)
                    ? mavContainer.getModel().get(name)
                    : this.createAttribute(name, parameter, binderFactory, webRequest);
            if (binderFactory != null) {
                WebDataBinder binder = binderFactory.createBinder(webRequest, attribute, name);
                if (binder.getTarget() != null) {
                    // 进行参数绑定
                    this.bindRequestParameters(binder, webRequest);
                }
                // 将参数转到预期类型，第一个参数为解析后的值，第二个参数为绑定Controller参数的类型，第三个参数为绑定的Controller参数
                attribute = binder.convertIfNecessary(binder.getTarget(), parameter.getParameterType(), parameter);
            }
            return attribute;
        }
        return null;
    }

    /**
     * Extension point to create the model attribute if not found in the model.
     * The default implementation uses the default constructor.
     *
     * @param attributeName
     *            the name of the attribute, never {@code null}
     * @param parameter
     *            the method parameter
     * @param binderFactory
     *            for creating WebDataBinder instance
     * @param request
     *            the current request
     * @return the created model attribute, never {@code null}
     */
    protected Object createAttribute(String attributeName, MethodParameter parameter, WebDataBinderFactory binderFactory, NativeWebRequest request)
            throws Exception {

        return BeanUtils.instantiateClass(parameter.getParameterType());
    }

    protected void bindRequestParameters(WebDataBinder binder, NativeWebRequest request) throws UnsupportedEncodingException {
        // 将key-value封装为map，传给bind方法进行参数值绑定
        Map<String, Object> map = new HashMap<>();
        Map<String, String[]> params = request.getParameterMap();

        for (Map.Entry<String, String[]> entry : params.entrySet()) {
            String name = entry.getKey();
            // 执行urldecode
            String value = URLDecoder.decode(entry.getValue()[0], "UTF-8");
            map.put(name, value);
        }

        long currentUserId = SSOUtils.isAuthenticated() ? SSOUtils.getCurrentUserId() : 0;

        map.put("createUserId","" + currentUserId);
        map.put("updateUserId", "" + currentUserId);
        map.put("createTime", LocalDateTime.now());
        map.put("updateTime", LocalDateTime.now());

        PropertyValues propertyValues = new MutablePropertyValues(map);

        // 将K-V绑定到binder.target属性上
        binder.bind(propertyValues);
    }
}