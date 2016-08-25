/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.chronic.controller;

import com.github.pagehelper.Page;
import com.xqsight.chronic.model.BooldPressure;
import com.xqsight.chronic.service.BooldPressureService;
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
 * <p>血压记录表 controller</p>
 * <p>Table: BOOLD_PRESSURE - 血压记录表</p>
 * @since 2016-05-09 07:50:31
 */
@RestController
@RequestMapping("/boold/pressure/")
public class BooldPressureController{

	@Autowired
	private BooldPressureService booldPressureService;

	@RequestMapping("save")
	public Object saveBooldPressure(HttpServletRequest request, BooldPressure booldPressure) {
		if(!WebUtils.isMobile(request))
			booldPressure.setCreateOprId(SSOUtils.getCurrentUserId().toString());
		if(booldPressure.getBooldId() != null && booldPressure.getBooldId() > 0) {
			booldPressureService.updateBooldPressure(booldPressure);
		}else {
			booldPressureService.saveBooldPressure(booldPressure);
		}
		return MessageSupport.successMsg("保存成功");
	}
	
	@RequestMapping("update")
	public Object updateBooldPressure(HttpServletRequest request, BooldPressure booldPressure) {
		if(!WebUtils.isMobile(request))
			booldPressure.setUpdOprId(SSOUtils.getCurrentUserId().toString());

		booldPressureService.updateBooldPressure(booldPressure);
		return MessageSupport.successMsg("修改成功");
	}
	
	@RequestMapping("delete")
	public Object deleteBooldPressure(Long booldId) {
		booldPressureService.deleteBooldPressure(booldId);
		return MessageSupport.successMsg("删除成功");
	}
	
	@RequestMapping("query")
	public Object queryBooldPressure(XqsightPage xqsightPage) {
		Page<?> page = XqsightPageHelper.startPageWithPageIndex(xqsightPage.getiDisplayStart(), xqsightPage.getiDisplayLength());
		List<BooldPressure> booldPressures = booldPressureService.queryBooldPressure();
		xqsightPage.setTotalCount(page.getTotal());
		return MessageSupport.successDataTableMsg(xqsightPage, booldPressures);
	}

	@RequestMapping("querybyid")
	public Object queryBooldPressure(Long booldId) {
		BooldPressure booldPressure = booldPressureService.queryBooldPressureById(booldId);
		return MessageSupport.successDataMsg(booldPressure, "查询成功");
	}
	
	@RequestMapping("querybyuser")
	public Object queryBooldPressure(@RequestParam(required = false) Integer durDay, String id) {
		List<BooldPressure> booldPressures;
		if(durDay != null){
			booldPressures = booldPressureService.queryBooldPressureByUserAndDurDay(id, durDay);
		} else {
			booldPressures = booldPressureService.queryBooldPressureByUser(id);
		}
		return MessageSupport.successDataMsg(booldPressures, "查询成功");
	}
}