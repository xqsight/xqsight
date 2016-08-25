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

import com.xqsight.chronic.model.Fat;

/**
 * <p>
 * 脂肪记录表数据库Mapper类
 * </p>
 * <p>
 * 脂肪记录表
 * </p>
 * 
 * @since 2016-05-09 07:50:53
 */
public interface FatMapper {

	@Insert(" INSERT INTO FAT(TEST_TIME,FAT_CONTENT,FAT_INDEX,WATER_CONTENT,BODY_SHAPE,FAT_RATE,EXCEPTION,ACTIVE,CREATE_TIME,CREATE_OPR_ID,REMARK)VALUES(#{testTime,jdbcType=TIMESTAMP},#{fatContent,jdbcType=VARCHAR},#{fatIndex,jdbcType=VARCHAR},#{waterContent,jdbcType=VARCHAR},#{bodyShape,jdbcType=VARCHAR},#{fatRate,jdbcType=VARCHAR},#{exception,jdbcType=VARCHAR},#{active,jdbcType=NUMERIC},#{createTime,jdbcType=TIMESTAMP},#{createOprId,jdbcType=VARCHAR},#{remark,jdbcType=VARCHAR})")
	@Options(useGeneratedKeys = true, keyProperty = "booldId")
	void saveFat(Fat fat);

	@Update(" UPDATE FAT SET TEST_TIME=#{testTime,jdbcType=TIMESTAMP},FAT_CONTENT=#{fatContent,jdbcType=VARCHAR},FAT_INDEX=#{fatIndex,jdbcType=VARCHAR},WATER_CONTENT=#{waterContent,jdbcType=VARCHAR},BODY_SHAPE=#{bodyShape,jdbcType=VARCHAR},FAT_RATE=#{fatRate,jdbcType=VARCHAR},EXCEPTION=#{exception,jdbcType=VARCHAR},ACTIVE=#{active,jdbcType=NUMERIC},REMARK=#{remark,jdbcType=VARCHAR} WHERE BOOLD_ID=#{booldId,jdbcType=NUMERIC}")
	void updateFat(Fat fat);

	@Delete(" DELETE FROM FAT WHERE BOOLD_ID=#{booldId,jdbcType=NUMERIC}")
	void deleteFat(@Param("booldId") Long booldId);

	@Select(" SELECT BOOLD_ID,TEST_TIME,FAT_CONTENT,FAT_INDEX,WATER_CONTENT,BODY_SHAPE,FAT_RATE,EXCEPTION,ACTIVE,CREATE_TIME,CREATE_OPR_ID,UPDATE_TIME,UPD_OPR_ID,REMARK FROM FAT ORDER BY CREATE_TIME DESC")
	List<Fat> queryFat();

	@Select(" SELECT BOOLD_ID,TEST_TIME,FAT_CONTENT,FAT_INDEX,WATER_CONTENT,BODY_SHAPE,FAT_RATE,EXCEPTION,ACTIVE,CREATE_TIME,CREATE_OPR_ID,UPDATE_TIME,UPD_OPR_ID,REMARK FROM FAT WHERE BOOLD_ID=#{booldId,jdbcType=NUMERIC}")
	Fat queryFatById(@Param("booldId") Long booldId);

	@Select(" SELECT BOOLD_ID,TEST_TIME,FAT_CONTENT,FAT_INDEX,WATER_CONTENT,BODY_SHAPE,FAT_RATE,EXCEPTION,ACTIVE,CREATE_TIME,CREATE_OPR_ID,UPDATE_TIME,UPD_OPR_ID,REMARK FROM FAT WHERE CREATE_OPR_ID=#{createOprId,jdbcType=VARCHAR} ORDER BY CREATE_TIME DESC")
	List<Fat> queryFatByUser(@Param("createOprId") String createOprId);
	
	@Select(" SELECT BOOLD_ID,TEST_TIME,FAT_CONTENT,FAT_INDEX,WATER_CONTENT,BODY_SHAPE,FAT_RATE,EXCEPTION,ACTIVE,CREATE_TIME,CREATE_OPR_ID,UPDATE_TIME,UPD_OPR_ID,REMARK FROM FAT "
			+ "WHERE CREATE_OPR_ID=#{createOprId,jdbcType=VARCHAR} AND TEST_TIME >= DATE_ADD(now(), INTERVAL ${stepId,jdbcType=durDay} DAY) ORDER BY CREATE_TIME DESC")
	List<Fat> queryFatByUserAndDurDay(@Param("createOprId") String createOprId, @Param("durDay") int durDay);
}