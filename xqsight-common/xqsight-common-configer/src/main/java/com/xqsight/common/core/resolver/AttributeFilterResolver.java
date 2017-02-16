package com.xqsight.common.core.resolver;

import com.xqsight.common.core.CustomerJsonSerializer;
import com.xqsight.common.core.annotation.AttributeFilter;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.util.Arrays;

/**
 * Created by wangganggang on 2017/2/15.
 */
public class AttributeFilterResolver implements HandlerMethodReturnValueHandler {
    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        boolean flag = returnType.hasMethodAnnotation(AttributeFilter.class);
        return returnType.getMethodAnnotation(AttributeFilter.class) != null;
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        // 设置这个就是最终的处理类了，处理完不再去找下一个类进行处理
        mavContainer.setRequestHandled(true);

        // 获得注解并执行filter方法 最后返回
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        Annotation[] annos = returnType.getMethodAnnotations();
        CustomerJsonSerializer jsonSerializer = new CustomerJsonSerializer();
        Arrays.asList(annos).forEach(a -> {
            if (a instanceof AttributeFilter) {
                AttributeFilter json = (AttributeFilter) a;
                jsonSerializer.filter(json.type(), json.include(), json.filter());
            }
        });
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        String json = jsonSerializer.toJson(returnValue);
        response.getWriter().write(json);
    }
}
