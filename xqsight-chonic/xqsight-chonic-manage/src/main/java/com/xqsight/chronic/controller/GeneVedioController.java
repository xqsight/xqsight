/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.chronic.controller;

import com.github.pagehelper.Page;
import com.xqsight.chronic.model.GeneVedio;
import com.xqsight.chronic.service.GeneVedioService;
import com.xqsight.common.model.XqsightPage;
import com.xqsight.common.support.MessageSupport;
import com.xqsight.common.support.XqsightPageHelper;
import com.xqsight.sso.utils.SSOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * <p>基因大讲堂表 controller</p>
 * <p>Table: GENE_VEDIO - 基因大讲堂表</p>
 * @since 2016-05-13 03:29:55
 */
@RestController
@RequestMapping("/gene/vedio/")
public class GeneVedioController{

	@Autowired
	private GeneVedioService geneVedioService;

	@RequestMapping("save")
	public Object saveGeneVedio(GeneVedio geneVedio) {
		geneVedio.setCreateOprId(SSOUtils.getCurrentUserId().toString());
		geneVedioService.saveGeneVedio(geneVedio);
		return MessageSupport.successMsg("保存成功");
	}
	
	@RequestMapping("update")
	public Object updateGeneVedio(GeneVedio geneVedio) {
		geneVedio.setCreateOprId(SSOUtils.getCurrentUserId().toString());
		geneVedioService.updateGeneVedio(geneVedio);
		return MessageSupport.successMsg("修改成功");
	}
	
	@RequestMapping("delete")
	public Object deleteGeneVedio(Long vedioId) {
		geneVedioService.deleteGeneVedio(vedioId);
		return MessageSupport.successMsg("删除成功");
	}
	
	@RequestMapping("query")
	public Object queryGeneVedio(XqsightPage xqsightPage,String vedioName) {
		Page<?> page = XqsightPageHelper.startPageWithPageIndex(xqsightPage.getiDisplayStart(), xqsightPage.getiDisplayLength());
		List<GeneVedio> geneVedios = geneVedioService.queryGeneVedioByLikeName(vedioName);
		xqsightPage.setTotalCount(page.getTotal());
		return MessageSupport.successDataTableMsg(xqsightPage, geneVedios);
	}

	@RequestMapping("querybyid")
	public Object queryGeneVedio(Long vedioId) {
		GeneVedio geneVedio = geneVedioService.queryGeneVedioById(vedioId);
		return MessageSupport.successDataMsg(geneVedio, "查询成功");
	}
}