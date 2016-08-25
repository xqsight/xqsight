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

import com.xqsight.chronic.model.Ecg;

/**
 * <p>心电记录表数据库Mapper类</p>
 * <p>心电记录表</p>
 * @since 2016-05-09 07:50:46
 */
public interface EcgMapper {

	@Insert(" INSERT INTO ECG(TEST_TIME,HEART_RATE,EXCEPTION,ACTIVE,CREATE_TIME,CREATE_OPR_ID,REMARK)VALUES(#{testTime,jdbcType=TIMESTAMP},#{heartRate,jdbcType=VARCHAR},#{exception,jdbcType=VARCHAR},#{active,jdbcType=NUMERIC},#{createTime,jdbcType=TIMESTAMP},#{createOprId,jdbcType=VARCHAR},#{remark,jdbcType=VARCHAR})")
	@Options(useGeneratedKeys = true, keyProperty = "booldId")
	void saveEcg(Ecg ecg);
	
	@Update(" UPDATE ECG SET TEST_TIME=#{testTime,jdbcType=TIMESTAMP},HEART_RATE=#{heartRate,jdbcType=VARCHAR},EXCEPTION=#{exception,jdbcType=VARCHAR},ACTIVE=#{active,jdbcType=NUMERIC},UPDATE_TIME=#{updateTime,jdbcType=TIMESTAMP},UPD_OPR_ID=#{updOprId,jdbcType=VARCHAR},REMARK=#{remark,jdbcType=VARCHAR} WHERE BOOLD_ID=#{booldId,jdbcType=NUMERIC}")
	void updateEcg(Ecg ecg);
	
	@Delete(" DELETE FROM ECG WHERE BOOLD_ID=#{booldId,jdbcType=NUMERIC}")
	void deleteEcg(@Param("booldId") Long booldId);
	
	@Select(" SELECT BOOLD_ID,TEST_TIME,HEART_RATE,EXCEPTION,ACTIVE,CREATE_TIME,CREATE_OPR_ID,UPDATE_TIME,UPD_OPR_ID,REMARK FROM ECG ORDER BY CREATE_TIME DESC")
	List<Ecg> queryEcg();
	
	@Select(" SELECT BOOLD_ID,TEST_TIME,HEART_RATE,EXCEPTION,ACTIVE,CREATE_TIME,CREATE_OPR_ID,UPDATE_TIME,UPD_OPR_ID,REMARK FROM ECG WHERE BOOLD_ID=#{booldId,jdbcType=NUMERIC}")
	Ecg queryEcgById(@Param("booldId") Long booldId);
	 
	@Select(" SELECT BOOLD_ID,TEST_TIME,HEART_RATE,EXCEPTION,ACTIVE,CREATE_TIME,CREATE_OPR_ID,UPDATE_TIME,UPD_OPR_ID,REMARK FROM ECG WHERE CREATE_OPR_ID=#{createOprId,jdbcType=VARCHAR} ORDER BY CREATE_TIME DESC")
	List<Ecg> queryEcgByUser(@Param("createOprId") String createOprId);
	
	@Select(" SELECT BOOLD_ID,TEST_TIME,HEART_RATE,EXCEPTION,ACTIVE,CREATE_TIME,CREATE_OPR_ID,UPDATE_TIME,UPD_OPR_ID,REMARK FROM ECG "
			+ "WHERE CREATE_OPR_ID=#{createOprId,jdbcType=VARCHAR} AND TEST_TIME >= DATE_ADD(now(), INTERVAL ${stepId,jdbcType=durDay} DAY) ORDER BY CREATE_TIME DESC")
	List<Ecg> queryEcgByUserAndDurDay(@Param("createOprId") String createOprId, @Param("durDay") int durDay);
}