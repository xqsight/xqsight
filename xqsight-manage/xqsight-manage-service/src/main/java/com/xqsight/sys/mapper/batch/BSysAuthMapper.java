package com.xqsight.sys.mapper.batch;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.xqsight.common.core.dao.annotation.BatchAnnotation;

/**
 * @Description: 系统收权限分派
 * @author wangganggang
 * @date 2016年1月8日 上午9:11:27
 */
@BatchAnnotation
public interface BSysAuthMapper {

	@Insert("insert into sys_menu_role (menu_id,role_id)values(#{menuId, jdbcType=NUMERIC},#{roleId, jdbcType=NUMERIC})")
	void saveMenuRole(@Param("menuId") long menuId, @Param("roleId") long roleId);
	
	@Delete("delete from sys_menu_role where role_id =#{roleId, jdbcType=NUMERIC}")
	void deleMenuRole(@Param("roleId") long roleId);
	
	@Insert("insert into sys_user_role (id,role_id)values(#{id, jdbcType=NUMERIC},#{roleId, jdbcType=NUMERIC})")
	void saveUserRole(@Param("id") long id, @Param("roleId") long roleId);
	
	@Delete("delete from sys_user_role where role_id =#{roleId, jdbcType=NUMERIC}")
	void deleUserRole(@Param("roleId") long roleId);
}
