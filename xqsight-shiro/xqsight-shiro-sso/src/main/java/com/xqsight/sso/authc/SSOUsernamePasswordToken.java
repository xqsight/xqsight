/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.sso.authc;

import com.xqsight.sso.enums.UserType;
import com.xqsight.sso.shiro.authc.PersonalUserToken;
import com.xqsight.sso.shiro.authc.SysUserToken;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 
 * @author linhaoran
 * @version SSOUsernamePasswordToken.java, v 0.1 2015年9月29日 下午11:37:01 linhaoran
 */
public class SSOUsernamePasswordToken {
    
    private UserType ut;
    private UsernamePasswordToken upToken;
    
    public SSOUsernamePasswordToken(String username, String password, boolean isRememberMe, UserType ut){
        this.ut = ut;
        switch(ut){
            case PERSON:
                upToken = new PersonalUserToken(username, password, isRememberMe);
                break;
            default:
                upToken = new SysUserToken(username, password, isRememberMe);
                break;
        }
    }
    
    /*--------------------------------------------
    |  A C C E S S O R S / M O D I F I E R S    |
    ============================================*/

    /**
     * Getter method for property <tt>ut</tt>.
     * 
     * @return property value of ut
     */
    public UserType getUt() {
        return ut;
    }

    /**
     * Setter method for property <tt>ut</tt>.
     * 
     * @param ut value to be assigned to property ut
     */
    public void setUt(UserType ut) {
        this.ut = ut;
    }

    /**
     * Getter method for property <tt>upToken</tt>.
     * 
     * @return property value of upToken
     */
    public UsernamePasswordToken getUpToken() {
        return upToken;
    }

    /**
     * Setter method for property <tt>upToken</tt>.
     * 
     * @param upToken value to be assigned to property upToken
     */
    public void setUpToken(UsernamePasswordToken upToken) {
        this.upToken = upToken;
    }

    /**
     * Returns the username submitted during an authentication attempt.
     *
     * @return the username submitted during an authentication attempt.
     */
    public String getUsername() {
        return this.upToken.getUsername();
    }

    /**
     * Sets the username for submission during an authentication attempt.
     *
     * @param username the username to be used for submission during an authentication attempt.
     */
    public void setUsername(String username) {
        this.upToken.setUsername(username);
    }


    /**
     * Returns the password submitted during an authentication attempt as a character array.
     *
     * @return the password submitted during an authentication attempt as a character array.
     */
    public char[] getPassword() {
        return this.upToken.getPassword();
    }

    /**
     * Sets the password for submission during an authentication attempt.
     *
     * @param password the password to be used for submission during an authentication attemp.
     */
    public void setPassword(char[] password) {
        this.upToken.setPassword(password);
    }

    /**
     * Simply returns {@link #getUsername() getUsername()}.
     *
     * @return the {@link #getUsername() username}.
     * @see org.apache.shiro.authc.AuthenticationToken#getPrincipal()
     */
    public Object getPrincipal() {
        return this.upToken.getUsername();
    }

    /**
     * Returns the {@link #getPassword() password} char array.
     *
     * @return the {@link #getPassword() password} char array.
     * @see org.apache.shiro.authc.AuthenticationToken#getCredentials()
     */
    public Object getCredentials() {
        return this.upToken.getPassword();
    }

    /**
     * Returns the host name or IP string from where the authentication attempt occurs.  May be <tt>null</tt> if the
     * host name/IP is unknown or explicitly omitted.  It is up to the Authenticator implementation processing this
     * token if an authentication attempt without a host is valid or not.
     * <p/>
     * <p>(Shiro's default Authenticator allows <tt>null</tt> hosts to support localhost and proxy server environments).</p>
     *
     * @return the host from where the authentication attempt occurs, or <tt>null</tt> if it is unknown or
     *         explicitly omitted.
     * @since 1.0
     */
    public String getHost() {
        return this.upToken.getHost();
    }

    /**
     * Sets the host name or IP string from where the authentication attempt occurs.  It is up to the Authenticator
     * implementation processing this token if an authentication attempt without a host is valid or not.
     * <p/>
     * <p>(Shiro's default Authenticator
     * allows <tt>null</tt> hosts to allow localhost and proxy server environments).</p>
     *
     * @param host the host name or IP string from where the attempt is occuring
     * @since 1.0
     */
    public void setHost(String host) {
        this.upToken.setHost(host);
    }

    /**
     * Returns <tt>true</tt> if the submitting user wishes their identity (principal(s)) to be remembered
     * across sessions, <tt>false</tt> otherwise.  Unless overridden, this value is <tt>false</tt> by default.
     *
     * @return <tt>true</tt> if the submitting user wishes their identity (principal(s)) to be remembered
     *         across sessions, <tt>false</tt> otherwise (<tt>false</tt> by default).
     * @since 0.9
     */
    public boolean isRememberMe() {
        return this.upToken.isRememberMe();
    }

    /**
     * Sets if the submitting user wishes their identity (pricipal(s)) to be remembered across sessions.  Unless
     * overridden, the default value is <tt>false</tt>, indicating <em>not</em> to be remembered across sessions.
     *
     * @param rememberMe value inidicating if the user wishes their identity (principal(s)) to be remembered across
     *                   sessions.
     * @since 0.9
     */
    public void setRememberMe(boolean rememberMe) {
        this.upToken.setRememberMe(rememberMe);
    }

    /*--------------------------------------------
    |               M E T H O D S               |
    ============================================*/

    /**
     * Clears out (nulls) the username, password, rememberMe, and inetAddress.  The password bytes are explicitly set to
     * <tt>0x00</tt> before nulling to eliminate the possibility of memory access at a later time.
     */
    public void clear() {
        this.upToken.setUsername(null);
        this.upToken.setHost(null);
        this.upToken.setRememberMe(false);

        if (this.upToken.getPassword() != null) {
            for (int i = 0; i < this.upToken.getPassword().length; i++) {
                this.upToken.getPassword()[i] = 0x00;
            }
            this.upToken.setPassword(null);
        }

    }

    /**
     * Returns the String representation.  It does not include the password in the resulting
     * string for security reasons to prevent accidentially printing out a password
     * that might be widely viewable).
     *
     * @return the String representation of the <tt>UsernamePasswordToken</tt>, omitting
     *         the password.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getName());
        sb.append(" - ");
        sb.append(this.upToken.getUsername());
        sb.append(", rememberMe=").append(this.upToken.isRememberMe());
        if (this.upToken.getHost() != null) {
            sb.append(" (").append(this.upToken.getHost()).append(")");
        }
        return sb.toString();
    }
    

}
