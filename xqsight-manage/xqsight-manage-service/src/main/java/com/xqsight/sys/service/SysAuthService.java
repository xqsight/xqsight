package com.xqsight.sys.service;

import com.xqsight.sys.model.SysLogin;

import java.util.List;


/**
 * @Description: TODO
 * @author wangganggang
 * @date 2016年1月8日 上午9:22:24
 */
public interface SysAuthService {

	/**
	 * 保存用户角色对应关系
	 *
	 * @param roleId
	 * @param ids
     */
	 void saveUserRole(long roleId, Long... ids);

	/**
	 * 保存菜单角色对应关系
	 *
	 * @param roleId
	 * @param menuIds
     */
	 void saveMenuRole(long roleId, Long... menuIds);

	/**
	 * 查询用户与当前角色的关系
	 *
	 * @param roleId
	 * @return
     */
	 List<SysLogin> querAuthUser(long roleId);
	 
}
