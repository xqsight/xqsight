/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.cms.service;

import java.util.List;
import java.util.Map;

import com.xqsight.cms.model.CmsComment;

/**
 * <p>接口类service</p>
 * <p>Table: CMS_COMMENT - 评论表</p>
 * @since 2016-05-07 08:00:23
 */
public interface CmsCommentService {

	/**
	 * 保存
	 *
	 * @param cmsComment
     */
	void saveCmsComment(CmsComment cmsComment);

	/**
	 *  修改
	 * @param cmsComment
     */
	void updateCmsComment(CmsComment cmsComment);

	/**
	 * 删除
	 *
	 * @param commentId
     */
	void deleteCmsComment(Long commentId);

	/**
	 * 删除评论根据附属主键
	 * @param associcationId
     */
	void deleteCmsCommentByAssocicationId(Long associcationId);

	/**
	 * 修改评论是否显示和隐藏  有时可表示已读和未读
	 * @param commentHit
	 * @param commentId
     */
	void updateCmsCommentHitByCommentId(int commentHit, Long commentId);

	/**
	 * 修改评论active
	 *
	 * @param active
	 * @param commentId
     */
	void updateCmsCommentActiveByCommentId(int active, Long commentId);

	/**
	 * 修改是否显示 隐藏 根据关联键
	 * @param commentHit
	 * @param associcationId
     */
	void updateCmsCommentHitByAssocicationId(int commentHit, Long associcationId);

	/**
	 *  查询评论
	 *
	 * @param commentId
	 * @return
     */
	CmsComment queryCmsCommentByCommentId(Long commentId);

	/**
	 * 查询评论
	 *
	 * @return
     */
	List<CmsComment> queryCmsComment();

	/**
	 * 根据关联查询评论
	 *
	 * @param associcationId
	 * @return
     */
	List<CmsComment> queryCmsCommentByAssocicationId(Long associcationId);

	/**
	 * 根据关联查询评论 附带user信息
	 * @param associcationId
	 * @return
     */
	List<Map<String,Object>> queryCmsCommentWithUserByAssocicationId(Long associcationId);

}