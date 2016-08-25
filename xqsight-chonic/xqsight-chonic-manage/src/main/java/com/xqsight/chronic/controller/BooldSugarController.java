/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.chronic.controller;

import com.github.pagehelper.Page;
import com.xqsight.chronic.model.BooldSugar;
import com.xqsight.chronic.service.BooldSugarService;
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
 * <p>血糖记录表 controller</p>
 * <p>Table: BOOLD_SUGAR - 血糖记录表</p>
 * @since 2016-05-09 07:50:39
 */
@RestController
@RequestMapping("/boold/sugar/")
public class BooldSugarController{

	@Autowired
	private BooldSugarService booldSugarService;

	@RequestMapping("save")
	public Object saveBooldSugar(HttpServletRequest request, BooldSugar booldSugar) {
		if(!WebUtils.isMobile(request))
			booldSugar.setCreateOprId(SSOUtils.getCurrentUserId().toString());
		if(booldSugar.getBooldId() != null && booldSugar.getBooldId() > 0){
			booldSugarService.updateBooldSugar(booldSugar);
		}else {
			booldSugarService.saveBooldSugar(booldSugar);
		}
		return MessageSupport.successMsg("保存成功");
	}
	
	@RequestMapping("update")
	public Object updateBooldSugar(HttpServletRequest request, BooldSugar booldSugar) {
		if(!WebUtils.isMobile(request))
			booldSugar.setUpdOprId(SSOUtils.getCurrentUserId().toString());

		booldSugarService.updateBooldSugar(booldSugar);
		return MessageSupport.successMsg("修改成功");
	}
	
	@RequestMapping("delete")
	public Object deleteBooldSugar(Long booldId) {
		booldSugarService.deleteBooldSugar(booldId);
		return MessageSupport.successMsg("删除成功");
	}
	
	@RequestMapping("query")
	public Object queryBooldSugar(XqsightPage xqsightPage) {
		Page<?> page = XqsightPageHelper.startPageWithPageIndex(xqsightPage.getiDisplayStart(), xqsightPage.getiDisplayLength());
		List<BooldSugar> booldSugars = booldSugarService.queryBooldSugar();
		xqsightPage.setTotalCount(page.getTotal());
		return MessageSupport.successDataTableMsg(xqsightPage, booldSugars);
	}

	@RequestMapping("querybyid")
	public Object queryBooldSugar(Long booldId) {
		BooldSugar booldSugar = booldSugarService.queryBooldSugarById(booldId);
		return MessageSupport.successDataMsg(booldSugar, "查询成功");
	}
	
	@RequestMapping("querybyuser")
	public Object queryBooldSugar(@RequestParam(required = false) Integer durDay, String id) {
		List<BooldSugar> booldSugars;
		if(durDay != null){
			booldSugars = booldSugarService.queryBooldSugarByUserAndDurDay(id, durDay);
		} else {
			booldSugars = booldSugarService.queryBooldSugarByUser(id);
		}
		return MessageSupport.successDataMsg(booldSugars, "查询成功");
	}
}