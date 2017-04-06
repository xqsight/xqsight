package com.xqsight.system.mapper;

import com.xqsight.common.base.dao.ICrudDao;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author wangganggang
 * @Description: 系统收权限分派
 * @date 2016年1月8日 上午9:11:27
 */
public interface SysAuthMapper extends ICrudDao {

    @Insert("insert into sys_menu_role (menu_id,role_id)values(#{menuId, jdbcType=NUMERIC},#{roleId, jdbcType=NUMERIC})")
    void saveMenuRole(@Param("menuId") long menuId, @Param("roleId") long roleId);

    @Delete("delete from sys_menu_role where role_id =#{roleId, jdbcType=NUMERIC}")
    void deleMenuRole(@Param("roleId") long roleId);

    @Insert("insert into sys_user_role (id,role_id)values(#{id, jdbcType=NUMERIC},#{roleId, jdbcType=NUMERIC})")
    void saveUserRole(@Param("id") long id, @Param("roleId") long roleId);

    @Delete("delete from sys_user_role where role_id =#{roleId, jdbcType=NUMERIC}")
    void deleUserRole(@Param("roleId") long roleId);

    @Select("select id from sys_user_role where role_id = #{roleId, jdbcType=NUMERIC}")
    List<String> queryUserIdByRole(long roleId);

    @Select("select role_id from sys_user_role where id = #{userId, jdbcType=NUMERIC}")
    List<String> queryRoleIdByuser(long userId);

    @Select("select menu_id from sys_menu_role where role_id = #{roleId, jdbcType=NUMERIC}")
    List<String> queryMenuIdByRole(long roleId);
}
