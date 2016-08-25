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

import com.xqsight.chronic.model.BooldPressure;

/**
 * <p>血压记录表数据库Mapper类</p>
 * <p>血压记录表</p>
 * @since 2016-05-09 07:50:31
 */
public interface BooldPressureMapper {

	@Insert(" INSERT INTO BOOLD_PRESSURE(TEST_TIME,SYSTOLIC_PRESSURE,DISATOLIC_PRESSURE,VENOUS_PRESSURE,EXCEPTION,ACTIVE,CREATE_TIME,CREATE_OPR_ID,REMARK)VALUES(#{testTime,jdbcType=TIMESTAMP},#{systolicPressure,jdbcType=VARCHAR},#{disatolicPressure,jdbcType=VARCHAR},#{venousPressure,jdbcType=VARCHAR},#{exception,jdbcType=VARCHAR},#{active,jdbcType=NUMERIC},#{createTime,jdbcType=TIMESTAMP},#{createOprId,jdbcType=VARCHAR},#{remark,jdbcType=VARCHAR})")
	@Options(useGeneratedKeys = true, keyProperty = "booldId")
	void saveBooldPressure(BooldPressure booldPressure);
	
	@Update(" UPDATE BOOLD_PRESSURE SET TEST_TIME=#{testTime,jdbcType=TIMESTAMP},SYSTOLIC_PRESSURE=#{systolicPressure,jdbcType=VARCHAR},DISATOLIC_PRESSURE=#{disatolicPressure,jdbcType=VARCHAR},VENOUS_PRESSURE=#{venousPressure,jdbcType=VARCHAR},EXCEPTION=#{exception,jdbcType=VARCHAR},ACTIVE=#{active,jdbcType=NUMERIC},UPDATE_TIME=#{updateTime,jdbcType=TIMESTAMP},UPD_OPR_ID=#{updOprId,jdbcType=VARCHAR},REMARK=#{remark,jdbcType=VARCHAR} WHERE BOOLD_ID=#{booldId,jdbcType=NUMERIC}")
	void updateBooldPressure(BooldPressure booldPressure);
	
	@Delete(" DELETE FROM BOOLD_PRESSURE WHERE BOOLD_ID=#{booldId,jdbcType=NUMERIC}")
	void deleteBooldPressure(@Param("booldId") Long booldId);
	
	@Select(" SELECT BOOLD_ID,TEST_TIME,SYSTOLIC_PRESSURE,DISATOLIC_PRESSURE,VENOUS_PRESSURE,EXCEPTION,CREATE_TIME,CREATE_OPR_ID,UPDATE_TIME,UPD_OPR_ID,REMARK FROM BOOLD_PRESSURE ORDER BY CREATE_TIME DESC")
	List<BooldPressure> queryBooldPressure();
	
	@Select(" SELECT BOOLD_ID,TEST_TIME,SYSTOLIC_PRESSURE,DISATOLIC_PRESSURE,VENOUS_PRESSURE,EXCEPTION,CREATE_TIME,CREATE_OPR_ID,UPDATE_TIME,UPD_OPR_ID,REMARK FROM BOOLD_PRESSURE WHERE BOOLD_ID=#{booldId,jdbcType=NUMERIC}")
	BooldPressure queryBooldPressureById(@Param("booldId") Long booldId);

	@Select(" SELECT BOOLD_ID,TEST_TIME,SYSTOLIC_PRESSURE,DISATOLIC_PRESSURE,VENOUS_PRESSURE,EXCEPTION,CREATE_TIME,CREATE_OPR_ID,UPDATE_TIME,UPD_OPR_ID,REMARK FROM BOOLD_PRESSURE WHERE CREATE_OPR_ID=#{createOprId,jdbcType=VARCHAR} ORDER BY CREATE_TIME DESC")
	List<BooldPressure> queryBooldPressureByUser(@Param("createOprId") String createOprId);
	
	@Select(" SELECT TEST_TIME,SYSTOLIC_PRESSURE,DISATOLIC_PRESSURE,VENOUS_PRESSURE,EXCEPTION,CREATE_TIME,CREATE_OPR_ID,UPDATE_TIME,UPD_OPR_ID,REMARK FROM BOOLD_PRESSURE "
			+ "WHERE CREATE_OPR_ID=#{createOprId,jdbcType=VARCHAR} AND TEST_TIME >= DATE_ADD(now(), INTERVAL ${stepId,jdbcType=durDay} DAY) ORDER BY CREATE_TIME DESC")
	List<BooldPressure> queryBooldPressureByUserAndDurDay(@Param("createOprId") String createOprId, @Param("durDay") int durDay);
}