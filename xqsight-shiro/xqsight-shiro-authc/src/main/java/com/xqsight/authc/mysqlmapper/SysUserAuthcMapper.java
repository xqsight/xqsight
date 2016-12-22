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
	
	@Select("select sl.* from sys_login sl where sl.login_id=#{loginId,jdbcType=VARCHAR}")
	UserBaseModel queryUserByLoginId(@Param("loginId") String loginId);
	
	@Select("select sr.role_id id, sr.role_name description, sr.role_code role from sys_user_role sur left join sys_role sr on sur.role_id = sr.role_id where sur.id=#{id,jdbcType=NUMERIC}")
	List<Role> queryUserRoleById(@Param("id") long id);
	
	@Select("select sr.role_id id, sr.role_name description, sr.role_code role from sys_role sr ")
	List<Role> queryAllRole();
	
	@Select(" select sm.menu_id id,sm.menu_name name ,sm.url,sm.permission,sm.parent_id,sm.type from sys_menu sm where exists( select role_id from sys_menu_role smr where smr.menu_id = sm.menu_id and smr.role_id = #{roleId,jdbcType=NUMERIC})")
	List<Resource> queryResourceByRoleId(@Param("roleId") long roleId);
	
	@Insert("insert into sys_login(login_id, user_name, password,salt, locked, login_type) values("
			+ "#{loginId,jdbcType=VARCHAR},#{userName,jdbcType=VARCHAR},#{password,jdbcType=VARCHAR},#{salt,jdbcType=VARCHAR},#{locked,jdbcType=NUMERIC},#{loginType, jdbcType=NUMERIC})")
	void saveUserLogin(UserBaseModel userBaseModel);
}
