/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.sso.subject;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.ExecutionException;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.xqsight.sso.authc.SSOUsernamePasswordToken;

/**
 * 
 * @author linhaoran
 * @version SSOSubject.java, v 0.1 2015年9月29日 下午11:34:52 linhaoran
 */
public class SSOSubject {
    
    private Subject subject;
    
    public SSOSubject(Subject subject) {
        this.subject = subject;
    }
    
    /**
     * Getter method for property <tt>subject</tt>.
     * 
     * @return property value of subject
     */
    public Subject getSubject() {
        return subject;
    }

    /**
     * Setter method for property <tt>subject</tt>.
     * 
     * @param subject value to be assigned to property subject
     */
    public void setSubject(Subject subject) {
        this.subject = subject;
    }
    /** 
     * @see Subject#getPrincipal()
     */
    
    public Object getPrincipal() {
        return subject.getPrincipal();
    }
    /** 
     * @see Subject#getPrincipals()
     */
    
    public PrincipalCollection getPrincipals() {
        return subject.getPrincipals();
    }
    /** 
     * @see Subject#isPermitted(String)
     */
    
    public boolean isPermitted(String permission) {
        return subject.isPermitted(permission);
    }
    /** 
     * @see Subject#isPermitted(Permission)
     */
    
    public boolean isPermitted(Permission permission) {
        return subject.isPermitted(permission);
    }
    /** 
     * @see Subject#isPermitted(String[])
     */
    
    public boolean[] isPermitted(String... permissions) {
        return subject.isPermitted(permissions);
    }
    /** 
     * @see Subject#isPermitted(List)
     */
    
    public boolean[] isPermitted(List<Permission> permissions) {
        return subject.isPermitted(permissions);
    }
    /** 
     * @see Subject#isPermittedAll(String[])
     */
    
    public boolean isPermittedAll(String... permissions) {
        return subject.isPermittedAll(permissions);
    }
    /** 
     * @see Subject#isPermittedAll(Collection)
     */
    
    public boolean isPermittedAll(Collection<Permission> permissions) {
        return subject.isPermittedAll(permissions);
    }
    /** 
     * @see Subject#checkPermission(String)
     */
    
    public void checkPermission(String permission) throws AuthorizationException {
        subject.checkPermission(permission);
    }
    /** 
     * @see Subject#checkPermission(Permission)
     */
    
    public void checkPermission(Permission permission) throws AuthorizationException {
        subject.checkPermission(permission);
    }
    /** 
     * @see Subject#checkPermissions(String[])
     */
    
    public void checkPermissions(String... permissions) throws AuthorizationException {
        subject.checkPermissions(permissions);
    }
    /** 
     * @see Subject#checkPermissions(Collection)
     */
    
    public void checkPermissions(Collection<Permission> permissions) throws AuthorizationException {
        subject.checkPermissions(permissions);
    }
    /** 
     * @see Subject#hasRole(String)
     */
    
    public boolean hasRole(String roleIdentifier) {
        return subject.hasRole(roleIdentifier);
    }
    /** 
     * @see Subject#hasRoles(List)
     */
    
    public boolean[] hasRoles(List<String> roleIdentifiers) {
        return subject.hasRoles(roleIdentifiers);
    }
    /** 
     * @see Subject#hasAllRoles(Collection)
     */
    
    public boolean hasAllRoles(Collection<String> roleIdentifiers) {
        return subject.hasAllRoles(roleIdentifiers);
    }
    /** 
     * @see Subject#checkRole(String)
     */
    
    public void checkRole(String roleIdentifier) throws AuthorizationException {
        subject.checkRole(roleIdentifier);
    }
    /** 
     * @see Subject#checkRoles(Collection)
     */
    
    public void checkRoles(Collection<String> roleIdentifiers) throws AuthorizationException {
        subject.checkRoles(roleIdentifiers);
    }
    /** 
     * @see Subject#checkRoles(String[])
     */
    
    public void checkRoles(String... roleIdentifiers) throws AuthorizationException {
        subject.checkRoles(roleIdentifiers);
    }
    /** 
     * @see Subject#login(org.apache.shiro.authc.AuthenticationToken)
     */
    
    public void login(SSOUsernamePasswordToken token) throws AuthenticationException {
        subject.login(token.getUpToken());
    }
    /** 
     * @see Subject#isAuthenticated()
     */
    
    public boolean isAuthenticated() {
        return subject.isAuthenticated();
    }
    /** 
     * @see Subject#isRemembered()
     */
    
    public boolean isRemembered() {
        return subject.isAuthenticated();
    }
    /** 
     * @see Subject#getSession()
     */
    
    public Session getSession() {
        return subject.getSession();
    }
    /** 
     * @see Subject#getSession(boolean)
     */
    
    public Session getSession(boolean create) {
        return subject.getSession(create);
    }
    /** 
     * @see Subject#logout()
     */
    
    public void logout() {
        subject.logout();
    }
    /** 
     * @see Subject#execute(Callable)
     */
    
    public <V> V execute(Callable<V> callable) throws ExecutionException {
        return subject.execute(callable);
    }
    /** 
     * @see Subject#execute(Runnable)
     */
    
    public void execute(Runnable runnable) {
        subject.execute(runnable);
    }
    /** 
     * @see Subject#associateWith(Callable)
     */
    
    public <V> Callable<V> associateWith(Callable<V> callable) {
        return subject.associateWith(callable);
    }
    /** 
     * @see Subject#associateWith(Runnable)
     */
    
    public Runnable associateWith(Runnable runnable) {
        return subject.associateWith(runnable);
    }
    /** 
     * @see Subject#runAs(PrincipalCollection)
     */
    
    public void runAs(PrincipalCollection principals) throws NullPointerException, IllegalStateException {
        subject.runAs(principals);
    }
    /** 
     * @see Subject#isRunAs()
     */
    
    public boolean isRunAs() {
        return subject.isRunAs();
    }
    /** 
     * @see Subject#getPreviousPrincipals()
     */
    
    public PrincipalCollection getPreviousPrincipals() {
        return subject.getPreviousPrincipals();
    }
    /** 
     * @see Subject#releaseRunAs()
     */
    
    public PrincipalCollection releaseRunAs() {
        return subject.releaseRunAs();
    }

}
