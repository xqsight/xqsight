/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.sys.mysqlmapper;

import com.xqsight.sys.model.SysMenu;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Description: TODO
 * @author wangganggang
 * @date 2015年12月30日 上午10:48:08
 */
public interface SysMenuMapper {

	@Insert("insert into sys_menu (menu_name, url, type ,icon, permission, parent_id,sort,active, create_time, create_opr_id, update_time, upd_opr_id, remark) values("
	+ "#{menuName, jdbcType=VARCHAR},#{url, jdbcType=VARCHAR},#{type, jdbcType=NUMERIC},#{icon, jdbcType=VARCHAR},#{permission, jdbcType=VARCHAR},#{parentId, jdbcType=NUMERIC},#{sort, jdbcType=NUMERIC},#{active, jdbcType=NUMERIC},#{createTime, jdbcType=TIMESTAMP} ,#{createOprId, jdbcType=VARCHAR} , #{updateTime, jdbcType=TIMESTAMP} , #{updOprId, jdbcType=VARCHAR} , #{remark, jdbcType=VARCHAR})")
	void saveSysMenu(SysMenu sysMenu);

	@Update("update  sys_menu set type=#{type, jdbcType=NUMERIC}, menu_name=#{menuName, jdbcType=VARCHAR},url=#{url, jdbcType=VARCHAR}, icon=#{icon, jdbcType=VARCHAR}, permission=#{permission, jdbcType=VARCHAR},sort=#{sort, jdbcType=NUMERIC},active=#{active, jdbcType=NUMERIC}, update_time= #{updateTime, jdbcType=TIMESTAMP}, upd_opr_id=#{updOprId, jdbcType=VARCHAR}, REMARK=#{remark, jdbcType=VARCHAR} where  menu_id=#{menuId, jdbcType=NUMERIC}")
	void updateSysMenu(SysMenu sysMenu);

	@Delete("delete from sys_menu where  menu_id=#{menuId, jdbcType=NUMERIC}")
	void deleteSysMenu(@Param("menuId") long menuId);

	@Delete("delete from sys_menu_role where  menu_id=#{menuId, jdbcType=NUMERIC}")
	void deleteSysMenuRoleByMenuId(@Param("menuId") long menuId);

	@Select("select * from sys_menu where menu_id=#{menuId, jdbcType=NUMERIC}")
	SysMenu querySysMenuByMenuId(@Param("menuId") long menuId);

	@Select("select * from sys_menu where menu_name like '%${menuName}%' and parent_id = #{parentId, jdbcType=NUMERIC} order by sort asc")
	List<SysMenu> querySysMenuByMenuNameAndParentId(@Param("menuName") String menuName, @Param("parentId") long parentId);

	@Select("select menu_name , menu_id, icon, parent_id from sys_menu where type=0 order by sort asc")
	List<SysMenu> querySysMenuForTree();

	@Select("select sm.menu_name,sm.menu_id,sm.parent_id from sys_menu_role smr left join sys_menu sm  on sm.menu_id = smr.menu_id  where smr.role_id = #{roleId, jdbcType=NUMERIC}")
	List<SysMenu> querySyeMenuByRoleId(long roleId);

	@Select("select distinct sm.menu_id,sm.menu_name,sm.url,sm.icon,sm.parent_id  from  sys_menu sm left join sys_menu_role smr on sm.menu_id = smr.menu_id left join sys_user_role sur on sur.role_id = smr.role_id where type=0 and sur.id =#{userId, jdbcType=NUMERIC}  order by sm.sort asc")
	List<SysMenu> querySysMenuByUser(long userId);

	@Select("select menu_name , menu_id, icon, parent_id from sys_menu order by sort asc")
	List<SysMenu> querySysMenuAllForTree();

	@Select("select * from sys_menu")
	List<SysMenu> querySysMenu();

}
