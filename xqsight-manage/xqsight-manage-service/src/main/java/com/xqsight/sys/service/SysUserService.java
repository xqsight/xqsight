package com.xqsight.sys.service;

import com.xqsight.sys.model.SysLogin;

import java.util.List;


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
	void  updateSysLoginPwd(String password, long id);
	
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
	void updUserImg(String imgUrl, long id);
	
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
	void deleteSysLogin(long id);
	
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
	List<SysLogin> querySysLoginByLoginId(String loginId);
	
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
	SysLogin querySysLoginById(long id);
	
	
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
