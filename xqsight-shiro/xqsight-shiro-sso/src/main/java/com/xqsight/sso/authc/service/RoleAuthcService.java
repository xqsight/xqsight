/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.sso.authc.service;

/**
 * 
 * @author linhaoran
 * @version RoleAuthcService.java, v 0.1 2015年8月3日 下午5:39:32 linhaoran
 */
public interface RoleAuthcService {
	
    //添加角色-权限之间关系
    void correlationPermissions(Long roleId, Long... permissionIds);
    
    //移除角色-权限之间关系
   void uncorrelationPermissions(Long roleId, Long... permissionIds);
}
