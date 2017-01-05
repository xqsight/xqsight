/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.cms.controller;

import com.xqsight.cms.model.CmsModel;
import com.xqsight.cms.service.CmsModelService;
import com.xqsight.commons.support.MessageSupport;
import com.xqsight.sso.utils.SSOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * <p>controller</p>
 * <p>Table: CMS_MODEL - 模块信息表</p>
 * @since 2016-05-07 08:03:01
 */
@RestController
@RequestMapping("/cms/model/")
public class CmsModelController{

	@Autowired
	private CmsModelService cmsModelService;

	@RequestMapping("save")
	public Object saveCmsModel(CmsModel cmsModel) {
		cmsModel.setCreateUserId(SSOUtils.getCurrentUserId().toString());
		Long modelId = cmsModelService.queryExistByCode(cmsModel.getModelCode());
		if(modelId != null && modelId > 0){
			return MessageSupport.failureMsg("模块编号已经存在");
		}
		cmsModelService.saveCmsModel(cmsModel);
		return MessageSupport.successMsg("保存成功");
	}
	
	@RequestMapping("update")
	public Object updateCmsModel(CmsModel cmsModel) {
		cmsModel.setUpdateUserId(SSOUtils.getCurrentUserId().toString());
		Long modelId = cmsModelService.queryExistByCode(cmsModel.getModelCode());
		if(modelId != null && modelId != cmsModel.getModelId()){
			return MessageSupport.failureMsg("模块编号已经存在");
		}
		cmsModelService.updateCmsModel(cmsModel);
		return MessageSupport.successMsg("修改成功");
	}
	
	@RequestMapping("delete")
	public Object deleteCmsModel(Long modelId) {
		cmsModelService.deleteCmsModel(modelId);
		return MessageSupport.successMsg("删除成功");
	}
	
	@RequestMapping("querybyappid")
	public Object queryCmsModelAll(Long appId) {
		List<CmsModel> cmsModels = cmsModelService.queryCmsModelByAppId(appId);
		return MessageSupport.successDataMsg(cmsModels, "查询成功");
	}

	@RequestMapping("querybyid")
	public Object queryCmsModel(Long modelId) {
		CmsModel cmsModel = cmsModelService.queryCmsModelById(modelId);
		return MessageSupport.successDataMsg(cmsModel, "查询成功");
	}

	@RequestMapping("querytree")
	public Object queryCmsModelToTree() {
		CmsModel cmsModel = cmsModelService.queryCmsModelToTree();
		return MessageSupport.successDataMsg(cmsModel, "查询成功");
	}

	@RequestMapping("query")
	public Object queryCmsModelByModelNameAndModelCodeAndParentId(String modelName,String modelCode,Long parentId) {
		List<CmsModel> cmsModels = cmsModelService.queryCmsModelByModelNameAndModelCodeAndParentId(modelName,modelCode,parentId);
		return MessageSupport.successDataMsg(cmsModels, "查询成功");
	}
}