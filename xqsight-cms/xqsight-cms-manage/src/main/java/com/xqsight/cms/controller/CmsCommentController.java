/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.cms.controller;

import com.github.pagehelper.Page;
import com.xqsight.cms.model.CmsComment;
import com.xqsight.cms.service.CmsCommentService;
import com.xqsight.common.model.XqsightPage;
import com.xqsight.common.support.MessageSupport;
import com.xqsight.common.support.XqsightPageHelper;
import com.xqsight.sso.utils.SSOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * <p>controller</p>
 * <p>Table: CMS_COMMENT - 评论表</p>
 * @since 2016-05-07 08:02:56
 */
@RestController
@RequestMapping("/cms/comment/")
public class CmsCommentController{

	@Autowired
	private CmsCommentService cmsCommentService;

	@RequestMapping("save")
	public Object saveCmsComment(CmsComment cmsComment) {
		cmsComment.setCreateUserId(SSOUtils.getCurrentUserId().toString());
		cmsCommentService.saveCmsComment(cmsComment);
		return MessageSupport.successMsg("保存成功");
	}
	
	@RequestMapping("update")
	public Object updateCmsComment(CmsComment cmsComment) {
		cmsComment.setUpdateUserId(SSOUtils.getCurrentUserId().toString());
		cmsCommentService.updateCmsComment(cmsComment);
		return MessageSupport.successMsg("修改成功");
	}
	
	@RequestMapping("delete")
	public Object deleteCmsComment(Long commentId) {
		cmsCommentService.deleteCmsComment(commentId);
		return MessageSupport.successMsg("删除成功");
	}
	
	@RequestMapping("query")
	public Object queryCmsComment(XqsightPage xqsightPage) {
		// 分页处理
		Page<?> page = XqsightPageHelper.startPageWithPageIndex(xqsightPage.getiDisplayStart(), xqsightPage.getiDisplayLength());
		List<CmsComment> cmsComments = cmsCommentService.queryCmsComment();
		xqsightPage.setTotalCount(page.getTotal());
		return MessageSupport.successDataTableMsg(xqsightPage, cmsComments);
	}

	@RequestMapping("querybyid")
	public Object queryCmsComment(Long commentId) {
		CmsComment cmsComment = cmsCommentService.queryCmsCommentByCommentId(commentId);
		return MessageSupport.successDataMsg(cmsComment, "查询成功");
	}
}