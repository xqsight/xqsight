/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.authc.mysqlmapper;

import com.xqsight.sso.model.UserBaseModel;
import com.xqsight.sso.shiro.model.Resource;
import com.xqsight.sso.shiro.model.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description: TODO
 * @author wangganggang
 * @date 2015年12月30日 下午4:33:59
 */
public interface SysUserAuthcMapper {
	
	@Select("SELECT SL.* FROM SYS_LOGIN SL WHERE SL.LOGIN_ID=#{loginId,jdbcType=VARCHAR}")
	UserBaseModel queryUserByLoginId(@Param("loginId") String loginId);
	
	@Select("SELECT SR.ROLE_ID ID, SR.ROLE_NAME DESCRIPTION, SR.ROLE_CODE ROLE FROM SYS_USER_ROLE SUR LEFT JOIN SYS_ROLE SR ON SUR.ROLE_ID = SR.ROLE_ID WHERE SUR.ID=#{id,jdbcType=NUMERIC}")
	List<Role> queryUserRoleById(@Param("id") long id);
	
	@Select("SELECT SR.ROLE_ID ID, SR.ROLE_NAME description, SR.ROLE_CODE ROLE FROM SYS_ROLE SR ")
	List<Role> queryAllRole();
	
	@Select(" SELECT SM.MENU_ID ID,SM.MENU_NAME NAME ,SM.URL,SM.PERMISSION,SM.PARENT_ID,SM.TYPE FROM SYS_MENU SM WHERE EXISTS( SELECT ROLE_ID FROM SYS_MENU_ROLE SMR WHERE SMR.MENU_ID = SM.MENU_ID AND SMR.ROLE_ID = #{roleId,jdbcType=NUMERIC})")
	List<Resource> queryResourceByRoleId(@Param("roleId") long roleId);
	
	@Insert("INSERT INTO SYS_LOGIN(LOGIN_ID, USER_NAME, PASSWORD,SALT, LOCKED, LOGIN_TYPE) VALUES("
			+ "#{loginId,jdbcType=VARCHAR},#{userName,jdbcType=VARCHAR},#{password,jdbcType=VARCHAR},#{salt,jdbcType=VARCHAR},#{locked,jdbcType=NUMERIC},#{loginType, jdbcType=NUMERIC})")
	void saveUserLogin(UserBaseModel userBaseModel);
}
