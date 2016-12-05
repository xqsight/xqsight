package com.xqsight.sys.mysqlmapper;

import com.xqsight.sys.model.SysLogin;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Description: TODO
 * @author wangganggang
 * @date 2015年12月30日 上午10:48:08
 */
public interface SysUserMapper {

	@Insert("insert into sys_login (org_id,login_id,user_name, password, login_type, from_source, img_url, salt, locked,age,sex, active, create_time, create_opr_id, remark) values("
	+ "#{orgId, jdbcType=NUMERIC},#{loginId, jdbcType=VARCHAR},#{userName, jdbcType=VARCHAR},#{password, jdbcType=VARCHAR},#{loginType, jdbcType=NUMERIC},#{fromSource, jdbcType=VARCHAR},#{imgUrl, jdbcType=VARCHAR},#{salt, jdbcType=VARCHAR},#{locked, jdbcType=NUMERIC},#{age, jdbcType=NUMERIC},#{sex, jdbcType=NUMERIC},#{active, jdbcType=NUMERIC},#{createTime, jdbcType=TIMESTAMP} ,#{createOprId, jdbcType=VARCHAR} , #{remark, jdbcType=VARCHAR})")
	void saveSysLogin(SysLogin sysLogin);
	
	@Update("update  sys_login set password=#{password, jdbcType=VARCHAR}  where  id=#{id, jdbcType=NUMERIC}")
	void updateSysLoginPwd(@Param("password") String password, @Param("id") long id);

	@Update("update  sys_login set img_url=#{imgUrl, jdbcType=VARCHAR} where  id=#{id, jdbcType=NUMERIC}")
	void updateUserImg(@Param("imgUrl") String imgUrl, @Param("id") long id);
	
	@Update("update  sys_login set user_name=#{userName, jdbcType=VARCHAR},age=#{age, jdbcType=VARCHAR},sex=#{sex, jdbcType=VARCHAR} where  id=#{id, jdbcType=NUMERIC}")
	void updSysLogin(SysLogin sysLogin);
	
	@Delete("delete  from sys_login where  id=#{id, jdbcType=NUMERIC}")
	void deleteSysLogin(@Param("id") long id);
	
	@Delete("delete  from sys_user_role where  id=#{id, jdbcType=NUMERIC}")
	void deleteUserRole(@Param("id") long id);
	
	@Select("select * from sys_login where login_id like '%${loginId}%' order by create_time desc")
	List<SysLogin> querySysLoginByLoginId(@Param("loginId") String loginId);
	
	@Select("select sl.*,sl.login_id user_id from sys_login sl where id=#{id, jdbcType=NUMERIC}")
	SysLogin querySysLoginById(@Param("id") long id);

	@Select("select sl.*,sl.login_id userid from sys_login sl where sl.login_id=#{loginId,jdbcType=VARCHAR}")
	SysLogin querySingleUserByLoginId(@Param("loginId") String loginId);

	@Select("select * from sys_login where login_id like '%${loginId}%' and org_id=#{orgId,jdbcType=NUMERIC} order by create_time desc")
	List<SysLogin> querySingleUserByLoginIdAndOrgId(@Param("loginId") String loginId,@Param("orgId") long orgId);

}
