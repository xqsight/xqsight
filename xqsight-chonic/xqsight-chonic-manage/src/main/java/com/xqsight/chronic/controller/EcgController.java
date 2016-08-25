/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.chronic.controller;

import com.github.pagehelper.Page;
import com.xqsight.chronic.model.Ecg;
import com.xqsight.chronic.service.EcgService;
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
 * <p>心电记录表 controller</p>
 * <p>Table: ECG - 心电记录表</p>
 * @since 2016-05-09 07:50:46
 */
@RestController
@RequestMapping("/ecg/")
public class EcgController{

	@Autowired
	private EcgService ecgService;

	@RequestMapping("save")
	public Object saveEcg(HttpServletRequest request, Ecg ecg) {
		if(!WebUtils.isMobile(request))
			ecg.setCreateOprId(SSOUtils.getCurrentUserId().toString());
		if(ecg.getBooldId() != null && ecg.getBooldId() > 0){
			ecgService.updateEcg(ecg);
		}else {
			ecgService.saveEcg(ecg);
		}
		return MessageSupport.successMsg("保存成功");
	}
	
	@RequestMapping("update")
	public Object updateEcg(HttpServletRequest request, Ecg ecg) {
		if(!WebUtils.isMobile(request))
			ecg.setUpdOprId(SSOUtils.getCurrentUserId().toString());

		ecgService.updateEcg(ecg);
		return MessageSupport.successMsg("修改成功");
	}
	
	@RequestMapping("delete")
	public Object deleteEcg(Long booldId) {
		ecgService.deleteEcg(booldId);
		return MessageSupport.successMsg("删除成功");
	}
	
	@RequestMapping("query")
	public Object queryEcg(XqsightPage xqsightPage) {
		Page<?> page = XqsightPageHelper.startPageWithPageIndex(xqsightPage.getiDisplayStart(), xqsightPage.getiDisplayLength());
		List<Ecg> ecgs = ecgService.queryEcg();
		xqsightPage.setTotalCount(page.getTotal());
		return MessageSupport.successDataTableMsg(xqsightPage, ecgs);
	}

	@RequestMapping("querybyid")
	public Object queryEcg(Long booldId) {
		Ecg ecg = ecgService.queryEcgById(booldId);
		return MessageSupport.successDataMsg(ecg, "查询成功");
	}
	
	@RequestMapping("querybyuser")
	public Object queryEcg(@RequestParam(required = false) Integer durDay, String id) {
		List<Ecg> ecgs;
		if(durDay != null){
			ecgs = ecgService.queryEcgByUserAndDurDay(id, durDay);
		} else {
			ecgs = ecgService.queryEcgByUser(id);
		}
		return MessageSupport.successDataMsg(ecgs, "查询成功");
	}
}