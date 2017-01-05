/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.cms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xqsight.cms.model.CmsComment;

/**
 * <p>数据库Mapper类</p>
 * <p>评论表</p>
 * @since 2016-05-07 08:00:23
 */
public interface CmsCommentMapper {

	@Insert(" insert into cms_comment(assocication_id,comment,comment_has_pic,comment_hit,comment_points,active,create_time,create_user_id,update_time,update_user_id,remark)values(#{associcationId,jdbcType=NUMERIC},#{comment,jdbcType=VARCHAR},#{commentHasPic,jdbcType=NUMERIC},#{commentHit,jdbcType=NUMERIC},#{commentPoints,jdbcType=NUMERIC},#{active,jdbcType=NUMERIC},#{createTime,jdbcType=TIMESTAMP},#{createOprId,jdbcType=VARCHAR},#{updateTime,jdbcType=TIMESTAMP},#{updateUserId,jdbcType=VARCHAR},#{remark,jdbcType=VARCHAR})")
	@Options(useGeneratedKeys = true, keyProperty = "commentId")
	void saveCmsComment(CmsComment cmsComment);
	
	@Update(" update cms_comment set assocication_id=#{associcationId,jdbcType=NUMERIC},comment=#{comment,jdbcType=VARCHAR},comment_has_pic=#{commentHasPic,jdbcType=NUMERIC},comment_hit=#{commentHit,jdbcType=NUMERIC},comment_points=#{commentPoints,jdbcType=NUMERIC},active=#{active,jdbcType=NUMERIC},update_time=#{updateTime,jdbcType=TIMESTAMP},update_user_id=#{updateUserId,jdbcType=VARCHAR},remark=#{remark,jdbcType=VARCHAR} where comment_id=#{commentId,jdbcType=NUMERIC}")
	void updateCmsComment(CmsComment cmsComment);
	
	@Delete(" delete from cms_comment where comment_id=#{commentId,jdbcType=NUMERIC}")
	void deleteCmsComment(@Param("commentId") Long commentId);

	@Select(" select comment_id,assocication_id,comment,comment_has_pic,comment_hit,comment_points,active,create_time,create_user_id,update_time,update_user_id,remark from cms_comment order by create_time desc")
	List<CmsComment> queryCmsComment();

	@Delete(" delete from cms_comment where assocication_id=#{associcationId,jdbcType=NUMERIC}")
	void deleteCmsCommentByAssocicationId(@Param("associcationId") Long associcationId);

	@Select(" select comment_id,assocication_id,comment,comment_has_pic,comment_hit,comment_points,active,create_time,create_user_id,update_time,update_user_id,remark from cms_comment where comment_id=#{commentId,jdbcType=NUMERIC}")
	CmsComment queryCmsCommentByCommentId(@Param("commentId") Long commentId);
	
	@Select(" select comment_id,assocication_id,comment,comment_has_pic,comment_hit,comment_points,active,create_time,create_user_id,update_time,update_user_id,remark from cms_comment where assocication_id = #{associcationId,jdbcType=NUMERIC} order by create_time desc")
	List<CmsComment> queryCmsCommentByAssocicationId(@Param("associcationId") Long associcationId);

	@Select(" select cco.comment_id,cco.comment,cco.assocication_id,cco.comment_hit,cco.create_time,slo.id,slo.img_url,slo.user_name,slo.login_id,slo.age,slo.sex from cms_comment cco left join sys_login slo on cco.create_user_id = slo.id where cco.assocication_id = #{associcationId,jdbcType=NUMERIC} order by cco.create_time desc")
	List<Map<String,Object>> queryCmsCommentWithUserByAssocicationId(@Param("associcationId") Long associcationId);

	@Update(" update cms_comment set comment_hit = #{commentHit,jdbcType=NUMERIC} where comment_id=#{commentId,jdbcType=NUMERIC}")
	void updateCmsCommentHitByCommentId(@Param("commentHit") int commentHit, @Param("commentId") Long commentId);

	@Update(" update cms_comment set active = #{active,jdbcType=NUMERIC} where comment_id=#{commentId,jdbcType=NUMERIC}")
	void updateCmsCommentActiveByCommentId(@Param("active") int active, @Param("commentId") Long commentId);

	@Update(" update cms_comment set comment_hit = #{commentHit,jdbcType=NUMERIC} where assocication_id=#{associcationId,jdbcType=NUMERIC}")
	void updateCmsCommentHitByAssocicationId(@Param("commentHit") int commentHit, @Param("associcationId") Long associcationId);
}