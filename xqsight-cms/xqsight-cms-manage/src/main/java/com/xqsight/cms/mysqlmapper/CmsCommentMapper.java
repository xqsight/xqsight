/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 package com.xqsight.cms.mysqlmapper;

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

	@Insert(" INSERT INTO CMS_COMMENT(ASSOCICATION_ID,COMMENT,COMMENT_HAS_PIC,COMMENT_HIT,COMMENT_POINTS,ACTIVE,CREATE_TIME,CREATE_OPR_ID,UPDATE_TIME,UPD_OPR_ID,REMARK)VALUES(#{associcationId,jdbcType=NUMERIC},#{comment,jdbcType=VARCHAR},#{commentHasPic,jdbcType=NUMERIC},#{commentHit,jdbcType=NUMERIC},#{commentPoints,jdbcType=NUMERIC},#{active,jdbcType=NUMERIC},#{createTime,jdbcType=TIMESTAMP},#{createOprId,jdbcType=VARCHAR},#{updateTime,jdbcType=TIMESTAMP},#{updOprId,jdbcType=VARCHAR},#{remark,jdbcType=VARCHAR})")
	@Options(useGeneratedKeys = true, keyProperty = "commentId")
	void saveCmsComment(CmsComment cmsComment);
	
	@Update(" UPDATE CMS_COMMENT SET ASSOCICATION_ID=#{associcationId,jdbcType=NUMERIC},COMMENT=#{comment,jdbcType=VARCHAR},COMMENT_HAS_PIC=#{commentHasPic,jdbcType=NUMERIC},COMMENT_HIT=#{commentHit,jdbcType=NUMERIC},COMMENT_POINTS=#{commentPoints,jdbcType=NUMERIC},ACTIVE=#{active,jdbcType=NUMERIC},UPDATE_TIME=#{updateTime,jdbcType=TIMESTAMP},UPD_OPR_ID=#{updOprId,jdbcType=VARCHAR},REMARK=#{remark,jdbcType=VARCHAR} WHERE COMMENT_ID=#{commentId,jdbcType=NUMERIC}")
	void updateCmsComment(CmsComment cmsComment);
	
	@Delete(" DELETE FROM CMS_COMMENT WHERE COMMENT_ID=#{commentId,jdbcType=NUMERIC}")
	void deleteCmsComment(@Param("commentId") Long commentId);

	@Select(" SELECT COMMENT_ID,ASSOCICATION_ID,COMMENT,COMMENT_HAS_PIC,COMMENT_HIT,COMMENT_POINTS,ACTIVE,CREATE_TIME,CREATE_OPR_ID,UPDATE_TIME,UPD_OPR_ID,REMARK FROM CMS_COMMENT ORDER BY CREATE_TIME DESC")
	List<CmsComment> queryCmsComment();

	@Delete(" DELETE FROM CMS_COMMENT WHERE ASSOCICATION_ID=#{associcationId,jdbcType=NUMERIC}")
	void deleteCmsCommentByAssocicationId(@Param("associcationId") Long associcationId);

	@Select(" SELECT COMMENT_ID,ASSOCICATION_ID,COMMENT,COMMENT_HAS_PIC,COMMENT_HIT,COMMENT_POINTS,ACTIVE,CREATE_TIME,CREATE_OPR_ID,UPDATE_TIME,UPD_OPR_ID,REMARK FROM CMS_COMMENT WHERE COMMENT_ID=#{commentId,jdbcType=NUMERIC}")
	CmsComment queryCmsCommentByCommentId(@Param("commentId") Long commentId);
	
	@Select(" SELECT COMMENT_ID,ASSOCICATION_ID,COMMENT,COMMENT_HAS_PIC,COMMENT_HIT,COMMENT_POINTS,ACTIVE,CREATE_TIME,CREATE_OPR_ID,UPDATE_TIME,UPD_OPR_ID,REMARK FROM CMS_COMMENT WHERE ASSOCICATION_ID = #{associcationId,jdbcType=NUMERIC} ORDER BY CREATE_TIME DESC")
	List<CmsComment> queryCmsCommentByAssocicationId(@Param("associcationId") Long associcationId);

	@Select(" SELECT CCO.COMMENT_ID,CCO.COMMENT,CCO.ASSOCICATION_ID,CCO.COMMENT_HIT,CCO.CREATE_TIME,SLO.ID,SLO.IMG_URL,SLO.USER_NAME,SLO.LOGIN_ID,SLO.AGE,SLO.SEX FROM CMS_COMMENT CCO LEFT JOIN SYS_LOGIN SLO ON CCO.CREATE_OPR_ID = SLO.ID WHERE CCO.ASSOCICATION_ID = #{associcationId,jdbcType=NUMERIC} ORDER BY CCO.CREATE_TIME DESC")
	List<Map<String,Object>> queryCmsCommentWithUserByAssocicationId(@Param("associcationId") Long associcationId);

	@Update(" UPDATE CMS_COMMENT SET COMMENT_HIT = #{commentHit,jdbcType=NUMERIC} WHERE COMMENT_ID=#{commentId,jdbcType=NUMERIC}")
	void updateCmsCommentHitByCommentId(@Param("commentHit") int commentHit, @Param("commentId") Long commentId);

	@Update(" UPDATE CMS_COMMENT SET ACTIVE = #{active,jdbcType=NUMERIC} WHERE COMMENT_ID=#{commentId,jdbcType=NUMERIC}")
	void updateCmsCommentActiveByCommentId(@Param("active") int active, @Param("commentId") Long commentId);

	@Update(" UPDATE CMS_COMMENT SET COMMENT_HIT = #{commentHit,jdbcType=NUMERIC} WHERE ASSOCICATION_ID=#{associcationId,jdbcType=NUMERIC}")
	void updateCmsCommentHitByAssocicationId(@Param("commentHit") int commentHit, @Param("associcationId") Long associcationId);
}