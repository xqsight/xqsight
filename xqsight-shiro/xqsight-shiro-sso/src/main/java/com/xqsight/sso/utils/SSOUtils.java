package com.xqsight.sso.utils;

import com.xqsight.common.model.UserBaseModel;
import com.xqsight.sso.shiro.constants.WebConstants;
import com.xqsight.sso.subject.SSOSubject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * 
 * @author linhaoran
 * @version SSOUtils.java, v 0.1 2015年9月28日 上午9:54:49 linhaoran
 */
public class SSOUtils {

    public static SSOSubject getCurrentUserSubject() {
        return new SSOSubject(SecurityUtils.getSubject());
    }
    
    
    public static UserBaseModel getCurrentUser(){
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession(false);
        return (UserBaseModel) session.getAttribute(WebConstants.CURRENT_USER);
    }

    public static Long getCurrentUserId(){
        return getCurrentUser().getId();
    }
    
    public static Object getSessionAttribute(String key){
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession(false);
        return session.getAttribute(key);
    }
    
    public static void setSessionAttribute(String key, String value){
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession(false);
        session.setAttribute(key, value);
    }

}
