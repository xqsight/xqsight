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

import com.xqsight.sys.model.SysRole;

/**
 * @Description: TODO
 * @author wangganggang
 * @date 2015年12月30日 上午10:48:08
 */
public interface SysRoleMapper {

	@Insert("INSERT INTO SYS_ROLE (ROLE_NAME, ROLE_CODE, ROLE_TYPE, ACTIVE, CREATE_TIME, CREATE_OPR_ID, REMARK) VALUES("
	+ "#{roleName, jdbcType=VARCHAR},#{roleCode, jdbcType=VARCHAR},#{roleType, jdbcType=NUMERIC},#{active, jdbcType=NUMERIC},#{createTime, jdbcType=TIMESTAMP} ,#{createOprId, jdbcType=VARCHAR} , #{remark, jdbcType=VARCHAR})")
	void saveSysRole(SysRole sysRole);

	@Update("UPDATE  SYS_ROLE SET ROLE_NAME=#{roleName, jdbcType=VARCHAR},ROLE_CODE=#{roleCode, jdbcType=VARCHAR},active=#{active, jdbcType=NUMERIC}, update_time= #{updateTime, jdbcType=TIMESTAMP}, upd_opr_id=#{updOprId, jdbcType=VARCHAR}, remark=#{remark, jdbcType=VARCHAR} WHERE  ROLE_ID=#{roleId, jdbcType=NUMERIC}")
	void updateSysRole(SysRole sysRole);

	@Delete("DELETE FROM SYS_ROLE WHERE  ROLE_ID=#{roleId, jdbcType=NUMERIC}")
	void deleteSysRole(@Param("roleId") int roleId);

	@Select("SELECT * FROM SYS_ROLE WHERE ROLE_NAME LIKE #{roleName, jdbcType=VARCHAR} ORDER BY ROLE_NAME ASC")
	List<SysRole> querySysRoleByRoleName(@Param("roleName") String roleName);
	
	@Select("SELECT * FROM SYS_ROLE WHERE ROLE_ID=#{roleId, jdbcType=NUMERIC}")
	SysRole querySysRoleByRoleId(@Param("roleId") int roleId);
}
