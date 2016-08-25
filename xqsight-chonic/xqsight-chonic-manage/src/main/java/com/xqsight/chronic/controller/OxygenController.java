/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.chronic.controller;

import com.github.pagehelper.Page;
import com.xqsight.chronic.model.Oxygen;
import com.xqsight.chronic.service.OxygenService;
import com.xqsight.common.model.XqsightPage;
import com.xqsight.common.support.MessageSupport;
import com.xqsight.common.support.XqsightPageHelper;
import com.xqsight.commons.web.WebUtils;
import com.xqsight.sso.utils.SSOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * <p>血氧记录表 controller</p>
 * <p>Table: OXYGEN - 血氧记录表</p>
 * @since 2016-05-09 07:51:50
 */
@RestController
@RequestMapping("/oxygen/")
public class OxygenController{

	@Autowired
	private OxygenService oxygenService;

	@RequestMapping("save")
	public Object saveOxygen(HttpServletRequest request, Oxygen oxygen) {
		if(!WebUtils.isMobile(request))
			oxygen.setCreateOprId(SSOUtils.getCurrentUserId().toString());
		if(oxygen.getBooldId() != null && oxygen.getBooldId() > 0){
			oxygenService.updateOxygen(oxygen);
		}else{
			oxygenService.saveOxygen(oxygen);
		}
		return MessageSupport.successMsg("保存成功");
	}
	
	@RequestMapping("update")
	public Object updateOxygen(HttpServletRequest request, Oxygen oxygen) {
		if(!WebUtils.isMobile(request))
			oxygen.setUpdOprId(SSOUtils.getCurrentUserId().toString());

		oxygenService.updateOxygen(oxygen);
		return MessageSupport.successMsg("修改成功");
	}
	
	@RequestMapping("delete")
	public Object deleteOxygen(Long booldId) {
		oxygenService.deleteOxygen(booldId);
		return MessageSupport.successMsg("删除成功");
	}
	
	@RequestMapping("query")
	public Object queryOxygen(XqsightPage xqsightPage) {
		Page<?> page = XqsightPageHelper.startPageWithPageIndex(xqsightPage.getiDisplayStart(), xqsightPage.getiDisplayLength());
		List<Oxygen> oxygens = oxygenService.queryOxygen();
		xqsightPage.setTotalCount(page.getTotal());
		return MessageSupport.successDataTableMsg(xqsightPage, oxygens);
	}

	@RequestMapping("querybyid")
	public Object queryOxygen(Long booldId) {
		Oxygen oxygen = oxygenService.queryOxygenById(booldId);
		return MessageSupport.successDataMsg(oxygen,"查询成功");
	}
	
	@RequestMapping("querybyuser")
	public Object queryOxygen(@RequestParam(required = false)Integer durDay, String id) {
		List<Oxygen> oxygens ;
		if(durDay != null){
			oxygens = oxygenService.queryOxygenByUserAndDurDay(id, durDay);
		} else {
			oxygens = oxygenService.queryOxygenByUser(id);
		}
		return MessageSupport.successDataMsg(oxygens,"查询成功");
	}
}