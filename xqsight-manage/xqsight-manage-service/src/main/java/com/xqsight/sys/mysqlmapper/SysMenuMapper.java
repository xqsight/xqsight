/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.sys.mysqlmapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xqsight.sys.model.SysMenu;

/**
 * @Description: TODO
 * @author wangganggang
 * @date 2015年12月30日 上午10:48:08
 */
public interface SysMenuMapper {

	@Insert("INSERT INTO SYS_MENU (MENU_NAME, URL, TYPE ,ICON, PERMISSION, PARENT_ID,SORT,ACTIVE, CREATE_TIME, CREATE_OPR_ID, UPDATE_TIME, UPD_OPR_ID, REMARK) VALUES("
	+ "#{menuName, jdbcType=VARCHAR},#{url, jdbcType=VARCHAR},#{type, jdbcType=NUMERIC},#{icon, jdbcType=VARCHAR},#{permission, jdbcType=VARCHAR},#{parentId, jdbcType=NUMERIC},#{sort, jdbcType=NUMERIC},#{active, jdbcType=NUMERIC},#{createTime, jdbcType=TIMESTAMP} ,#{createOprId, jdbcType=VARCHAR} , #{updateTime, jdbcType=TIMESTAMP} , #{updOprId, jdbcType=VARCHAR} , #{remark, jdbcType=VARCHAR})")
	void saveSysMenu(SysMenu sysMenu);

	@Update("UPDATE  SYS_MENU SET TYPE=#{type, jdbcType=NUMERIC}, MENU_NAME=#{menuName, jdbcType=VARCHAR},URL=#{url, jdbcType=VARCHAR}, ICON=#{icon, jdbcType=VARCHAR}, PERMISSION=#{permission, jdbcType=VARCHAR},SORT=#{sort, jdbcType=NUMERIC},ACTIVE=#{active, jdbcType=NUMERIC}, UPDATE_TIME= #{updateTime, jdbcType=TIMESTAMP}, UPD_OPR_ID=#{updOprId, jdbcType=VARCHAR}, REMARK=#{remark, jdbcType=VARCHAR} WHERE  MENU_ID=#{menuId, jdbcType=NUMERIC}")
	void updateSysMenu(SysMenu sysMenu);

	@Delete("DELETE FROM SYS_MENU WHERE  MENU_ID=#{menuId, jdbcType=NUMERIC}")
	void deleteSysMenu(@Param("menuId") long menuId);

	@Delete("DELETE FROM SYS_MENU_ROLE WHERE  MENU_ID=#{menuId, jdbcType=NUMERIC}")
	void deleteSysMenuRoleByMenuId(@Param("menuId") long menuId);

	@Select("SELECT * FROM SYS_MENU WHERE MENU_ID=#{menuId, jdbcType=NUMERIC}")
	SysMenu querySysMenuByMenuId(@Param("menuId") long menuId);

	@Select("SELECT * FROM SYS_MENU WHERE MENU_NAME LIKE '%${menuName}%' AND PARENT_ID = #{parentId, jdbcType=NUMERIC} ORDER BY SORT ASC")
	List<SysMenu> querySysMenuByMenuNameAndParentId(@Param("menuName") String menuName, @Param("parentId") long parentId);

	@Select("SELECT MENU_NAME , MENU_ID, ICON, PARENT_ID FROM SYS_MENU WHERE TYPE=0 ORDER BY SORT ASC")
	List<SysMenu> querySysMenuForTree();

	@Select("SELECT SM.MENU_NAME,SM.MENU_ID,SM.PARENT_ID FROM SYS_MENU_ROLE SMR LEFT JOIN SYS_MENU SM  ON SM.MENU_ID = SMR.MENU_ID  WHERE SMR.ROLE_ID = #{roleId, jdbcType=NUMERIC}")
	List<SysMenu> querySyeMenuByRoleId(long roleId);

	@Select("SELECT DISTINCT SM.MENU_ID,SM.MENU_NAME,SM.URL,SM.ICON,SM.PARENT_ID  FROM  SYS_MENU SM LEFT JOIN SYS_MENU_ROLE SMR ON SM.MENU_ID = SMR.MENU_ID LEFT JOIN SYS_USER_ROLE SUR ON SUR.ROLE_ID = SMR.ROLE_ID WHERE TYPE=0 AND SUR.ID =#{userId, jdbcType=NUMERIC}  ORDER BY SM.SORT ASC")
	List<SysMenu> querySysMenuByUser(long userId);

	@Select("SELECT MENU_NAME , MENU_ID, ICON, PARENT_ID FROM SYS_MENU ORDER BY SORT ASC")
	List<SysMenu> querySysMenuAllForTree();
}
