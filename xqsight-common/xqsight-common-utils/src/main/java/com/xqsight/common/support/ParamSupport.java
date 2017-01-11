package com.xqsight.common.support;

import com.xqsight.common.model.constants.Constants;
import com.xqsight.common.utils.ReflectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by wangganggang on 2016/12/30.
 */
public class ParamSupport {

    public static final String ACTIVE = "active";

    /**
     * 修改实体对象里面的状态为Invalidate，表明这个订单现在无效
     *
     * @param object 实体类并且实现了Serializable接口的类
     */
    public static void setModelInvalidStatus(Serializable object) {
        if (ReflectionUtils.getDeclaredField(object, ACTIVE) != null) {
            ReflectionUtils.invokeSetterMethod(object, ACTIVE, Constants.NO);
        }
    }

    public static Map<String, Object> getParametersStartingWith(HttpServletRequest request, String prefix) {
        Enumeration<String> paramNames = (Enumeration<String>) request.getParameterNames();
        Map<String, Object> params = new TreeMap<String, Object>();
        if (prefix == null) {
            prefix = "";
        }
        while (paramNames != null && paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            if ("".equals(prefix) || paramName.startsWith(prefix)) {
                String unprefixed = paramName.substring(prefix.length());
                String[] values = request.getParameterValues(paramName);
                if (values == null || values.length == 0) {
                    //NOSONAR, Do nothing, no values found at all.
                } else if (values.length > 1) {
                    params.put(unprefixed, values);
                } else {
                    params.put(unprefixed, values[0]);
                }
            }
        }
        return params;
    }
}
