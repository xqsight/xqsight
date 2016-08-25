/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.chronic.mysqlmapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xqsight.chronic.model.BeautyParlor;

/**
 * <p>美容院表数据库Mapper类</p>
 * <p>美容院表</p>
 * @since 2016-05-09 07:48:43
 */
public interface BeautyParlorMapper {

	@Insert(" INSERT INTO BEAUTY_PARLOR(BEAUTY_NAME,FILE_ID,BEAUTY_ADDRESS,BEAUTY_PHONE,BEAUTY_QQ,BEAUTY_DESCRIPT,BEAUTY_LNG,BEAUTY_LAT,COMMENT_HAS_PIC,ACTIVE,CREATE_TIME,CREATE_OPR_ID,REMARK)VALUES(#{beautyName,jdbcType=VARCHAR},#{fileId,jdbcType=VARCHAR},#{beautyAddress,jdbcType=VARCHAR},#{beautyPhone,jdbcType=VARCHAR},#{beautyQq,jdbcType=VARCHAR},#{beautyDescript,jdbcType=VARCHAR},#{beautyLng,jdbcType=VARCHAR},#{beautyLat,jdbcType=VARCHAR},#{commentHasPic,jdbcType=NUMERIC},#{active,jdbcType=NUMERIC},#{createTime,jdbcType=TIMESTAMP},#{createOprId,jdbcType=VARCHAR},#{remark,jdbcType=VARCHAR})")
	@Options(useGeneratedKeys = true, keyProperty = "beautyId")
	void saveBeautyParlor(BeautyParlor beautyParlor);
	
	@Update(" UPDATE BEAUTY_PARLOR SET FILE_ID=#{fileId,jdbcType=VARCHAR},BEAUTY_NAME=#{beautyName,jdbcType=VARCHAR},BEAUTY_ADDRESS=#{beautyAddress,jdbcType=VARCHAR},BEAUTY_PHONE=#{beautyPhone,jdbcType=VARCHAR},BEAUTY_QQ=#{beautyQq,jdbcType=VARCHAR},BEAUTY_DESCRIPT=#{beautyDescript,jdbcType=VARCHAR},BEAUTY_LNG=#{beautyLng,jdbcType=VARCHAR},BEAUTY_LAT=#{beautyLat,jdbcType=VARCHAR},COMMENT_HAS_PIC=#{commentHasPic,jdbcType=NUMERIC},ACTIVE=#{active,jdbcType=NUMERIC},UPDATE_TIME=#{updateTime,jdbcType=TIMESTAMP},UPD_OPR_ID=#{updOprId,jdbcType=VARCHAR},REMARK=#{remark,jdbcType=VARCHAR} WHERE BEAUTY_ID=#{beautyId,jdbcType=NUMERIC}")
	void updateBeautyParlor(BeautyParlor beautyParlor);
	
	@Delete(" DELETE FROM BEAUTY_PARLOR WHERE BEAUTY_ID=#{beautyId,jdbcType=NUMERIC}")
	void deleteBeautyParlor(@Param("beautyId") Long beautyId);
	
	@Select(" SELECT BEAUTY_ID,FILE_ID,BEAUTY_NAME,BEAUTY_ADDRESS,BEAUTY_PHONE,BEAUTY_QQ,BEAUTY_DESCRIPT,BEAUTY_LNG,BEAUTY_LAT,COMMENT_HAS_PIC,ACTIVE,CREATE_TIME,CREATE_OPR_ID,UPDATE_TIME,UPD_OPR_ID,REMARK FROM BEAUTY_PARLOR ORDER BY CREATE_TIME DESC")
	List<BeautyParlor> queryBeautyParlor();
	
	@Select(" SELECT BEAUTY_ID,FILE_ID,BEAUTY_NAME,BEAUTY_ADDRESS,BEAUTY_PHONE,BEAUTY_QQ,BEAUTY_DESCRIPT,BEAUTY_LNG,BEAUTY_LAT,COMMENT_HAS_PIC,ACTIVE,CREATE_TIME,CREATE_OPR_ID,UPDATE_TIME,UPD_OPR_ID,REMARK FROM BEAUTY_PARLOR WHERE BEAUTY_ID=#{beautyId,jdbcType=NUMERIC} ORDER BY CREATE_TIME DESC")
	BeautyParlor queryBeautyParlorById(@Param("beautyId") Long beautyId);

	@Select("SELECT BP.BEAUTY_ID,BP.FILE_ID,BP.BEAUTY_NAME,BP.BEAUTY_ADDRESS,BP.BEAUTY_PHONE,BP.BEAUTY_DESCRIPT,ifnull(SF.FILE_URL,' ') IMG_PATH FROM BEAUTY_PARLOR BP LEFT JOIN SYS_FILE SF ON SF.FILE_ID = SUBSTRING_INDEX(BP.FILE_ID,',',1) WHERE BP.BEAUTY_ADDRESS LIKE '%${beautyAddress}%' ORDER BY BP.CREATE_TIME DESC")
	List<Map<String,Object>> queryBeautyParlorWithFirstPic(@Param("beautyAddress") String beautyAddress);
}