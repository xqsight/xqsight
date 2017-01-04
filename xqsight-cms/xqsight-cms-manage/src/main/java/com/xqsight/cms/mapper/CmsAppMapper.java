/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.cms.mapper;

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

	@Insert(" insert into cms_app(app_code,app_name,app_domain,app_logo,app_keyword,app_copyright,app_style,app_managerid,app_description,active,create_time,create_opr_id,update_time,upd_opr_id,remark)values(#{appCode,jdbcType=VARCHAR},#{appName,jdbcType=VARCHAR},#{appDomain,jdbcType=VARCHAR},#{appLogo,jdbcType=VARCHAR},#{appKeyword,jdbcType=VARCHAR},#{appCopyright,jdbcType=VARCHAR},#{appStyle,jdbcType=VARCHAR},#{appManagerid,jdbcType=VARCHAR},#{appDescription,jdbcType=VARCHAR},#{active,jdbcType=NUMERIC},#{createTime,jdbcType=TIMESTAMP},#{createOprId,jdbcType=VARCHAR},#{updateTime,jdbcType=TIMESTAMP},#{updOprId,jdbcType=VARCHAR},#{remark,jdbcType=VARCHAR})")
	@Options(useGeneratedKeys = true, keyProperty = "appId")
	void saveCmsApp(CmsApp cmsApp);
	
	@Update(" update cms_app set app_code=#{appCode,jdbcType=VARCHAR},app_name=#{appName,jdbcType=VARCHAR},app_domain=#{appDomain,jdbcType=VARCHAR},app_logo=#{appLogo,jdbcType=VARCHAR},app_keyword=#{appKeyword,jdbcType=VARCHAR},app_copyright=#{appCopyright,jdbcType=VARCHAR},app_style=#{appStyle,jdbcType=VARCHAR},app_managerid=#{appManagerid,jdbcType=VARCHAR},app_description=#{appDescription,jdbcType=VARCHAR},active=#{active,jdbcType=NUMERIC},update_time=#{updateTime,jdbcType=TIMESTAMP},upd_opr_id=#{updOprId,jdbcType=VARCHAR},remark=#{remark,jdbcType=VARCHAR} where app_id=#{appId,jdbcType=NUMERIC}")
	void updateCmsApp(CmsApp cmsApp);
	
	@Delete(" delete from cms_app where app_id=#{appId,jdbcType=NUMERIC}")
	void deleteCmsApp(@Param("appId") Long appId);
	
	@Select(" select app_id,app_name,app_code,app_domain,app_logo,app_keyword,app_copyright,app_style,app_managerid,app_description,active,create_time,create_opr_id,update_time,upd_opr_id,remark from cms_app")
	List<CmsApp> queryCmsApp();

	@Select(" select app_id,app_name,app_code,app_domain,app_logo,app_keyword,app_copyright,app_style,app_managerid,app_description,active,create_time,create_opr_id,update_time,upd_opr_id,remark from cms_app where app_code like '%${appCode,jdbcType=VARCHAR}%'")
	List<CmsApp> queryCmsAppByLikeAppCode(@Param("appCode") String appCode);
	
	@Select(" select app_id,app_name,app_code,app_domain,app_logo,app_keyword,app_copyright,app_style,app_managerid,app_description,active,create_time,create_opr_id,update_time,upd_opr_id,remark from cms_app where app_id=#{appId,jdbcType=NUMERIC}")
	CmsApp queryCmsAppById(@Param("appId") Long appId);
	
	@Select(" select app_id from cms_app where app_code =#{appCode,jdbcType=VARCHAR}")
	Long queryExistByCode(@Param("appCode") String appCode);

}