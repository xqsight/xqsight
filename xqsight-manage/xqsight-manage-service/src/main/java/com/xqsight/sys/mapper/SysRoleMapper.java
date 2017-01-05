package com.xqsight.sys.mapper;

import com.xqsight.sys.model.SysRole;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Description: TODO
 * @author wangganggang
 * @date 2015年12月30日 上午10:48:08
 */
public interface SysRoleMapper {

	@Insert("insert into sys_role (role_name, role_code, role_type, active, create_time, create_opr_id, remark) values("
	+ "#{roleName, jdbcType=VARCHAR},#{roleCode, jdbcType=VARCHAR},#{roleType, jdbcType=NUMERIC},#{active, jdbcType=NUMERIC},#{createTime, jdbcType=TIMESTAMP} ,#{createOprId, jdbcType=VARCHAR} , #{remark, jdbcType=VARCHAR})")
	void saveSysRole(SysRole sysRole);

	@Update("update  sys_role set role_name=#{roleName, jdbcType=VARCHAR},role_code=#{roleCode, jdbcType=VARCHAR},active=#{active, jdbcType=NUMERIC}, update_time= #{updateTime, jdbcType=TIMESTAMP}, update_user_id=#{updateUserId, jdbcType=VARCHAR}, remark=#{remark, jdbcType=VARCHAR} WHERE  ROLE_ID=#{roleId, jdbcType=NUMERIC}")
	void updateSysRole(SysRole sysRole);

	@Delete("delete from sys_role where role_id=#{roleId, jdbcType=NUMERIC}")
	void deleteSysRole(@Param("roleId") long roleId);

	@Select("select * from sys_role where role_name like #{roleName, jdbcType=VARCHAR} order by role_name asc")
	List<SysRole> querySysRoleByRoleName(@Param("roleName") String roleName);
	
	@Select("select * from sys_role where role_id=#{roleId, jdbcType=NUMERIC}")
	SysRole querySysRoleByRoleId(@Param("roleId") long roleId);
}
