/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.cms.controller;

import com.github.pagehelper.Page;
import com.xqsight.cms.enums.ModelCodeEnums;
import com.xqsight.cms.model.CmsArticle;
import com.xqsight.cms.service.CmsArticleService;
import com.xqsight.cms.utils.HtmlUtil;
import com.xqsight.cms.utils.StrUtil;
import com.xqsight.common.model.XqsightPage;
import com.xqsight.commons.support.MessageSupport;
import com.xqsight.common.support.XqsightPageHelper;
import com.xqsight.sso.utils.SSOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * controller
 * </p>
 * <p>
 * Table: CMS_ARTICLE - 文章表
 * </p>
 * 
 * @since 2016-05-07 08:02:43
 */
@RestController
@RequestMapping("/cms/article/")
public class CmsArticleController{

	@Autowired
	private CmsArticleService cmsArticleService;
	
	@RequestMapping("save")
	public Object saveCmsArticle(CmsArticle cmsArticle,String img) {
		cmsArticle.setCreateUserId(SSOUtils.getCurrentUserId().toString());

		/** 描述为空，获取前20个字符 **/
		if (StringUtils.isBlank(cmsArticle.getArticleDescription()))
			cmsArticle.setArticleDescription(StrUtil.pickPrefixContent(cmsArticle.getArticleContent(),20));

		cmsArticleService.saveCmsArticle(cmsArticle,img);
		return MessageSupport.successMsg("保存成功");
	}

	@RequestMapping("update")
	public Object updateCmsArticle(CmsArticle cmsArticle,String img) {
		cmsArticle.setUpdateUserId(SSOUtils.getCurrentUserId().toString());

		/** 描述为空，获取前20个字符 **/
		if (StringUtils.isBlank(cmsArticle.getArticleDescription()))
			cmsArticle.setArticleDescription(StrUtil.pickPrefixContent(cmsArticle.getArticleContent(),20));

		cmsArticleService.updateCmsArticle(cmsArticle,img);
		return MessageSupport.successMsg("修改成功");
	}

	@RequestMapping("delete")
	public Object deleteCmsArticle(Long articleId) {
		cmsArticleService.deleteCmsArticle(articleId);
		return MessageSupport.successMsg("删除成功");
	}

	@RequestMapping("query")
	public Object queryCmsArticle(XqsightPage xqsightPage, String appId,Long modelId) {
		Page<?> page = XqsightPageHelper.startPageWithPageIndex(xqsightPage.getiDisplayStart(), xqsightPage.getiDisplayLength());
		if(modelId != null && modelId > 0){
			List<CmsArticle> cmsArticles = cmsArticleService.queryCmsArticleByModelId(modelId);
			xqsightPage.setTotalCount(page.getTotal());
			return MessageSupport.successDataTableMsg(xqsightPage, cmsArticles);
		}

		String[] modelIds;
		if(StringUtils.equals(appId,"1")){
			modelIds = StrUtil.split("14,16,2",",");
		}else{
			modelIds = StrUtil.split("15,17,6,7,8,9,10,11,12,13",",");
		}

		List<CmsArticle> cmsArticles = cmsArticleService.queryCmsArticleByModelIds(modelIds);
		xqsightPage.setTotalCount(page.getTotal());
		return MessageSupport.successDataTableMsg(xqsightPage, cmsArticles);
	}

	@RequestMapping("querybymodecode")
	public Object queryCmsArticleForMobile(XqsightPage xqsightPage,String modelCode) {
		// 分页处理
		Page<?> page = XqsightPageHelper.startPageWithPageIndex(xqsightPage.getiDisplayStart(), xqsightPage.getiDisplayLength());
		List<CmsArticle> cmsArticles;
		if(StringUtils.equalsIgnoreCase(modelCode,"BEST_NEW")){//推荐
			String[] modelIds = StrUtil.split("15,6,7,8,9,10,11,12,13",",");
			cmsArticles = cmsArticleService.queryCmsArticleByModelIds(modelIds);
		}else{
			int modelId = ModelCodeEnums.getEnum(modelCode).getModelId();
			cmsArticles = cmsArticleService.queryCmsArticleByModelId(Long.valueOf(modelId));
		}

		List<Map<String,Object>> dataList = new ArrayList<>();
		for(CmsArticle cmsArticle : cmsArticles){
			Map<String,Object> dataMap = new HashMap<>();
			dataMap.put("articleId",cmsArticle.getArticleId());
			dataMap.put("createTime",cmsArticle.getCreateTime());
			dataMap.put("articleTile",cmsArticle.getArticleTitle());
			List<String> imgList = HtmlUtil.pickImg(cmsArticle.getArticleContent());
			dataMap.put("imgUrl",(imgList != null && imgList.size() > 0) ? imgList.get(0) : "");
			dataMap.put("articleDescription",cmsArticle.getArticleDescription());
			dataList.add(dataMap);
		}

		xqsightPage.setTotalCount(page.getTotal());
		return MessageSupport.successDataTableMsg(xqsightPage, dataList);
	}

	@RequestMapping("querybyid")
	public Object queryCmsArticle(Long articleId) {
		CmsArticle cmsArticle = cmsArticleService.queryCmsArticleById(articleId);
		//提取文档中的img
		List<String> imgList = HtmlUtil.pickImg(cmsArticle.getArticleContent());
		Map<String,Object> dataMap = new HashMap<>();
		dataMap.put("cmsArticle",cmsArticle);
		dataMap.put("img",imgList);
		return MessageSupport.successDataMsg(dataMap, "查询成功");
	}

	@RequestMapping("querybyarticleid")
	public Object queryCmsArticleForMobiel(Long articleId) {
		CmsArticle cmsArticle = cmsArticleService.queryCmsArticleById(articleId);
		if(cmsArticle == null)
			return MessageSupport.failureMsg("当前查询的文章不存在");

		Map<String,Object> dataMap = new HashMap<>();
		dataMap.put("articleId",cmsArticle.getArticleId());
		dataMap.put("createTime",cmsArticle.getCreateTime());
		dataMap.put("articleTile",cmsArticle.getArticleTitle());
		dataMap.put("articleContent",HtmlUtil.removeImg(cmsArticle.getArticleContent()));
		List<String> imgList = HtmlUtil.pickImg(cmsArticle.getArticleContent());
		dataMap.put("images",imgList);
		dataMap.put("articleDescription",cmsArticle.getArticleDescription());
		return MessageSupport.successDataMsg(dataMap, "查询成功");
	}

}