/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.chronic.controller;

import com.github.pagehelper.Page;
import com.xqsight.chronic.model.Fat;
import com.xqsight.chronic.service.FatService;
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
 * <p>脂肪记录表 controller</p>
 * <p>Table: FAT - 脂肪记录表</p>
 * @since 2016-05-09 07:50:53
 */
@RestController
@RequestMapping("/fat/")
public class FatController{

	@Autowired
	private FatService fatService;

	@RequestMapping("save")
	public Object saveFat(HttpServletRequest request, Fat fat) {
		if(!WebUtils.isMobile(request))
			fat.setCreateOprId(SSOUtils.getCurrentUserId().toString());
		if(fat.getBooldId() != null && fat.getBooldId() > 0) {
			fatService.updateFat(fat);
		}else{
			fatService.saveFat(fat);
		}
		return MessageSupport.successMsg("保存成功");
	}
	
	@RequestMapping("update")
	public Object updateFat(HttpServletRequest request, Fat fat) {

		if(!WebUtils.isMobile(request))
			fat.setUpdOprId(SSOUtils.getCurrentUserId().toString());

		fatService.updateFat(fat);
		return MessageSupport.successMsg("修改成功");
	}
	
	@RequestMapping("delete")
	public Object deleteFat(Long booldId) {
		fatService.deleteFat(booldId);
		return MessageSupport.successMsg("删除成功");
	}
	
	@RequestMapping("query")
	public Object queryFat(XqsightPage xqsightPage) {
		Page<?> page = XqsightPageHelper.startPageWithPageIndex(xqsightPage.getiDisplayStart(), xqsightPage.getiDisplayLength());
		List<Fat> fats = fatService.queryFat();
		xqsightPage.setTotalCount(page.getTotal());
		return MessageSupport.successDataTableMsg(xqsightPage, fats);
	}

	@RequestMapping("querybyid")
	public Object queryFat(Long booldId) {
		Fat fat = fatService.queryFatById(booldId);
		return MessageSupport.successDataMsg(fat, "查询成功");
	}
	
	@RequestMapping("querybyuser")
	public Object queryFat(@RequestParam(required = false) Integer durDay, String id) {
		List<Fat> fats;
		if(durDay != null){
			fats = fatService.queryFatByUserAndDurDay(id, durDay);
		} else {
			fats = fatService.queryFatByUser(id);
		}
		return MessageSupport.successDataMsg(fats, "查询成功");
	}
}