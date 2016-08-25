/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.sys.mysqlbatchmapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.xqsight.common.dao.batch.MysqlBatchAnnotation;

/**
 * @Description: 系统收权限分派
 * @author wangganggang
 * @date 2016年1月8日 上午9:11:27
 */
@MysqlBatchAnnotation
public interface BSysAuthMapper {

	@Insert("INSERT INTO SYS_MENU_ROLE (MENU_ID,ROLE_ID)values(#{menuId, jdbcType=NUMERIC},#{roleId, jdbcType=NUMERIC})")
	void saveMenuRole(@Param("menuId") int menuId, @Param("roleId") int roleId);
	
	@Delete("DELETE FROM SYS_MENU_ROLE WHERE ROLE_ID =#{roleId, jdbcType=NUMERIC}")
	void deleMenuRole(@Param("roleId") int roleId);
	
	@Insert("INSERT INTO SYS_USER_ROLE (ID,ROLE_ID)values(#{id, jdbcType=NUMERIC},#{roleId, jdbcType=NUMERIC})")
	void saveUserRole(@Param("id") int id, @Param("roleId") int roleId);
	
	@Delete("DELETE FROM SYS_USER_ROLE WHERE ROLE_ID =#{roleId, jdbcType=NUMERIC}")
	void deleUserRole(@Param("roleId") int roleId);
}
