/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.sys.mysqlmapper;

import java.util.List;

import org.apache.ibatis.annotations.*;

import com.xqsight.sys.model.SysOrg;

/**
 * <p>组织机构表数据库Mapper类</p>
 * <p>组织机构表</p>
 * @since 2016-06-20 07:55:50
 */
public interface SysOrgMapper {

	@Insert(" INSERT INTO SYS_ORG(PARENT_ID,ORG_NAME,ORG_TYPE,ORG_CODE,CUSTOM_CODE,SORT,ICON ,ACTIVE,CREATE_OPR_ID,CREATE_TIME,REMARK)VALUES(#{parentId,jdbcType=NUMERIC},#{orgName,jdbcType=VARCHAR},#{orgType,jdbcType=VARCHAR},#{orgCode,jdbcType=VARCHAR},#{customCode,jdbcType=VARCHAR},#{sort,jdbcType=NUMERIC},#{icon,jdbcType=VARCHAR},#{active,jdbcType=NUMERIC},#{createOprId,jdbcType=VARCHAR},#{createTime,jdbcType=TIMESTAMP},#{remark,jdbcType=VARCHAR})")
	@Options(useGeneratedKeys = true, keyProperty = "orgId")
	void saveSysOrg(SysOrg sysOrg);
	
	@Update(" UPDATE SYS_ORG SET PARENT_ID=#{parentId,jdbcType=NUMERIC},ORG_NAME=#{orgName,jdbcType=VARCHAR},ORG_TYPE=#{orgType,jdbcType=VARCHAR},ORG_CODE=#{orgCode,jdbcType=VARCHAR},CUSTOM_CODE=#{customCode,jdbcType=VARCHAR},SORT=#{sort,jdbcType=NUMERIC},ICON=#{icon,jdbcType=VARCHAR},ACTIVE=#{active,jdbcType=NUMERIC},UPD_OPR_ID=#{updOprId,jdbcType=VARCHAR},UPDATE_TIME=#{updateTime,jdbcType=TIMESTAMP},REMARK=#{remark,jdbcType=VARCHAR} WHERE ORG_ID=#{orgId,jdbcType=NUMERIC}")
	void updateSysOrg(SysOrg sysOrg);
	
	@Delete(" DELETE FROM sys_org WHERE ORG_ID=#{orgId,jdbcType=NUMERIC}")
	void deleteSysOrg(@Param("orgId") Long orgId);
	
	@Select(" SELECT ORG_ID,PARENT_ID,ORG_NAME,ORG_TYPE,ORG_CODE,CUSTOM_CODE,SORT,ACTIVE,CREATE_OPR_ID,CREATE_TIME,UPD_OPR_ID,UPDATE_TIME,REMARK FROM SYS_ORG WHERE ORG_ID=#{orgId,jdbcType=NUMERIC}")
	SysOrg querySysOrgById(@Param("orgId") Long orgId);

	@Select(" SELECT ORG_ID,PARENT_ID,ORG_NAME,ORG_TYPE,ORG_CODE,CUSTOM_CODE,SORT,ACTIVE,CREATE_OPR_ID,CREATE_TIME,UPD_OPR_ID,UPDATE_TIME,REMARK FROM SYS_ORG ORDER BY SORT ASC")
	List<SysOrg> querySysOrg();

	@Select(" SELECT ORG_ID,PARENT_ID,ORG_NAME,ORG_TYPE,ORG_CODE,CUSTOM_CODE,SORT,ACTIVE,CREATE_OPR_ID,CREATE_TIME,UPD_OPR_ID,UPDATE_TIME,REMARK FROM SYS_ORG WHERE ORG_NAME LIKE '%${orgName}%' AND ORG_CODE LIKE '%${orgCode}%' AND CUSTOM_CODE LIKE '%${customCode}%' AND PARENT_ID=#{parentId,jdbcType=NUMERIC} ORDER BY SORT ASC")
	List<SysOrg> querySysOrgByOrgNameAndOrgCodeAndCustomCodeAndParentId(@Param("orgName") String orgName, @Param("orgCode") String orgCode, @Param("customCode") String customCode, @Param("parentId") Long parentId);

}