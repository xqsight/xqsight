/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.chronic.mysqlmapper;

import com.xqsight.chronic.model.ReportRecord;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;


/**
 * <p>报告单数据库Mapper类</p>
 * <p>报告单表</p>
 * @since 2016-05-13 03:29:34
 */
public interface ReportRecordMapper {

	@Insert(" INSERT INTO REPORT_RECORD(REPORT_NAME,REPORT_DESCRIPTION,REPORT_URL,TEST_TIME,ACTIVE,CREATE_TIME,CREATE_OPR_ID,REMARK)VALUES(#{reportName,jdbcType=VARCHAR},#{reportDescription,jdbcType=VARCHAR},#{reportURL,jdbcType=VARCHAR},#{testTime,jdbcType=TIMESTAMP},#{active,jdbcType=NUMERIC},#{createTime,jdbcType=TIMESTAMP},#{createOprId,jdbcType=VARCHAR},#{remark,jdbcType=VARCHAR})")
	@Options(useGeneratedKeys = true, keyProperty = "reportId")
	void saveReportRecord(ReportRecord reportRecord);
	
	@Delete(" DELETE FROM REPORT_RECORD WHERE REPORT_ID=#{reportId,jdbcType=NUMERIC}")
	void deleteReportRecord(@Param("reportId") Long reportId);

	@Select(" SELECT REPORT_ID,TEST_TIME,REPORT_NAME,REPORT_DESCRIPTION,REPORT_URL,ACTIVE,CREATE_TIME,CREATE_OPR_ID,REMARK FROM REPORT_RECORD  ORDER BY CREATE_TIME DESC")
	List<ReportRecord> queryReportRecord();

	@Select(" SELECT REPORT_ID,TEST_TIME,REPORT_NAME,REPORT_DESCRIPTION,REPORT_URL,ACTIVE,CREATE_TIME,CREATE_OPR_ID,REMARK FROM REPORT_RECORD WHERE CREATE_OPR_ID=#{createOprId,jdbcType=VARCHAR} ORDER BY CREATE_TIME DESC")
	List<ReportRecord> queryReportRecordByUser(@Param("createOprId") String createOprId);
	
	@Select(" SELECT REPORT_ID,TEST_TIME,REPORT_NAME,REPORT_DESCRIPTION,REPORT_URL,ACTIVE,CREATE_TIME,CREATE_OPR_ID,REMARK FROM REPORT_RECORD WHERE REPORT_ID=#{reportId,jdbcType=NUMERIC}")
	ReportRecord queryReportRecordById(@Param("reportId") Long reportId);
}