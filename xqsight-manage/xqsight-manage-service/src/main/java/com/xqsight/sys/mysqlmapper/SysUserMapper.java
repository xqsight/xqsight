/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.sys.mysqlmapper;

import java.util.List;

import com.xqsight.sso.model.UserBaseModel;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xqsight.sys.model.SysLogin;

/**
 * @Description: TODO
 * @author wangganggang
 * @date 2015年12月30日 上午10:48:08
 */
public interface SysUserMapper {

	@Insert("INSERT INTO SYS_LOGIN (LOGIN_ID,USER_NAME,ORG_ID, PASSWORD, LOGIN_TYPE, FROM_SOURCE, IMG_URL, SALT, LOCKED,AGE,SEX, ACTIVE, CREATE_TIME, CREATE_OPR_ID, REMARK) VALUES("
	+ "#{loginId, jdbcType=VARCHAR},#{userName, jdbcType=VARCHAR},#{orgId, jdbcType=VARCHAR},#{password, jdbcType=VARCHAR},#{loginType, jdbcType=NUMERIC},#{fromSource, jdbcType=VARCHAR},#{imgUrl, jdbcType=VARCHAR},#{salt, jdbcType=VARCHAR},#{locked, jdbcType=NUMERIC},#{age, jdbcType=NUMERIC},#{sex, jdbcType=NUMERIC},#{active, jdbcType=NUMERIC},#{createTime, jdbcType=TIMESTAMP} ,#{createOprId, jdbcType=VARCHAR} , #{remark, jdbcType=VARCHAR})")
	void saveSysLogin(SysLogin sysLogin);
	
	@Update("UPDATE  SYS_LOGIN SET PASSWORD = #{password, jdbcType=VARCHAR}  WHERE  ID=#{id, jdbcType=NUMERIC}")
	void updateSysLoginPwd(@Param("password") String password, @Param("id") Long id);

	@Update("UPDATE  SYS_LOGIN SET IMG_URL = #{imgUrl, jdbcType=VARCHAR} WHERE ID=#{id, jdbcType=NUMERIC}")
	void updateUserImg(@Param("imgUrl") String imgUrl, @Param("id") Long id);
	
	@Update("UPDATE  SYS_LOGIN SET USER_NAME = #{userName, jdbcType=VARCHAR},AGE=#{age, jdbcType=VARCHAR},SEX=#{sex, jdbcType=VARCHAR} WHERE  ID=#{id, jdbcType=NUMERIC}")
	void updSysLogin(SysLogin sysLogin);
	
	@Delete("DELETE  FROM SYS_LOGIN WHERE ID = #{id, jdbcType=NUMERIC}")
	void deleteSysLogin(@Param("id") Long id);
	
	@Delete("DELETE  FROM SYS_USER_ROLE WHERE ID = #{id, jdbcType=NUMERIC}")
	void deleteUserRole(@Param("id") Long id);
	
	@Select("SELECT * FROM SYS_LOGIN WHERE LOGIN_ID LIKE '%${loginId}%' AND USER_NAME LIKE '%${userName}%' AND ORG_ID = #{orgId, jdbcType=NUMERIC} ORDER BY CREATE_TIME DESC")
	List<SysLogin> querySysLoginByUserNameAndLoginIdAndOrgId(@Param("userName") String userName, @Param("loginId") String loginId, @Param("orgId") Long orgId);
	
	@Select("SELECT * FROM SYS_LOGIN WHERE ID = #{id, jdbcType=NUMERIC}")
	SysLogin querySysLoginById(@Param("id") Long id);

	@Select("SELECT * FROM SYS_LOGIN WHERE LOGIN_ID = #{loginId,jdbcType=VARCHAR}")
	SysLogin querySingleUserByLoginId(@Param("loginId") String loginId);

}
