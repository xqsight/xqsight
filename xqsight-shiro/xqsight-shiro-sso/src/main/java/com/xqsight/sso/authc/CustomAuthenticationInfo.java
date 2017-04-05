package com.xqsight.sso.authc;

import com.xqsight.common.model.shiro.BaseUserModel;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.util.ByteSource;

public class CustomAuthenticationInfo extends SimpleAuthenticationInfo {

    /**  */
    private static final long serialVersionUID = 4490029324161372170L;
    
    public CustomAuthenticationInfo(BaseUserModel user, Object principal, Object hashedCredentials, ByteSource credentialsSalt, String realmName){
        super(principal, hashedCredentials, credentialsSalt, realmName);
        this.setBaseUserModel(user);
    }

    /**
     * 
     */
    public CustomAuthenticationInfo() {
    }

    private BaseUserModel baseUserModel;

    public BaseUserModel getBaseUserModel() {
        return baseUserModel;
    }

    public void setBaseUserModel(BaseUserModel baseUserModel) {
        this.baseUserModel = baseUserModel;
    }

    @Override
    public void merge(AuthenticationInfo info) {
        super.merge(info);
        if (this.baseUserModel == null && info instanceof CustomAuthenticationInfo) {
            this.baseUserModel = ((CustomAuthenticationInfo) info).getBaseUserModel();
        }
    }
}
