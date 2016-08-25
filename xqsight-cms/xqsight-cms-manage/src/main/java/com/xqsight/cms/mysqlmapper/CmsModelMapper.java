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

import com.xqsight.cms.model.CmsModel;

/**
 * <p>数据库Mapper类</p>
 * <p>模块信息表</p>
 * @since 2016-05-07 08:00:53
 */
public interface CmsModelMapper {

	@Insert(" INSERT INTO CMS_MODEL(APP_ID,PARENT_ID,MODEL_CODE,MODEL_CLASS,MODEL_TITLE,MODEL_DESCRIPTION,MODEL_THUMBNAILS,MODEL_SORT,MODEL_URL,ACTIVE,CREATE_TIME,CREATE_OPR_ID,REMARK)VALUES(#{appId,jdbcType=NUMERIC},#{parentId,jdbcType=NUMERIC},#{modelCode,jdbcType=VARCHAR},#{modelClass,jdbcType=VARCHAR},#{modelTitle,jdbcType=VARCHAR},#{modelDescription,jdbcType=VARCHAR},#{modelThumbnails,jdbcType=VARCHAR},#{modelSort,jdbcType=NUMERIC},#{modelUrl,jdbcType=VARCHAR},#{active,jdbcType=NUMERIC},#{createTime,jdbcType=TIMESTAMP},#{createOprId,jdbcType=VARCHAR},#{remark,jdbcType=VARCHAR})")
	@Options(useGeneratedKeys = true, keyProperty = "modelId")
	void saveCmsModel(CmsModel cmsModel);
	
	@Update(" UPDATE CMS_MODEL SET APP_ID=#{appId,jdbcType=NUMERIC},PARENT_ID=#{parentId,jdbcType=NUMERIC},MODEL_CLASS=#{modelClass,jdbcType=VARCHAR},MODEL_CODE=#{modelCode,jdbcType=VARCHAR},MODEL_TITLE=#{modelTitle,jdbcType=VARCHAR},MODEL_DESCRIPTION=#{modelDescription,jdbcType=VARCHAR},MODEL_THUMBNAILS=#{modelThumbnails,jdbcType=VARCHAR},MODEL_SORT=#{modelSort,jdbcType=NUMERIC},MODEL_URL=#{modelUrl,jdbcType=VARCHAR},ACTIVE=#{active,jdbcType=NUMERIC},UPDATE_TIME=#{updateTime,jdbcType=TIMESTAMP},UPD_OPR_ID=#{updOprId,jdbcType=VARCHAR},REMARK=#{remark,jdbcType=VARCHAR} WHERE MODEL_ID=#{modelId,jdbcType=NUMERIC}")
	void updateCmsModel(CmsModel cmsModel);
	
	@Delete(" DELETE FROM CMS_MODEL WHERE MODEL_ID=#{modelId,jdbcType=NUMERIC}")
	void deleteCmsModel(@Param("modelId") Long modelId);

	@Select(" SELECT MODEL_ID,APP_ID,PARENT_ID,MODEL_CODE,MODEL_CLASS,MODEL_TITLE,MODEL_DESCRIPTION,MODEL_THUMBNAILS,MODEL_SORT,MODEL_URL,ACTIVE,CREATE_TIME,CREATE_OPR_ID,UPDATE_TIME,UPD_OPR_ID,REMARK FROM CMS_MODEL WHERE MODEL_ID=#{modelId,jdbcType=NUMERIC}")
	CmsModel queryCmsModelById(@Param("modelId") Long modelId);

	@Select(" SELECT MODEL_ID FROM CMS_MODEL WHERE MODEL_CODE=#{modelCode,jdbcType=VARCHAR}")
	Long queryExistByCode(@Param("modelCode") String modelCode);
	
	@Select(" SELECT MODEL_ID,APP_ID,PARENT_ID,MODEL_CODE,MODEL_CLASS,MODEL_TITLE,MODEL_DESCRIPTION,MODEL_THUMBNAILS,MODEL_SORT,MODEL_URL,ACTIVE,CREATE_TIME,CREATE_OPR_ID,UPDATE_TIME,UPD_OPR_ID,REMARK FROM CMS_MODEL WHERE APP_ID=#{appId,jdbcType=NUMERIC} ORDER BY MODEL_SORT ASC")
	List<CmsModel> queryCmsModelByAppId(@Param("AppId") Long appId);

	@Select(" SELECT MODEL_ID,APP_ID,PARENT_ID,MODEL_CODE,MODEL_CLASS,MODEL_TITLE,MODEL_DESCRIPTION,MODEL_THUMBNAILS,MODEL_SORT,MODEL_URL,ACTIVE,CREATE_TIME,CREATE_OPR_ID,UPDATE_TIME,UPD_OPR_ID,REMARK FROM CMS_MODEL ORDER BY MODEL_SORT ASC")
	List<CmsModel> queryCmsModel();

	@Select(" SELECT MODEL_ID,APP_ID,PARENT_ID,MODEL_CODE,MODEL_CLASS,MODEL_TITLE,MODEL_DESCRIPTION,MODEL_THUMBNAILS,MODEL_SORT,MODEL_URL,ACTIVE,CREATE_TIME,CREATE_OPR_ID,UPDATE_TIME,UPD_OPR_ID,REMARK FROM CMS_MODEL WHERE MODEL_CODE LIKE '%${modelCode}%' AND MODEL_TITLE LIKE '%${modelName}%' AND PARENT_ID = #{parentId,jdbcType=NUMERIC} ORDER BY MODEL_SORT ASC")
	List<CmsModel> queryCmsModelByModelNameAndModelCodeAndParentId(@Param("modelName") String modelName, @Param("modelCode") String modelCode, @Param("parentId") Long parentId);


}