/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.sso.shiro.model;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author linhaoran
 * @version AbstractUser.java, v 0.1 2015年8月3日 下午5:41:34 linhaoran
 */
public abstract class AbstractUser implements Serializable{

    /** 用户内码 **/
    private Long       id;

    /** 登录编号 **/
    private String    loginId;

    /** 密码 **/
    private String     password;

    /** 加密密码的盐 **/
    private String     salt;

    /** 拥有的角色列表 **/
    private List<Long> roleIds;

    /** 是否锁定 0-未锁定 -1-锁定*/
    private int locked;

    /**
     * Getter method for property <tt>id</tt>.
     * 
     * @return property value of id
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter method for property <tt>id</tt>.
     * 
     * @param id value to be assigned to property id
     */
    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    /**
     * Getter method for property <tt>password</tt>.
     * 
     * @return property value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter method for property <tt>password</tt>.
     * 
     * @param password value to be assigned to property password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter method for property <tt>salt</tt>.
     * 
     * @return property value of salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     * Setter method for property <tt>salt</tt>.
     * 
     * @param salt value to be assigned to property salt
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getLocked() {
        return locked;
    }

    public void setLocked(int locked) {
        this.locked = locked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        AbstractUser user = (AbstractUser) o;

        if (id != null ? !id.equals(user.id) : user.id != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", password='" + password + '\'' + ", salt='" + salt + '\'' + ", roleIds="
               + roleIds + ", locked=" + locked + '}';
    }

    /**
     *  判断是否锁定
     * @return
     */
    public boolean isUserLocked(){
        return this.locked == -1;
    }
    /**
     * 
     * @return
     */
    public String getCredentialsSalt() {
        return loginId + salt;
    }
}
