package com.xqsight.sys.service;

import com.xqsight.sys.model.SysMenu;

import java.util.List;


/**
 * @Description: TODO
 * @author wangganggang
 * @date 2015年12月30日 上午11:44:35
 */
public interface SysMenuService {

	/**
	 * 新增
	 *
	 * @Title: saveSysMenu
	 * @Description: TODO
	 * @param @param sysMenu    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void saveSysMenu(SysMenu sysMenu);
	
	/**
	 * 
	 * updateSysMenu 修改
	 * @Title: updateSysMenu
	 * @Description: TODO
	 * @param @param sysMenu    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void  updateSysMenu(SysMenu sysMenu);
	
	/**
	 * 
	 * deleteSysMenu 删除 
	 *
	 * @Title: deleteSysMenu
	 * @Description: TODO
	 * @param @param menuId    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void deleteSysMenu(long menuId);

	/**
	 *
	 * querySysMenuByMenuName 查询
	 *
	 * @Title: querySysMenuByMenuId
	 * @Description: TODO
	 * @param @param menuId
	 * @param @return    设定文件
	 * @return SysMenu    返回类型
	 * @throws
	 */
	SysMenu querySysMenuByMenuId(long menuId);

	/**
	 * 查询菜单
	 * @param menuName
	 * @param parentId
     * @return
     */
	List<SysMenu> querySysMenuByMenuNameAndParentId(String menuName, long parentId);

	/**
	 * querySysMenu 查询菜单
	 * @return
     */
	SysMenu querySysMenuForTree();

	/**
	 * 所有菜单转成树
	 * @return
     */
	SysMenu queryMenuAllConvertTree();

	/**
	 * 根据角色查询菜单
	 * @param roleId
	 * @return
     */
	List<SysMenu> querySyeMenuByRoleId(long roleId);

	/**
	 * 根据用户查询菜单
	 *
	 * @param userId
	 * @return
     */
	List<SysMenu> querySysMenuByUser(long userId);

	/**
	 * querySysMenu 查询菜单
	 * @return
	 */
	List<SysMenu> querySysMenu();
}
