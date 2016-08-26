package com.xqsight.sys.mysqlmapper;

import com.xqsight.sys.model.SysQuickKey;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * @Description: TODO
 * @author wangganggang
 * @date 2015年12月30日 上午10:48:08
 */
public interface SysQuickKeyMapper {

	@Insert("insert into sys_quick_key (id, fun_order, key_icon,key_title,key_value, active, create_time, create_opr_id,  remark) values("
	+ "#{id, jdbcType=VARCHAR},#{funOrder, jdbcType=NUMERIC},#{keyIcon, jdbcType=VARCHAR},#{keyTitle, jdbcType=VARCHAR},#{keyValue, jdbcType=VARCHAR},#{active, jdbcType=NUMERIC},#{createTime, jdbcType=TIMESTAMP} ,#{createOprId, jdbcType=VARCHAR} ,  #{remark, jdbcType=VARCHAR})")
	void saveSysQuickKey(SysQuickKey sysQuickKey);

	@Delete("delete from sys_quick_key where id = #{id, jdbcType=NUMERIC}")
	void deleteSysQuickKey(@Param("id") long id);

	@Select("select * from sys_quick_key where id = #{id, jdbcType=NUMERIC} order by fun_order asc")
	List<SysQuickKey> querySysQuickKeyById(@Param("id") long id);
	
}
