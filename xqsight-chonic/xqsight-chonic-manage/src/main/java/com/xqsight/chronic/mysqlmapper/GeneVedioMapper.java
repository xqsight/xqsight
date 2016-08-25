/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.chronic.mysqlmapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xqsight.chronic.model.GeneVedio;

/**
 * <p>基因大讲堂表数据库Mapper类</p>
 * <p>基因大讲堂表</p>
 * @since 2016-05-13 03:29:55
 */
public interface GeneVedioMapper {

	@Insert(" INSERT INTO GENE_VEDIO(VEDIO_NAME,VEDIO_DESCRIPTION,VEDIO_TYPE,FILE_ID,ACTIVE,CREATE_TIME,CREATE_OPR_ID,REMARK)VALUES(#{vedioName,jdbcType=VARCHAR},#{vedioDescription,jdbcType=VARCHAR},#{vedioType,jdbcType=VARCHAR},#{fileId,jdbcType=VARCHAR},#{active,jdbcType=NUMERIC},#{createTime,jdbcType=TIMESTAMP},#{createOprId,jdbcType=VARCHAR},#{remark,jdbcType=VARCHAR})")
	void saveGeneVedio(GeneVedio geneVedio);
	
	@Update(" UPDATE GENE_VEDIO SET VEDIO_NAME=#{vedioName,jdbcType=VARCHAR},VEDIO_DESCRIPTION=#{vedioDescription,jdbcType=VARCHAR},VEDIO_TYPE=#{vedioType,jdbcType=VARCHAR},FILE_ID=#{fileId,jdbcType=VARCHAR},ACTIVE=#{active,jdbcType=NUMERIC},UPDATE_TIME=#{updateTime,jdbcType=TIMESTAMP},UPD_OPR_ID=#{updOprId,jdbcType=VARCHAR},REMARK=#{remark,jdbcType=VARCHAR} WHERE VEDIO_ID=#{vedioId,jdbcType=NUMERIC}")
	void updateGeneVedio(GeneVedio geneVedio);
	
	@Delete(" DELETE FROM GENE_VEDIO WHERE VEDIO_ID=#{vedioId,jdbcType=NUMERIC}")
	void deleteGeneVedio(@Param("vedioId") Long vedioId);
	
	@Select(" SELECT VEDIO_ID,VEDIO_NAME,VEDIO_DESCRIPTION,VEDIO_TYPE,FILE_ID,ACTIVE,CREATE_TIME,CREATE_OPR_ID,UPDATE_TIME,UPD_OPR_ID,REMARK FROM GENE_VEDIO WHERE VEDIO_NAME LIKE '%${vedioName}%' ORDER BY CREATE_TIME DESC")
	List<GeneVedio> queryGeneVedioByLikeName(@Param("vedioName") String vedioName);

	@Select("SELECT GV.VEDIO_ID,GV.VEDIO_NAME,GV.VEDIO_DESCRIPTION,ifnull(SF.FILE_URL,' ') IMG_PATH FROM GENE_VEDIO GV LEFT JOIN SYS_FILE SF ON SF.FILE_ID = SUBSTRING_INDEX(GV.FILE_ID,',',1) ORDER BY GV.CREATE_TIME DESC")
	List<Map<String,Object>> queryGeneVedioWithFirstVedio();
	
	@Select(" SELECT VEDIO_ID,VEDIO_NAME,VEDIO_DESCRIPTION,VEDIO_TYPE,FILE_ID,ACTIVE,CREATE_TIME,CREATE_OPR_ID,UPDATE_TIME,UPD_OPR_ID,REMARK FROM GENE_VEDIO WHERE VEDIO_ID=#{vedioId,jdbcType=NUMERIC}")
	GeneVedio queryGeneVedioById(@Param("vedioId") Long vedioId);

}