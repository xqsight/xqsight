package com.xqsight.sso.authc.service;

public interface RoleAuthcService {
	
    //添加角色-权限之间关系
    void correlationPermissions(Long roleId, Long... permissionIds);
    
    //移除角色-权限之间关系
   void uncorrelationPermissions(Long roleId, Long... permissionIds);
}
