/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.chronic.controller;

import com.github.pagehelper.Page;
import com.xqsight.chronic.model.StepCounter;
import com.xqsight.chronic.service.StepCounterService;
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
 * <p>计步器表 controller</p>
 * <p>Table: STEP_COUNTER - 计步器表</p>
 * @since 2016-05-09 07:51:57
 */
@RestController
@RequestMapping("/step/counter/")
public class StepCounterController{

	@Autowired
	private StepCounterService stepCounterService;

	@RequestMapping("save")
	public Object saveStepCounter(HttpServletRequest request, StepCounter stepCounter) {
		if(!WebUtils.isMobile(request))
			stepCounter.setCreateOprId(SSOUtils.getCurrentUserId().toString());

		stepCounterService.saveStepCounter(stepCounter);
		return MessageSupport.successMsg("保存成功");
	}
	
	@RequestMapping("update")
	public Object updateStepCounter(HttpServletRequest request, StepCounter stepCounter) {
		if(!WebUtils.isMobile(request))
			stepCounter.setUpdOprId(SSOUtils.getCurrentUserId().toString());

		stepCounterService.updateStepCounter(stepCounter);
		return MessageSupport.successMsg("修改成功");
	}
	
	@RequestMapping("delete")
	public Object deleteStepCounter(HttpServletRequest request, Long stepId) {
		stepCounterService.deleteStepCounter(stepId);
		return MessageSupport.successMsg("删除成功");
	}
	
	@RequestMapping("query")
	public Object queryStepCounter(XqsightPage xqsightPage) {
		Page<?> page = XqsightPageHelper.startPageWithPageIndex(xqsightPage.getiDisplayStart(), xqsightPage.getiDisplayLength());
		List<StepCounter> stepCounters = stepCounterService.queryStepCounter();
		xqsightPage.setTotalCount(page.getTotal());
		return MessageSupport.successDataTableMsg(xqsightPage, stepCounters);
	}

	@RequestMapping("querybyid")
	public Object queryStepCounter(Long stepId) {
		StepCounter stepCounter = stepCounterService.queryStepCounterById(stepId);
		return MessageSupport.successDataMsg(stepCounter, "查询成功");
	}
	
	@RequestMapping("querybyuser")
	public Object queryStepCounter(@RequestParam(required = false) Integer durDay, String id) {
		List<StepCounter> stepCounters;
		if(durDay != null){
			stepCounters = stepCounterService.queryStepCounterByUserAndDurDay(id, durDay);
		} else {
			stepCounters = stepCounterService.queryStepCounterByUser(id);
		}
		return MessageSupport.successDataMsg(stepCounters, "查询成功");
	}
}