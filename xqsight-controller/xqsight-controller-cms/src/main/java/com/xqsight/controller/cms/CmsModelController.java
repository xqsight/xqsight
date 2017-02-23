/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.controller.cms;

import com.github.pagehelper.Page;
import com.xqsight.common.model.XqsightPage;
import com.xqsight.common.core.support.XqsightPageHelper;
import com.xqsight.common.support.MessageSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.xqsight.cms.model.CmsModel;
import com.xqsight.cms.service.CmsModelService;

/**
 * <p>模块表 controller</p>
 * <p>Table: cms_model - 模块表</p>
 * @since 2017-02-23 04:52:18
 * @author wangganggang
 */
@RestController
@RequestMapping("/cms/model/")
public class CmsModelController{

	@Autowired
	private CmsModelService cmsModelService;

	@RequestMapping("save")
	public Object save(CmsModel cmsModel) {
		cmsModelService.save(cmsModel,true);
		return MessageSupport.successMsg("保存成功");
	}
	
	@RequestMapping("update")
	public Object update(CmsModel cmsModel) {
		cmsModelService.update(cmsModel, true);
		return MessageSupport.successMsg("修改成功");
	}
	
	@RequestMapping("delete")
	public Object delete(Long modelId) {
		cmsModelService.delete(modelId);
		return MessageSupport.successMsg("删除成功");
	}

	@RequestMapping("logicDel")
	public Object logicDel(Long modelId) {
		cmsModelService.logicDel(modelId);
		return MessageSupport.successMsg("删除成功");
	}

	@RequestMapping("query")
	public Object query(XqsightPage xqsightPage) {
		Page page = XqsightPageHelper.startPageWithPageIndex(xqsightPage.getiDisplayStart(), xqsightPage.getiDisplayLength());
		List<CmsModel> cmsModels = cmsModelService.search(null);
		xqsightPage.setTotalCount(page.getTotal());
		return MessageSupport.successDataTableMsg(xqsightPage, cmsModels);
    }

	@RequestMapping("querybyid")
	public Object queryById(Long modelId) {
		CmsModel cmsModel = cmsModelService.get(modelId);
		return MessageSupport.successDataMsg(cmsModel, "查询成功");
	}

	@RequestMapping("queryall")
	public Object queryall() {
		List<CmsModel> cmsModels = cmsModelService.search(null);
		return MessageSupport.successDataMsg(cmsModels, "查询成功");
    }

}