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

import com.xqsight.cms.model.CmsModel;

/**
 * <p>数据库Mapper类</p>
 * <p>模块信息表</p>
 * @since 2016-05-07 08:00:53
 */
public interface CmsModelMapper {

	@Insert(" insert into cms_model(app_id,parent_id,model_code,model_class,model_title,model_description,model_thumbnails,model_sort,model_url,active,create_time,create_user_id,remark)values(#{appId,jdbcType=NUMERIC},#{parentId,jdbcType=NUMERIC},#{modelCode,jdbcType=VARCHAR},#{modelClass,jdbcType=VARCHAR},#{modelTitle,jdbcType=VARCHAR},#{modelDescription,jdbcType=VARCHAR},#{modelThumbnails,jdbcType=VARCHAR},#{modelSort,jdbcType=NUMERIC},#{modelUrl,jdbcType=VARCHAR},#{active,jdbcType=NUMERIC},#{createTime,jdbcType=TIMESTAMP},#{createUserId,jdbcType=VARCHAR},#{remark,jdbcType=VARCHAR})")
	@Options(useGeneratedKeys = true, keyProperty = "modelId")
	void saveCmsModel(CmsModel cmsModel);
	
	@Update(" update cms_model set app_id=#{appId,jdbcType=NUMERIC},parent_id=#{parentId,jdbcType=NUMERIC},model_class=#{modelClass,jdbcType=VARCHAR},model_code=#{modelCode,jdbcType=VARCHAR},model_title=#{modelTitle,jdbcType=VARCHAR},model_description=#{modelDescription,jdbcType=VARCHAR},model_thumbnails=#{modelThumbnails,jdbcType=VARCHAR},model_sort=#{modelSort,jdbcType=NUMERIC},model_url=#{modelUrl,jdbcType=VARCHAR},active=#{active,jdbcType=NUMERIC},update_time=#{updateTime,jdbcType=TIMESTAMP},update_user_id=#{updateUserId,jdbcType=VARCHAR},remark=#{remark,jdbcType=VARCHAR} where model_id=#{modelId,jdbcType=NUMERIC}")
	void updateCmsModel(CmsModel cmsModel);
	
	@Delete(" delete from cms_model where model_id=#{modelId,jdbcType=NUMERIC}")
	void deleteCmsModel(@Param("modelId") Long modelId);

	@Select(" select model_id,app_id,parent_id,model_code,model_class,model_title,model_description,model_thumbnails,model_sort,model_url,active,create_time,create_user_id,update_time,update_user_id,remark from cms_model where model_id=#{modelId,jdbcType=NUMERIC}")
	CmsModel queryCmsModelById(@Param("modelId") Long modelId);

	@Select(" select model_id from cms_model where model_codE=#{modelCode,jdbcType=VARCHAR}")
	Long queryExistByCode(@Param("modelCode") String modelCode);
	
	@Select(" select model_id,app_id,parent_id,model_code,model_class,model_title,model_description,model_thumbnails,model_sort,model_url,active,create_time,create_user_id,update_time,update_user_id,remark from cms_model where app_id=#{appId,jdbcType=NUMERIC} order by model_sort asc")
	List<CmsModel> queryCmsModelByAppId(@Param("AppId") Long appId);

	@Select(" select model_id,app_id,parent_id,model_code,model_class,model_title,model_description,model_thumbnails,model_sort,model_url,active,create_time,create_user_id,update_time,update_user_id,remark from cms_model order by model_sort asc")
	List<CmsModel> queryCmsModel();

	@Select(" select model_id,app_id,parent_id,model_code,model_class,model_title,model_description,model_thumbnails,model_sort,model_url,active,create_time,create_user_id,update_time,update_user_id,remark from cms_model where model_code like '%${modelCode}%' and model_title like '%${modelName}%' and parent_id = #{parentId,jdbcType=NUMERIC} order by model_sort asc")
	List<CmsModel> queryCmsModelByModelNameAndModelCodeAndParentId(@Param("modelName") String modelName, @Param("modelCode") String modelCode, @Param("parentId") Long parentId);


}