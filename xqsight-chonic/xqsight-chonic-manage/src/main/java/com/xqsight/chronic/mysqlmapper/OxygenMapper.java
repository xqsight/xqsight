/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.chronic.mysqlmapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xqsight.chronic.model.Oxygen;

/**
 * <p>血氧记录表数据库Mapper类</p>
 * <p>血氧记录表</p>
 * @since 2016-05-09 07:51:50
 */
public interface OxygenMapper {

	@Insert(" INSERT INTO OXYGEN(TEST_TIME,OXYGEN,PULSE_RATE,EXCEPTION,ACTIVE,CREATE_TIME,CREATE_OPR_ID,REMARK)VALUES(#{testTime,jdbcType=TIMESTAMP},#{oxygen,jdbcType=VARCHAR},#{pulseRate,jdbcType=VARCHAR},#{exception,jdbcType=VARCHAR},#{active,jdbcType=NUMERIC},#{createTime,jdbcType=TIMESTAMP},#{createOprId,jdbcType=VARCHAR},#{remark,jdbcType=VARCHAR})")
	@Options(useGeneratedKeys = true, keyProperty = "booldId")
	void saveOxygen(Oxygen oxygen);
	
	@Update(" UPDATE OXYGEN SET TEST_TIME=#{testTime,jdbcType=TIMESTAMP},OXYGEN=#{oxygen,jdbcType=VARCHAR},PULSE_RATE=#{pulseRate,jdbcType=VARCHAR},EXCEPTION=#{exception,jdbcType=VARCHAR},ACTIVE=#{active,jdbcType=NUMERIC},UPDATE_TIME=#{updateTime,jdbcType=TIMESTAMP},UPD_OPR_ID=#{updOprId,jdbcType=VARCHAR},REMARK=#{remark,jdbcType=VARCHAR} WHERE BOOLD_ID=#{booldId,jdbcType=NUMERIC}")
	void updateOxygen(Oxygen oxygen);
	
	@Delete(" DELETE FROM OXYGEN WHERE BOOLD_ID=#{booldId,jdbcType=NUMERIC}")
	void deleteOxygen(@Param("booldId") Long booldId);
	
	@Select(" SELECT TEST_TIME,OXYGEN,PULSE_RATE,EXCEPTION,ACTIVE,CREATE_TIME,CREATE_OPR_ID,UPDATE_TIME,UPD_OPR_ID,REMARK FROM OXYGEN ORDER BY CREATE_TIME DESC")
	List<Oxygen> queryOxygen();
	
	@Select(" SELECT BOOLD_ID,TEST_TIME,OXYGEN,PULSE_RATE,EXCEPTION,ACTIVE,CREATE_TIME,CREATE_OPR_ID,UPDATE_TIME,UPD_OPR_ID,REMARK FROM OXYGEN WHERE BOOLD_ID=#{booldId,jdbcType=NUMERIC}")
	Oxygen queryOxygenById(@Param("booldId") Long booldId);

	@Select(" SELECT BOOLD_ID,TEST_TIME,OXYGEN,PULSE_RATE,EXCEPTION,ACTIVE,CREATE_TIME,CREATE_OPR_ID,UPDATE_TIME,UPD_OPR_ID,REMARK FROM OXYGEN WHERE CREATE_OPR_ID=#{createOprId,jdbcType=VARCHAR} ORDER BY CREATE_TIME DESC")
	List<Oxygen> queryOxygenByUser(@Param("createOprId") String createOprId);
	
	@Select(" SELECT BOOLD_ID,TEST_TIME,OXYGEN,PULSE_RATE,EXCEPTION,ACTIVE,CREATE_TIME,CREATE_OPR_ID,UPDATE_TIME,UPD_OPR_ID,REMARK FROM OXYGEN "
			+ "WHERE CREATE_OPR_ID=#{createOprId,jdbcType=VARCHAR}AND TEST_TIME >= DATE_ADD(now(), INTERVAL ${stepId,jdbcType=durDay} DAY) ORDER BY CREATE_TIME DESC")
	List<Oxygen> queryOxygenByUserAndDurDay(@Param("createOprId") String createOprId, @Param("durDay") int durDay);
}