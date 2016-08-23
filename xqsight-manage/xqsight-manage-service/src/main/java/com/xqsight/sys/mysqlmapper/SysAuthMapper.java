/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.sys.mysqlmapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.xqsight.sys.model.SysLogin;
import com.xqsight.sys.model.SysMenu;

/**
 * @Description: TODO
 * @author wangganggang
 * @date 2016年1月11日 上午11:09:49
 */
public interface SysAuthMapper {

	@Select("SELECT SL.* ,CASE WHEN SUR.ID IS NULL THEN -1 ELSE 0 END ISSELECTED FROM SYS_LOGIN SL LEFT JOIN SYS_USER_ROLE SUR ON SL.ID = SUR.ID and SUR.ROLE_ID=#{roleId, jdbcType=NUMERIC}")
	List<SysLogin> queryAuthUser(int roleId);

	
	@Select("SELECT DISTINCT SM.* ,CASE WHEN SMR.MENU_ID IS NULL THEN -1 ELSE 0 END ISSELECTED FROM  SYS_MENU SM LEFT JOIN SYS_MENU_ROLE SMR ON SM.MENU_ID = SMR.MENU_ID LEFT JOIN SYS_USER_ROLE SUR ON SUR.ROLE_ID = SMR.ROLE_ID WHERE TYPE=0 AND SUR.ID =#{userId, jdbcType=NUMERIC}  ORDER BY SM.SORT ASC")
	List<SysMenu> queryCurrentUserMenu(long userId);

}
