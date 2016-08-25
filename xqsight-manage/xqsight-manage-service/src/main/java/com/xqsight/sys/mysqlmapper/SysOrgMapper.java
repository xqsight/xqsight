/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.sys.mysqlmapper;


import com.xqsight.sys.model.SysOrg;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * <p>组织机构表数据库Mapper类</p>
 * <p>组织机构表</p>
 * @since 2016-06-20 07:55:50
 */
public interface SysOrgMapper {

	@Insert(" insert into sys_org(parent_id,org_name,org_type,org_code,custom_code,sort,icon ,active,create_opr_id,create_time,remark)values(#{parentId,jdbcType=NUMERIC},#{orgName,jdbcType=VARCHAR},#{orgType,jdbcType=VARCHAR},#{orgCode,jdbcType=VARCHAR},#{customCode,jdbcType=VARCHAR},#{sort,jdbcType=NUMERIC},#{icon,jdbcType=VARCHAR},#{active,jdbcType=NUMERIC},#{createOprId,jdbcType=VARCHAR},#{createTime,jdbcType=TIMESTAMP},#{remark,jdbcType=VARCHAR})")
	@Options(useGeneratedKeys = true, keyProperty = "orgId")
	void saveSysOrg(SysOrg sysOrg);
	
	@Update(" update sys_org set parent_id=#{parentId,jdbcType=NUMERIC},org_name=#{orgName,jdbcType=VARCHAR},org_type=#{orgType,jdbcType=VARCHAR},org_code=#{orgCode,jdbcType=VARCHAR},custom_code=#{customCode,jdbcType=VARCHAR},sort=#{sort,jdbcType=NUMERIC},icon=#{icon,jdbcType=VARCHAR},active=#{active,jdbcType=NUMERIC},upd_opr_id=#{updOprId,jdbcType=VARCHAR},update_time=#{updateTime,jdbcType=TIMESTAMP},remark=#{remark,jdbcType=VARCHAR} where org_id=#{orgId,jdbcType=NUMERIC}")
	void updateSysOrg(SysOrg sysOrg);
	
	@Delete(" delete from sys_org where org_id=#{orgId,jdbcType=NUMERIC}")
	void deleteSysOrg(@Param("orgId") long orgId);
	
	@Select(" select org_id,parent_id,org_name,org_type,org_code,custom_code,sort,active,create_opr_id,create_time,upd_opr_id,update_time,remark from sys_org where org_id=#{orgId,jdbcType=NUMERIC}")
	SysOrg querySysOrgById(@Param("orgId") long orgId);

	@Select(" select org_id,parent_id,org_name,org_type,org_code,custom_code,sort,active,create_opr_id,create_time,upd_opr_id,update_time,remark from sys_org order by sort asc")
	List<SysOrg> querySysOrg();

	@Select(" select org_id,parent_id,org_name,org_type,org_code,custom_code,sort,active,create_opr_id,create_time,upd_opr_id,update_time,remark from sys_org where org_name like '%${orgName}%' and org_code like '%${orgCode}%' and custom_code like '%${customCode}%' and parent_id=#{parentId,jdbcType=NUMERIC} order by sort asc")
	List<SysOrg> querySysOrgByOrgNameAndOrgCodeAndCustomCodeAndParentId(@Param("orgName") String orgName, @Param("orgCode") String orgCode, @Param("customCode") String customCode, @Param("parentId") long parentId);
}