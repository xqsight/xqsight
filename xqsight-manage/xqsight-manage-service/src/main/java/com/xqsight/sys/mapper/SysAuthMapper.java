package com.xqsight.sys.mapper;

import com.xqsight.sys.model.SysLogin;
import com.xqsight.sys.model.SysMenu;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description: TODO
 * @author wangganggang
 * @date 2016年1月11日 上午11:09:49
 */
public interface SysAuthMapper {

	@Select("select sl.* ,case when sur.id is null then -1 else 0 end isselected from sys_login sl left join sys_user_role sur on sl.id = sur.id and sur.role_id=#{roleId, jdbcType=NUMERIC}")
	List<SysLogin> queryAuthUser(long roleId);

	
	@Select("select distinct sm.* ,case when smr.menu_id is null then -1 else 0 end isselected from  sys_menu sm left join sys_menu_role smr on sm.menu_id = smr.menu_id left join sys_user_role sur on sur.role_id = smr.role_id where type=0 and sur.id =#{userId, jdbcType=NUMERIC}  order by sm.sort asc")
	List<SysMenu> queryCurrentUserMenu(long userId);

}
