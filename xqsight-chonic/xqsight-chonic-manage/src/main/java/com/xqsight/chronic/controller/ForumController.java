/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.chronic.controller;

import com.github.pagehelper.Page;
import com.xiaoleilu.hutool.util.StrUtil;
import com.xqsight.chronic.service.ForumService;
import com.xqsight.cms.model.CmsArticle;
import com.xqsight.cms.model.CmsComment;
import com.xqsight.cms.service.CmsArticleService;
import com.xqsight.cms.service.CmsCommentService;
import com.xqsight.common.enums.ModelCodeEnums;
import com.xqsight.common.model.XqsightPage;
import com.xqsight.common.support.MessageSupport;
import com.xqsight.common.support.XqsightPageHelper;
import com.xqsight.commons.utils.MapKeyHandle;
import com.xqsight.commons.web.WebUtils;
import com.xqsight.sso.utils.SSOUtils;
import com.xqsight.upload.model.SysFile;
import com.xqsight.upload.service.UploadService;
import com.xqsight.upload.support.FileUploadSupport;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * <p>  controller  </p>
 * <p> Table: CMS_ARTICLE - 社区 </p>
 * 
 * @since 2016-05-07 08:02:43
 */
@RestController
@RequestMapping("/forum/")
public class ForumController{

	private static Logger logger = LogManager.getLogger(ForumController.class);

	@Autowired
	private ForumService forumService;

	@Autowired
	private CmsArticleService cmsArticleService;

	@Autowired
	private CmsCommentService cmsCommentService;

	@Autowired
	private UploadService uploadService;

	@RequestMapping("save")
	public Object saveCmsArticle(HttpServletRequest request, CmsArticle cmsArticle) {
		try {
			StringBuffer sbFileId = new StringBuffer();
			/** 处理文件 **/
			if(ServletFileUpload.isMultipartContent(request)){
				MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
				Map<String, MultipartFile> multipartFileMap = mRequest.getFileMap();
				for(String mapKey : multipartFileMap.keySet()){
					MultipartFile multipartFile = multipartFileMap.get(mapKey);
					SysFile sysFile = FileUploadSupport.uploadFile(multipartFile);
					uploadService.saveSysFile(sysFile);
					sbFileId.append(sysFile.getFileId()).append(",");
				}
			}

			/** 描述为空，获取前20个字符 **/
			if (StringUtils.isBlank(cmsArticle.getArticleDescription()))
				cmsArticle.setArticleDescription(StrUtil.pickPrefixContent(cmsArticle.getArticleContent(),20));

			cmsArticle.setModelId(Long.valueOf(ModelCodeEnums.getEnum(cmsArticle.getModelCode()).getModelId()));
			cmsArticle.setFileId(sbFileId.toString());

			if(!WebUtils.isMobile(request))
				cmsArticle.setCreateOprId(SSOUtils.getCurrentUserId().toString());

			forumService.saveForum(cmsArticle);
			return MessageSupport.successMsg("保存成功!");
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			return MessageSupport.failureMsg("保存失败!");
		}
	}

	@RequestMapping("savecomment")
	public Object saveCmsComment(HttpServletRequest request, CmsComment cmsComment) {
		if(!WebUtils.isMobile(request))
			cmsComment.setCreateOprId(SSOUtils.getCurrentUserId().toString());

		cmsCommentService.saveCmsComment(cmsComment);
		return MessageSupport.successMsg("保存成功");
	}

	
	@RequestMapping("delete")
	public Object deleteCmsArticle(Long articleId) {
		cmsArticleService.deleteCmsArticle(articleId);
		return MessageSupport.successMsg("删除成功");
	}

	@RequestMapping("query")
	public Object queryCmsArticle(XqsightPage xqsightPage,String queryType,String modelCode) {

		Long modelId = Long.valueOf(ModelCodeEnums.getEnum(modelCode).getModelId());

		String orderType = "VAR.CREATE_TIME";

		/** 热门 **/
		if(StringUtils.equalsIgnoreCase(queryType,"TOP")){
			orderType = "VAR.TOP_COUNT";
		}
		/** 好评 **/
		else if(StringUtils.equalsIgnoreCase(queryType,"GOOD")){
			orderType = "VAR.COMMENT_COUNT";
		}

		Page<?> page = XqsightPageHelper.startPageWithPageIndex(xqsightPage.getiDisplayStart(), xqsightPage.getiDisplayLength());
		List<Map<String, Object>> dataList = cmsArticleService.queryCmsArticleViewWithFirstPicByModelIdAndOrderBy(modelId,orderType);

		/** 循环获取帖子对应的图片 **/
		for(Map<String, Object> map : dataList){
			if(StringUtils.isBlank(MapUtils.getString(map,"FILE_ID")))
				continue;

			List<SysFile> sysFiles = uploadService.queryFileByFileId(MapUtils.getString(map,"FILE_ID"));
			map.put("sysFiles",sysFiles);
		}

		xqsightPage.setTotalCount(page.getTotal());
		return MessageSupport.successDataTableMsg(xqsightPage, MapKeyHandle.keyToJavaProperty(dataList));
	}

	@RequestMapping("querybyid")
	public Object queryCmsArticleById(Long articleId) {
		Map<String,Object> cmsArticleView = cmsArticleService.queryCmsArticleViewByArticleId(articleId);
		List<SysFile> sysFiles = uploadService.queryFileByFileId(MapUtils.getString(cmsArticleView,"FILE_ID"));
		cmsArticleView.put("sysFiles",sysFiles);
		return MessageSupport.successDataMsg(MapKeyHandle.keyToJavaProperty(cmsArticleView), "查询成功");
	}

	@RequestMapping("querycommentbyarticleid")
	public Object queryCmsCommentByArticleId(XqsightPage xqsightPage,Long articleId) {
		Page<?> page = XqsightPageHelper.startPageWithPageIndex(xqsightPage.getiDisplayStart(), xqsightPage.getiDisplayLength());
		List<Map<String, Object>> dataList = cmsCommentService.queryCmsCommentWithUserByAssocicationId(articleId);
		xqsightPage.setTotalCount(page.getTotal());
		return MessageSupport.successDataTableMsg(xqsightPage, MapKeyHandle.keyToJavaProperty(dataList));
	}

	@RequestMapping("querybyuser")
	public Object queryCmsArticleByUser(XqsightPage xqsightPage,String modelCode,String createOprId) {
		int modelId = ModelCodeEnums.getEnum(modelCode).getModelId();

		Page<?> page = XqsightPageHelper.startPageWithPageIndex(xqsightPage.getiDisplayStart(),xqsightPage.getiDisplayLength());

		List<Map<String, Object>> dataList = cmsArticleService.queryCmsArticleViewByModelIdAndCreateOprId(modelId,createOprId);
		/** 循环获取帖子对应的图片 **/
		for(Map<String, Object> map : dataList){
			if(StringUtils.isBlank(MapUtils.getString(map,"FILE_ID")))
				continue;

			List<SysFile> sysFiles = uploadService.queryFileByFileId(MapUtils.getString(map,"FILE_ID"));
			map.put("sysFiles",sysFiles);
		}
		xqsightPage.setTotalCount(page.getTotal());
		return MessageSupport.successDataTableMsg(xqsightPage, MapKeyHandle.keyToJavaProperty(dataList));
	}
}