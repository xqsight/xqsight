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

import com.xqsight.sys.model.SysQuickKey;

/**
 * @Description: TODO
 * @author wangganggang
 * @date 2015年12月30日 上午10:48:08
 */
public interface SysQuickKeyMapper {

	@Insert("INSERT INTO SYS_QUICK_KEY (ID, FUN_ORDER, KEY_ICON,KEY_TITLE,KEY_VALUE, ACTIVE, CREATE_TIME, CREATE_OPR_ID,  REMARK) VALUES("
	+ "#{id, jdbcType=VARCHAR},#{funOrder, jdbcType=NUMERIC},#{keyIcon, jdbcType=VARCHAR},#{keyTitle, jdbcType=VARCHAR},#{keyValue, jdbcType=VARCHAR},#{active, jdbcType=NUMERIC},#{createTime, jdbcType=TIMESTAMP} ,#{createOprId, jdbcType=VARCHAR} ,  #{remark, jdbcType=VARCHAR})")
	void saveSysQuickKey(SysQuickKey sysQuickKey);

	@Delete("DELETE FROM SYS_QUICK_KEY WHERE  id = #{id, jdbcType=NUMERIC}")
	void deleteSysQuickKey(@Param("id") long id);

	@Select("SELECT * FROM SYS_QUICK_KEY WHERE id = #{id, jdbcType=NUMERIC} ORDER BY FUN_ORDER ASC")
	List<SysQuickKey> querySysQuickKeyById(@Param("id") long id);
	
}
