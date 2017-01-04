package com.xqsight.sys.mapper;

import com.xqsight.sys.model.SysDict;
import com.xqsight.sys.model.SysDictDetail;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Description: TODO
 * @author wangganggang
 * @date 2015年12月30日 上午10:48:08
 */
public interface SysDictMapper {

	@Insert("insert into sys_dict (dict_name,dict_code, active, create_time, create_opr_id, remark) values("
			+ "#{dictName, jdbcType=VARCHAR},#{dictCode, jdbcType=VARCHAR},#{active, jdbcType=NUMERIC},#{createTime, jdbcType=TIMESTAMP} ,#{createOprId, jdbcType=VARCHAR} , #{remark, jdbcType=VARCHAR})")
	void saveSysDict(SysDict sysDict);

	@Insert("insert into sys_dict_detail (dict_id,dict_value,dict_desp,dict_lang, active,editable, create_time, create_opr_id, remark) values("
			+ "#{dictId, jdbcType=VARCHAR},#{dictValue, jdbcType=VARCHAR},#{dictDesp, jdbcType=VARCHAR},#{dictLang, jdbcType=VARCHAR},#{active, jdbcType=NUMERIC},#{editable, jdbcType=NUMERIC},#{createTime, jdbcType=TIMESTAMP} ,#{createOprId, jdbcType=VARCHAR}, #{remark, jdbcType=VARCHAR})")
	void saveSysDictDetail(SysDictDetail sysDictDetail);

	@Update("update  sys_dict set dict_name=#{dictName, jdbcType=VARCHAR},dict_code=#{dictCode, jdbcType=VARCHAR}, update_time= #{updateTime, jdbcType=TIMESTAMP}, upd_opr_id=#{updOprId, jdbcType=VARCHAR}, remark=#{remark, jdbcType=VARCHAR} WHERE  dict_id=#{dictId, jdbcType=NUMERIC}")
	void updateSysDict(SysDict sysDict);
	
	@Update("update  sys_dict_detail SET dict_value=#{dictValue, jdbcType=VARCHAR},dict_desp=#{dictDesp, jdbcType=VARCHAR},dict_lang=#{dictLang, jdbcType=VARCHAR},ACTIVE=#{active, jdbcType=NUMERIC},editable=#{editable, jdbcType=NUMERIC}, update_time= #{updateTime, jdbcType=TIMESTAMP}, upd_opr_id=#{updOprId, jdbcType=VARCHAR}, remark=#{remark, jdbcType=VARCHAR} WHERE  dict_detail_id=#{dictDetailId, jdbcType=NUMERIC}")
	void updateSysDictDetail(SysDictDetail sysDictDetail);

	@Delete("delete from sys_dict where  dict_id=#{dictId, jdbcType=NUMERIC}")
	void deleteSysDict(@Param("dictId") long dictId);
	
	@Delete("delete from sys_dict_detail where  dict_detail_id=#{dictDetailId, jdbcType=NUMERIC}")
	void deleteSysDictDetail(@Param("dictDetailId") long dictDetailId);
	
	@Delete("delete from sys_dict_detail where  dict_id=#{dictId, jdbcType=NUMERIC}")
	void deleteSysDictDetailByDictId(@Param("dictId") long dictId);

	@Select("select * from sys_dict where dict_name like #{dictName, jdbcType=VARCHAR} order by dict_name desc")
	List<SysDict> querySysDictByDictName(@Param("dictName") String dictName);
	
	@Select("select * from sys_dict where dict_code=#{dictCode, jdbcType=VARCHAR} order by dict_name desc")
	List<SysDict> querySysDictByDictCode(@Param("dictCode") String dictCode);

	@Select("select * from sys_dict where dict_id=#{dictId, jdbcType=NUMERIC}")
	SysDict querySysDictByDictId(@Param("dictId") long dictId);

	@Select("select * from sys_dict_detail where dict_id = #{dictId, jdbcType=NUMERIC} order by create_time asc")
	List<SysDictDetail> querySysDictDetailByDictId(@Param("dictId") long dictId);
	
	@Select("select * from sys_dict_detail where dict_detail_id=#{dictDetailId, jdbcType=NUMERIC}")
	SysDictDetail querySysDictDetailByDictDetailId(@Param("dictDetailId") long dictDetailId);

}
