/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.cms.mysqlmapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xqsight.cms.model.CmsApp;

/**
 * <p>数据库Mapper类</p>
 * <p>系统应用表</p>
 * @since 2016-05-07 08:00:44
 */
public interface CmsAppMapper {

	@Insert(" INSERT INTO CMS_APP(APP_CODE,APP_NAME,APP_DOMAIN,APP_LOGO,APP_KEYWORD,APP_COPYRIGHT,APP_STYLE,APP_MANAGERID,APP_DESCRIPTION,ACTIVE,CREATE_TIME,CREATE_OPR_ID,UPDATE_TIME,UPD_OPR_ID,REMARK)VALUES(#{appCode,jdbcType=VARCHAR},#{appName,jdbcType=VARCHAR},#{appDomain,jdbcType=VARCHAR},#{appLogo,jdbcType=VARCHAR},#{appKeyword,jdbcType=VARCHAR},#{appCopyright,jdbcType=VARCHAR},#{appStyle,jdbcType=VARCHAR},#{appManagerid,jdbcType=VARCHAR},#{appDescription,jdbcType=VARCHAR},#{active,jdbcType=NUMERIC},#{createTime,jdbcType=TIMESTAMP},#{createOprId,jdbcType=VARCHAR},#{updateTime,jdbcType=TIMESTAMP},#{updOprId,jdbcType=VARCHAR},#{remark,jdbcType=VARCHAR})")
	@Options(useGeneratedKeys = true, keyProperty = "appId")
	void saveCmsApp(CmsApp cmsApp);
	
	@Update(" UPDATE CMS_APP SET APP_CODE=#{appCode,jdbcType=VARCHAR},APP_NAME=#{appName,jdbcType=VARCHAR},APP_DOMAIN=#{appDomain,jdbcType=VARCHAR},APP_LOGO=#{appLogo,jdbcType=VARCHAR},APP_KEYWORD=#{appKeyword,jdbcType=VARCHAR},APP_COPYRIGHT=#{appCopyright,jdbcType=VARCHAR},APP_STYLE=#{appStyle,jdbcType=VARCHAR},APP_MANAGERID=#{appManagerid,jdbcType=VARCHAR},APP_DESCRIPTION=#{appDescription,jdbcType=VARCHAR},ACTIVE=#{active,jdbcType=NUMERIC},UPDATE_TIME=#{updateTime,jdbcType=TIMESTAMP},UPD_OPR_ID=#{updOprId,jdbcType=VARCHAR},REMARK=#{remark,jdbcType=VARCHAR} WHERE APP_ID=#{appId,jdbcType=NUMERIC}")
	void updateCmsApp(CmsApp cmsApp);
	
	@Delete(" DELETE FROM CMS_APP WHERE APP_ID=#{appId,jdbcType=NUMERIC}")
	void deleteCmsApp(@Param("appId") Long appId);
	
	@Select(" SELECT APP_ID,APP_NAME,APP_CODE,APP_DOMAIN,APP_LOGO,APP_KEYWORD,APP_COPYRIGHT,APP_STYLE,APP_MANAGERID,APP_DESCRIPTION,ACTIVE,CREATE_TIME,CREATE_OPR_ID,UPDATE_TIME,UPD_OPR_ID,REMARK FROM CMS_APP")
	List<CmsApp> queryCmsApp();

	@Select(" SELECT APP_ID,APP_NAME,APP_CODE,APP_DOMAIN,APP_LOGO,APP_KEYWORD,APP_COPYRIGHT,APP_STYLE,APP_MANAGERID,APP_DESCRIPTION,ACTIVE,CREATE_TIME,CREATE_OPR_ID,UPDATE_TIME,UPD_OPR_ID,REMARK FROM CMS_APP WHERE APP_CODE LIKE '%${appCode,jdbcType=VARCHAR}%'")
	List<CmsApp> queryCmsAppByLikeAppCode(@Param("appCode") String appCode);
	
	@Select(" SELECT APP_ID,APP_NAME,APP_CODE,APP_DOMAIN,APP_LOGO,APP_KEYWORD,APP_COPYRIGHT,APP_STYLE,APP_MANAGERID,APP_DESCRIPTION,ACTIVE,CREATE_TIME,CREATE_OPR_ID,UPDATE_TIME,UPD_OPR_ID,REMARK FROM CMS_APP WHERE APP_ID=#{appId,jdbcType=NUMERIC}")
	CmsApp queryCmsAppById(@Param("appId") Long appId);
	
	@Select(" SELECT APP_id FROM CMS_APP WHERE APP_CODE=#{appCode,jdbcType=VARCHAR}")
	Long queryExistByCode(@Param("appCode") String appCode);

}