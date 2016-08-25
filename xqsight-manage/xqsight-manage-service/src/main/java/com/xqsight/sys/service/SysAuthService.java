/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.sys.service;

import java.util.List;

import com.xqsight.sys.model.SysLogin;
import com.xqsight.sys.model.SysMenu;


/**
 * @Description: TODO
 * @author wangganggang
 * @date 2016年1月8日 上午9:22:24
 */
public interface SysAuthService {

	/**
	 * 
	 * saveUserRole保存用户角色对应关系
	 *
	 * @Title: saveUserRole
	 * @Description: TODO
	 * @param @param roleId
	 * @param @param ids    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	 void saveUserRole(int roleId, String... ids);
	 
	 /**
	  * 
	  * saveMenuRole保存菜单角色对应关系
	  *
	  * @Title: saveMenuRole
	  * @Description: TODO
	  * @param @param roleId
	  * @param @param menuIds    设定文件
	  * @return void    返回类型
	  * @throws
	  */
	 void saveMenuRole(int roleId, String... menuIds);
	 
	 /**
	  * 
	  * querAuthUser 查询用户与当前角色的关系
	  *
	  * @Title: querAuthUser
	  * @Description: TODO
	  * @param @param roleId
	  * @param @return    设定文件
	  * @return List<SysLogin>    返回类型
	  * @throws
	  */
	 List<SysLogin> querAuthUser(int roleId);
	 
}
