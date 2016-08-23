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
import org.apache.ibatis.annotations.Update;

import com.xqsight.sys.model.SysDict;
import com.xqsight.sys.model.SysDictDetail;

/**
 * @Description: TODO
 * @author wangganggang
 * @date 2015年12月30日 上午10:48:08
 */
public interface SysDictMapper {

	@Insert("INSERT INTO SYS_DICT (DICT_NAME,DICT_CODE, ACTIVE, CREATE_TIME, CREATE_OPR_ID, REMARK) VALUES("
			+ "#{dictName, jdbcType=VARCHAR},#{dictCode, jdbcType=VARCHAR},#{active, jdbcType=NUMERIC},#{createTime, jdbcType=TIMESTAMP} ,#{createOprId, jdbcType=VARCHAR} , #{remark, jdbcType=VARCHAR})")
	void saveSysDict(SysDict sysDict);

	@Insert("INSERT INTO SYS_DICT_DETAIL (DICT_ID,DICT_VALUE,DICT_DESP,DICT_LANG, ACTIVE,EDITABLE, CREATE_TIME, CREATE_OPR_ID, REMARK) VALUES("
			+ "#{dictId, jdbcType=VARCHAR},#{dictValue, jdbcType=VARCHAR},#{dictDesp, jdbcType=VARCHAR},#{dictLang, jdbcType=VARCHAR},#{active, jdbcType=NUMERIC},#{editable, jdbcType=NUMERIC},#{createTime, jdbcType=TIMESTAMP} ,#{createOprId, jdbcType=VARCHAR}, #{remark, jdbcType=VARCHAR})")
	void saveSysDictDetail(SysDictDetail sysDictDetail);

	@Update("UPDATE  SYS_DICT SET DICT_NAME=#{dictName, jdbcType=VARCHAR},DICT_CODE=#{dictCode, jdbcType=VARCHAR}, update_time= #{updateTime, jdbcType=TIMESTAMP}, upd_opr_id=#{updOprId, jdbcType=VARCHAR}, remark=#{remark, jdbcType=VARCHAR} WHERE  DICT_ID=#{dictId, jdbcType=NUMERIC}")
	void updateSysDict(SysDict sysDict);
	
	@Update("UPDATE  SYS_DICT_DETAIL SET DICT_VALUE=#{dictValue, jdbcType=VARCHAR},DICT_DESP=#{dictDesp, jdbcType=VARCHAR},DICT_LANG=#{dictLang, jdbcType=VARCHAR},ACTIVE=#{active, jdbcType=NUMERIC},EDITABLE=#{editable, jdbcType=NUMERIC}, update_time= #{updateTime, jdbcType=TIMESTAMP}, upd_opr_id=#{updOprId, jdbcType=VARCHAR}, remark=#{remark, jdbcType=VARCHAR} WHERE  DICT_DETAIL_ID=#{dictDetailId, jdbcType=NUMERIC}")
	void updateSysDictDetail(SysDictDetail sysDictDetail);

	@Delete("DELETE FROM SYS_DICT WHERE  DICT_ID=#{dictId, jdbcType=NUMERIC}")
	void deleteSysDict(@Param("dictId") int dictId);
	
	@Delete("DELETE FROM SYS_DICT_DETAIL WHERE  DICT_DETAIL_ID=#{dictDetailId, jdbcType=NUMERIC}")
	void deleteSysDictDetail(@Param("dictDetailId") int dictDetailId);
	
	@Delete("DELETE FROM SYS_DICT_DETAIL WHERE  DICT_ID=#{dictId, jdbcType=NUMERIC}")
	void deleteSysDictDetailByDictId(@Param("dictId") int dictId);

	@Select("SELECT * FROM SYS_DICT WHERE DICT_NAME LIKE #{dictName, jdbcType=VARCHAR} ORDER BY DICT_NAME DESC")
	List<SysDict> querySysDictByDictName(@Param("dictName") String dictName);
	
	@Select("SELECT * FROM SYS_DICT WHERE DICT_Code=#{dictCode, jdbcType=VARCHAR} ORDER BY DICT_NAME DESC")
	List<SysDict> querySysDictByDictCode(@Param("dictCode") String dictCode);

	@Select("SELECT * FROM SYS_DICT WHERE DICT_ID=#{dictId, jdbcType=NUMERIC}")
	SysDict querySysDictByDictId(@Param("dictId") int dictId);

	@Select("SELECT * FROM SYS_DICT_DETAIL WHERE DICT_ID = #{dictId, jdbcType=NUMERIC} ORDER BY CREATE_TIME ASC")
	List<SysDictDetail> querySysDictDetailByDictId(@Param("dictId") int dictId);
	
	@Select("SELECT * FROM SYS_DICT_DETAIL WHERE DICT_DETAIL_ID=#{dictDetailId, jdbcType=NUMERIC}")
	SysDictDetail querySysDictDetailByDictDetailId(@Param("dictDetailId") int dictDetailId);

}
