package com.xqsight.security.utils;

import com.xqsight.common.exception.UnAuthcException;
import com.xqsight.common.exception.constants.ErrorMessageConstants;
import com.xqsight.common.model.shiro.BaseUserModel;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * @author wangganggang
 * @date 2017年07月22日 下午1:54
 */
public class SSOUtils {

    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static BaseUserModel getUserEntity() {
        return (BaseUserModel)SecurityUtils.getSubject().getPrincipal();
    }

    public static Long getCurrentUserId() {
        return getUserEntity().getId();
    }

    public static void setSessionAttribute(Object key, Object value) {
        getSession().setAttribute(key, value);
    }

    public static Object getSessionAttribute(Object key) {
        return getSession().getAttribute(key);
    }

    public static boolean isAuthenticated() {
        return SecurityUtils.getSubject().getPrincipal() != null;
    }

    public static void logout() {
        SecurityUtils.getSubject().logout();
    }

    public static String getKaptcha(String key) {
        Object kaptcha = getSessionAttribute(key);
        if(kaptcha == null){
            throw new UnAuthcException(ErrorMessageConstants.ERROR_40004,"验证码已失效");
        }
        getSession().removeAttribute(key);
        return kaptcha.toString();
    }

}
