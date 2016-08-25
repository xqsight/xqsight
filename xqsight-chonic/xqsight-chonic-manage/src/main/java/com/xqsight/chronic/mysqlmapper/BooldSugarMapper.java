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

import com.xqsight.chronic.model.BooldSugar;

/**
 * <p>血糖记录表数据库Mapper类</p>
 * <p>血糖记录表</p>
 * @since 2016-05-09 07:50:39
 */
public interface BooldSugarMapper {

	@Insert(" INSERT INTO BOOLD_SUGAR(TEST_TIME,EMPTY,TWO_HOURS,RANDOM,EXCEPTION,ACTIVE,CREATE_TIME,CREATE_OPR_ID,REMARK)VALUES(#{testTime,jdbcType=TIMESTAMP},#{empty,jdbcType=VARCHAR},#{twoHours,jdbcType=VARCHAR},#{random,jdbcType=VARCHAR},#{exception,jdbcType=VARCHAR},#{active,jdbcType=NUMERIC},#{createTime,jdbcType=TIMESTAMP},#{createOprId,jdbcType=VARCHAR},#{remark,jdbcType=VARCHAR})")
	@Options(useGeneratedKeys = true, keyProperty = "booldId")
	void saveBooldSugar(BooldSugar booldSugar);
	
	@Update(" UPDATE BOOLD_SUGAR SET TEST_TIME=#{testTime,jdbcType=TIMESTAMP},EMPTY=#{empty,jdbcType=VARCHAR},TWO_HOURS=#{twoHours,jdbcType=VARCHAR},RANDOM=#{random,jdbcType=VARCHAR},EXCEPTION=#{exception,jdbcType=VARCHAR},UPDATE_TIME=#{updateTime,jdbcType=TIMESTAMP},UPD_OPR_ID=#{updOprId,jdbcType=VARCHAR},REMARK=#{remark,jdbcType=VARCHAR} WHERE BOOLD_ID=#{booldId,jdbcType=NUMERIC}")
	void updateBooldSugar(BooldSugar booldSugar);
	
	@Delete(" DELETE FROM BOOLD_SUGAR WHERE BOOLD_ID=#{booldId,jdbcType=NUMERIC}")
	void deleteBooldSugar(@Param("booldId") Long booldId);
	
	@Select(" SELECT BOOLD_ID,TEST_TIME,EMPTY,TWO_HOURS,RANDOM,EXCEPTION,REMARK FROM BOOLD_SUGAR ORDER BY CREATE_TIME DESC")
	List<BooldSugar> queryBooldSugar();
	
	@Select(" SELECT BOOLD_ID,TEST_TIME,EMPTY,TWO_HOURS,RANDOM,EXCEPTION,ACTIVE,CREATE_TIME,CREATE_OPR_ID,UPDATE_TIME,UPD_OPR_ID,REMARK FROM BOOLD_SUGAR WHERE BOOLD_ID=#{booldId,jdbcType=NUMERIC}")
	BooldSugar queryBooldSugarById(@Param("booldId") Long booldId);
	
	@Select(" SELECT BOOLD_ID,TEST_TIME,EMPTY,TWO_HOURS,RANDOM,EXCEPTION,ACTIVE,CREATE_TIME,CREATE_OPR_ID,UPDATE_TIME,UPD_OPR_ID,REMARK FROM BOOLD_SUGAR WHERE CREATE_OPR_ID=#{createOprId,jdbcType=VARCHAR} ORDER BY CREATE_TIME DESC")
	List<BooldSugar> queryBooldSugarByUser(@Param("createOprId") String createOprId);
	
	@Select(" SELECT BOOLD_ID,TEST_TIME,EMPTY,TWO_HOURS,RANDOM,EXCEPTION,ACTIVE,CREATE_TIME,CREATE_OPR_ID,UPDATE_TIME,UPD_OPR_ID,REMARK FROM BOOLD_SUGAR "
			+ "WHERE CREATE_OPR_ID=#{createOprId,jdbcType=VARCHAR} AND TEST_TIME >= DATE_ADD(now(), INTERVAL ${stepId,jdbcType=durDay} DAY) ORDER BY CREATE_TIME DESC")
	List<BooldSugar> queryBooldSugarByUserAndDurDay(@Param("createOprId") String createOprId, @Param("durDay") int durDay);
	
}