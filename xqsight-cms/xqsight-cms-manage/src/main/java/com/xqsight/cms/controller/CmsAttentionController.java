/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.cms.controller;

import com.github.pagehelper.Page;
import com.xiaoleilu.hutool.util.HtmlUtil;
import com.xqsight.cms.model.CmsAttention;
import com.xqsight.cms.service.CmsAttentionService;
import com.xqsight.common.model.XqsightPage;
import com.xqsight.common.support.MessageSupport;
import com.xqsight.common.support.XqsightPageHelper;
import com.xqsight.commons.utils.MapKeyHandle;
import com.xqsight.sso.utils.SSOUtils;
import com.xqsight.upload.model.SysFile;
import com.xqsight.upload.service.UploadService;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


/**
 * <p>controller</p>
 * <p>Table: CMS_ATTENTION - 用户收藏表</p>
 * @since 2016-05-07 08:02:51
 */
@RestController
@RequestMapping("/cms/attention/")
public class CmsAttentionController{

	@Autowired
	private CmsAttentionService cmsAttentionService;

	@Autowired
	private UploadService uploadService;

	@RequestMapping("save")
	public Object saveCmsAttention(CmsAttention cmsAttention) {
		cmsAttention.setCreateOprId(SSOUtils.getCurrentUserId().toString());
		CmsAttention attention = cmsAttentionService.queryCmsAttentionByAssocicationIdAndAttentionTypeAndCreateOprId(cmsAttention.getAssocicationId(),cmsAttention.getAttentionType(),cmsAttention.getCreateOprId());
		if(attention != null)
			return MessageSupport.failureMsg("你已经对该帖子有处理，不能重复处理");

		cmsAttentionService.saveCmsAttention(cmsAttention);
		return MessageSupport.successMsg("保存成功");
	}
	
	@RequestMapping("update")
	public Object updateCmsAttention(CmsAttention cmsAttention) {
		cmsAttention.setUpdOprId(SSOUtils.getCurrentUserId().toString());

		cmsAttentionService.updateCmsAttention(cmsAttention);
		return MessageSupport.successMsg("修改成功");
	}
	
	@RequestMapping("query")
	public Object queryCmsAttention(XqsightPage xqsightPage,Integer attentionType) {
		// 分页处理
		Page<?> page = XqsightPageHelper.startPageWithPageIndex(xqsightPage.getiDisplayStart(), xqsightPage.getiDisplayLength());
		List<CmsAttention> cmsAttentions = cmsAttentionService.queryCmsAttentionByAttentionType(attentionType);
		xqsightPage.setTotalCount(page.getTotal());
		return MessageSupport.successDataTableMsg(xqsightPage, cmsAttentions);
	}
	
	@RequestMapping("querybyuser")
	public Object queryCmsAttentionByUserId(XqsightPage xqsightPage,String createOprId,Integer attentionType) {
		Page<?> page = XqsightPageHelper.startPageWithPageIndex(xqsightPage.getiDisplayStart(), xqsightPage.getiDisplayLength());
		List<Map<String,Object>> cmsAttentions = null;
		if(attentionType == 1){//收藏
			cmsAttentions = cmsAttentionService.queryCmsAttentionToStoreByUser(createOprId);
			//循环获取帖子对应的图片
			for(Map<String, Object> map : cmsAttentions){
				List<String> imgList = HtmlUtil.pickImg(MapUtils.getString(map,"ARTICLE_CONTENT"));
				map.put("imgFile",(imgList != null && imgList.size() > 0) ? imgList.get(0) : "");
			}
		}else if(attentionType == 3){//关注
			cmsAttentions = cmsAttentionService.queryCmsAttentionToAttenionByUser(createOprId);
		}
		xqsightPage.setTotalCount(page.getTotal());
		return MessageSupport.successDataTableMsg(xqsightPage, MapKeyHandle.keyToJavaProperty(cmsAttentions));
	}

	@RequestMapping("operate")
	public Object operateCmsAttention(CmsAttention cmsAttention,String oprType){
		if(StringUtils.equalsIgnoreCase(oprType,"add")){
			CmsAttention attention = cmsAttentionService.queryCmsAttentionByAssocicationIdAndAttentionTypeAndCreateOprId(cmsAttention.getAssocicationId(),cmsAttention.getAttentionType(),cmsAttention.getCreateOprId());
			if(attention != null)
				return MessageSupport.failureMsg("你已经对该帖子有处理，不能重复处理");

			cmsAttentionService.saveCmsAttention(cmsAttention);
		}else if(StringUtils.equalsIgnoreCase(oprType,"del")){
			cmsAttentionService.deleteCmsAttentionByAssocicationIdAndAttentionTypeAndCreateOprId(cmsAttention.getAssocicationId(),cmsAttention.getAttentionType(),cmsAttention.getCreateOprId());
		}
		return MessageSupport.successMsg("处理成功");
	}

	@RequestMapping("querybyid")
	public Object queryCmsAttention(Long attentionId) {
		CmsAttention cmsAttention = cmsAttentionService.queryCmsAttentionByAttentionId(attentionId);
		return MessageSupport.successDataMsg(cmsAttention, "查询成功");
	}
}