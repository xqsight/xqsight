package com.xqsight.common.model;



import com.xqsight.common.model.shiro.AbstractUser;

import java.io.Serializable;


public class UserBaseModel extends AbstractUser implements Serializable {

    /** 用户名 **/
    private String     userName;

    /**
     * 登录类型
     */
    private int  loginType;

    /**
     * Getter method for property <tt>username</tt>.
     *
     * @return property value of username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Setter method for property <tt>username</tt>.
     *
     * @param userName value to be assigned to property username
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getLoginType() {
        return loginType;
    }

    public void setLoginType(int loginType) {
        this.loginType = loginType;
    }

}
