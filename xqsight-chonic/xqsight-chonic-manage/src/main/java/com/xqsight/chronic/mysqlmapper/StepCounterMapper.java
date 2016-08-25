/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.chronic.mysqlmapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xqsight.chronic.model.StepCounter;

/**
 * <p>计步器表数据库Mapper类</p>
 * <p>计步器表</p>
 * @since 2016-05-09 07:51:57
 */
public interface StepCounterMapper {

	@Insert(" INSERT INTO STEP_COUNTER(STEP_COUNT,KILOMERTRE_COUNT,ENERGY_COUNT,ACTIVE,CREATE_TIME,CREATE_OPR_ID,REMARK)VALUES(#{stepCount,jdbcType=NUMERIC},#{kilomertreCount,jdbcType=NUMERIC},#{energyCount,jdbcType=NUMERIC},#{active,jdbcType=NUMERIC},#{createTime,jdbcType=TIMESTAMP},#{createOprId,jdbcType=VARCHAR},#{remark,jdbcType=VARCHAR})")
	@Options(useGeneratedKeys = true, keyProperty = "stepId")
	void saveStepCounter(StepCounter stepCounter);
	
	@Update(" UPDATE STEP_COUNTER SET STEP_COUNT=#{stepCount,jdbcType=NUMERIC},KILOMERTRE_COUNT=#{kilomertreCount,jdbcType=NUMERIC},ENERGY_COUNT=#{energyCount,jdbcType=NUMERIC},ACTIVE=#{active,jdbcType=NUMERIC},UPDATE_TIME=#{updateTime,jdbcType=TIMESTAMP},UPD_OPR_ID=#{updOprId,jdbcType=VARCHAR},REMARK=#{remark,jdbcType=VARCHAR} WHERE STEP_ID=#{stepId,jdbcType=NUMERIC}")
	void updateStepCounter(StepCounter stepCounter);
	
	@Delete(" DELETE FROM STEP_COUNTER WHERE STEP_ID=#{stepId,jdbcType=NUMERIC}")
	void deleteStepCounter(@Param("stepId") Long stepId);

	@Delete(" DELETE FROM STEP_COUNTER WHERE DATE_FORMAT(CREATE_TIME,'%Y-%m-%d')=DATE_FORMAT(#{createTime,jdbcType=TIMESTAMP},'%Y-%m-%d')")
	void deleteStepCounterByCreateTime(@Param("createTime") Date createTime);
	
	@Select(" SELECT STEP_ID,STEP_COUNT,KILOMERTRE_COUNT,ENERGY_COUNT,ACTIVE,CREATE_TIME,CREATE_OPR_ID,UPDATE_TIME,UPD_OPR_ID,REMARK FROM STEP_COUNTER ORDER BY CREATE_TIME DESC")
	List<StepCounter> queryStepCounter();
	
	@Select(" SELECT STEP_ID,STEP_COUNT,KILOMERTRE_COUNT,ENERGY_COUNT,ACTIVE,CREATE_TIME,CREATE_OPR_ID,UPDATE_TIME,UPD_OPR_ID,REMARK FROM STEP_COUNTER WHERE STEP_ID=#{stepId,jdbcType=NUMERIC}")
	StepCounter queryStepCounterById(@Param("stepId") Long stepId);
	
	@Select(" SELECT STEP_ID,STEP_COUNT,KILOMERTRE_COUNT,ENERGY_COUNT,ACTIVE,CREATE_TIME,CREATE_OPR_ID,UPDATE_TIME,UPD_OPR_ID,REMARK FROM STEP_COUNTER WHERE CREATE_OPR_ID=#{createOprId,jdbcType=VARCHAR} ORDER BY CREATE_TIME DESC")
	List<StepCounter> queryStepCounterByUser(@Param("createOprId") String createOprId);
	
	@Select(" SELECT STEP_COUNT,KILOMERTRE_COUNT,ENERGY_COUNT,ACTIVE,CREATE_TIME,CREATE_OPR_ID,UPDATE_TIME,UPD_OPR_ID,REMARK FROM STEP_COUNTER "
			+ "WHERE CREATE_OPR_ID=#{createOprId,jdbcType=VARCHAR} AND CREATE_TIME >= DATE_ADD(now(), INTERVAL ${stepId,jdbcType=durDay} DAY) ORDER BY CREATE_TIME DESC")
	List<StepCounter> queryStepCounterByUserAndDurDay(@Param("createOprId") String createOprId, @Param("durDay") int durDay);
}