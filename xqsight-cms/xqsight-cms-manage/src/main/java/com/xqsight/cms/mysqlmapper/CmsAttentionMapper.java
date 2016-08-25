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

import com.xqsight.cms.model.CmsAttention;

/**
 * <p>数据库Mapper类</p>
 * <p>用户收藏表</p>
 * @since 2016-05-07 07:49:38
 */
public interface CmsAttentionMapper {

	@Insert(" INSERT INTO CMS_ATTENTION(ASSOCICATION_ID,ATTENTION_TYPE,ACTIVE,CREATE_TIME,CREATE_OPR_ID,REMARK)VALUES(#{associcationId,jdbcType=NUMERIC},#{attentionType,jdbcType=NUMERIC},#{active,jdbcType=NUMERIC},#{createTime,jdbcType=TIMESTAMP},#{createOprId,jdbcType=VARCHAR},#{remark,jdbcType=VARCHAR})")
	@Options(useGeneratedKeys = true, keyProperty = "attentionId")
	void saveCmsAttention(CmsAttention cmsAttention);
	
	@Update(" UPDATE CMS_ATTENTION SET ASSOCICATION_ID=#{associcationId,jdbcType=NUMERIC},ATTENTION_TYPE=#{attentionType,jdbcType=NUMERIC},ACTIVE=#{active,jdbcType=NUMERIC},UPDATE_TIME=#{updateTime,jdbcType=TIMESTAMP},UPD_OPR_ID=#{updOprId,jdbcType=VARCHAR},REMARK=#{remark,jdbcType=VARCHAR} WHERE ATTENTION_ID=#{attentionId,jdbcType=NUMERIC}")
	void updateCmsAttention(CmsAttention cmsAttention);

	@Delete(" DELETE FROM CMS_ATTENTION WHERE ASSOCICATION_ID=#{associcationId,jdbcType=NUMERIC} AND ATTENTION_TYPE=#{attentionType,jdbcType=NUMERIC} AND CREATE_OPR_ID=#{createOprId,jdbcType=VARCHAR}")
	void deleteCmsAttentionByAssocicationIdAndAttentionTypeAndCreateOprId(@Param("associcationId") Long associcationId, @Param("attentionType") int attentionType, @Param("createOprId") String createOprId);

	@Delete(" DELETE FROM CMS_ATTENTION WHERE ASSOCICATION_ID=#{associcationId,jdbcType=NUMERIC} AND ATTENTION_TYPE=#{attentionType,jdbcType=NUMERIC}")
	void deleteCmsAttentionByAssocicationIdAndAttentionType(@Param("associcationId") Long associcationId, @Param("attentionType") int attentionType);

	@Select(" SELECT ATTENTION_ID,ASSOCICATION_ID,ATTENTION_TYPE,ACTIVE,CREATE_TIME,CREATE_OPR_ID,UPDATE_TIME,UPD_OPR_ID,REMARK FROM CMS_ATTENTION WHERE ATTENTION_ID=#{attentionId,jdbcType=NUMERIC}")
	CmsAttention queryCmsAttentionByAttentionId(@Param("attentionId") Long attentionId);

	@Select(" SELECT ATTENTION_ID,ASSOCICATION_ID,ATTENTION_TYPE,ACTIVE,CREATE_TIME,CREATE_OPR_ID,UPDATE_TIME,UPD_OPR_ID,REMARK FROM CMS_ATTENTION WHERE ASSOCICATION_ID=#{associcationId,jdbcType=NUMERIC} AND ATTENTION_TYPE=#{attentionType,jdbcType=NUMERIC} AND CREATE_OPR_ID=#{createOprId,jdbcType=VARCHAR} ORDER BY CREATE_TIME DESC")
	CmsAttention queryCmsAttentionByAssocicationIdAndAttentionTypeAndCreateOprId(@Param("associcationId") Long associcationId, @Param("attentionType") int attentionType, @Param("createOprId") String createOprId);

	@Select(" SELECT ATTENTION_ID,ASSOCICATION_ID,ATTENTION_TYPE,ACTIVE,CREATE_TIME,CREATE_OPR_ID,UPDATE_TIME,UPD_OPR_ID,REMARK FROM CMS_ATTENTION WHERE ATTENTION_TYPE=#{attentionType,jdbcType=NUMERIC} ORDER BY CREATE_TIME DESC")
	List<CmsAttention> queryCmsAttentionByAttentionType(@Param("attentionType") int attentionType);

	@Select(" SELECT VAR.`*`,CAT.ATTENTION_ID,CAT.ASSOCICATION_ID,CAT.ATTENTION_TYPE FROM CMS_ATTENTION CAT LEFT JOIN VIEW_ARTICLE VAR ON CAT.ASSOCICATION_ID =VAR.ARTICLE_ID  WHERE CAT.ATTENTION_TYPE=1 AND CAT.CREATE_OPR_ID = #{createOprId,jdbcType=VARCHAR} ORDER BY CAT.CREATE_TIME DESC")
	List<Map<String, Object>> queryCmsAttentionToStoreByUser(@Param("createOprId") String createOprId);

	@Select(" SELECT SL.USER_NAME,SL.IMG_URL,SL.LOGIN_ID,CAT.ATTENTION_ID,CAT.ASSOCICATION_ID,CAT.ATTENTION_TYPE FROM CMS_ATTENTION CAT LEFT JOIN SYS_LOGIN SL ON CAT.ASSOCICATION_ID = SL.Id WHERE CAT.ATTENTION_TYPE=3 AND CAT.CREATE_OPR_ID = #{createOprId,jdbcType=VARCHAR} ORDER BY CAT.CREATE_TIME DESC")
	List<Map<String, Object>> queryCmsAttentionToAttenionByUser(@Param("createOprId") String createOprId);
}