/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.sys.service;

import java.util.List;

import com.xqsight.sso.model.UserBaseModel;
import com.xqsight.sys.model.SysLogin;

/**
 * @Description: TODO
 * @author wangganggang
 * @date 2015年12月30日 上午11:44:35
 */
public interface SysUserService {

	/**
	 * 新增
	 *
	 * @Title: saveSysLogin
	 * @Description: TODO
	 * @param @param sysLogin    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void saveSysLogin(SysLogin sysLogin);
	
	/**
	 * 
	 * updateSysLogin 修改
	 * @Title: updateSysLogin
	 * @Description: TODO
	 * @param @param password    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void  updateSysLoginPwd(String password, Long id);
	
	/**
	 * 修改用户名，年龄，性别
	 * @Description: TODO
	 *
	 * @Title: updUserName
	 * @param @param sysLogin    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void updSysLogin(SysLogin sysLogin);

	/**
	 * 修改图片
	 * @Description: TODO
	 *
	 * @Title: updUserImg
	 * @param @param imgUrl    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void updUserImg(String imgUrl, Long id);
	
	/**
	 * 
	 * deleteSysLogin 删除 
	 *
	 * @Title: deleteSysLogin
	 * @Description: TODO
	 * @param @param loginId    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void deleteSysLogin(Long id);
	
	/**
	 * 
	 * querySysLogin 查询
	 *
	 * @Title: querySysLogin
	 * @Description: TODO
	 * @param @return    设定文件
	 * @return List<SysLogin>    返回类型
	 * @throws
	 */
	List<SysLogin> querySysLoginByUserNameAndLoginIdAndOrgId(String userName, String loginId, Long orgId);
	
	/**
	 * 
	 * querySysLoginById查询
	 *
	 * @Title: querySysLoginById
	 * @Description: TODO
	 * @param @param id
	 * @param @return    设定文件
	 * @return SysLogin    返回类型
	 * @throws
	 */
	SysLogin querySysLoginById(Long id);
	
	
	/**
	 * 
	 * querySysLogin 查询
	 *
	 * @Title: querySysLogin
	 * @Description: TODO
	 * @param @return    设定文件
	 * @return List<SysLogin>    返回类型
	 * @throws
	 */
	SysLogin querySingleUserByLoginId(String loginId);

}
