/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.sso.authc;

import com.xqsight.sso.model.UserBaseModel;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.util.ByteSource;

/**
 * 
 * @author linhaoran
 * @version CustomAuthenticationInfo.java, v 0.1 2015年9月28日 上午10:47:39 linhaoran
 */
public class CustomAuthenticationInfo extends SimpleAuthenticationInfo {

    /**  */
    private static final long serialVersionUID = 4490029324161372170L;
    
    public CustomAuthenticationInfo(UserBaseModel user, Object principal, Object hashedCredentials, ByteSource credentialsSalt, String realmName){
        super(principal, hashedCredentials, credentialsSalt, realmName);
        this.setUserBaseModel(user);
    }

    /**
     * 
     */
    public CustomAuthenticationInfo() {
    }

    private UserBaseModel userBaseModel;

    /**
     * Getter method for property <tt>userBaseModel</tt>.
     * 
     * @return property value of userBaseModel
     */
    public UserBaseModel getUserBaseModel() {
        return userBaseModel;
    }

    /**
     * Setter method for property <tt>userBaseModel</tt>.
     * 
     * @param userBaseModel value to be assigned to property userBaseModel
     */
    public void setUserBaseModel(UserBaseModel userBaseModel) {
        this.userBaseModel = userBaseModel;
    }
    
    @Override
    public void merge(AuthenticationInfo info) {
        super.merge(info);
        if (this.userBaseModel == null && info instanceof CustomAuthenticationInfo) {
            this.userBaseModel = ((CustomAuthenticationInfo) info).getUserBaseModel();
        }
    }
}
