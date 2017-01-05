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

import com.xqsight.cms.model.CmsAttention;

/**
 * <p>数据库Mapper类</p>
 * <p>用户收藏表</p>
 * @since 2016-05-07 07:49:38
 */
public interface CmsAttentionMapper {

	@Insert(" insert into cms_attention(assocication_id,attention_type,active,create_time,create_user_id,remark)values(#{associcationId,jdbcType=NUMERIC},#{attentionType,jdbcType=NUMERIC},#{active,jdbcType=NUMERIC},#{createTime,jdbcType=TIMESTAMP},#{createUserId,jdbcType=VARCHAR},#{remark,jdbcType=VARCHAR})")
	@Options(useGeneratedKeys = true, keyProperty = "attentionId")
	void saveCmsAttention(CmsAttention cmsAttention);
	
	@Update(" update cms_attention set assocication_id=#{associcationId,jdbcType=NUMERIC},attention_type=#{attentionType,jdbcType=NUMERIC},active=#{active,jdbcType=NUMERIC},update_time=#{updateTime,jdbcType=TIMESTAMP},update_user_id=#{updateUserId,jdbcType=VARCHAR},remark=#{remark,jdbcType=VARCHAR} where attention_id=#{attentionId,jdbcType=NUMERIC}")
	void updateCmsAttention(CmsAttention cmsAttention);

	@Delete(" delete from cms_attention where assocication_id=#{associcationId,jdbcType=NUMERIC} AND attention_type=#{attentionType,jdbcType=NUMERIC} AND create_user_id=#{createUserId,jdbcType=VARCHAR}")
	void deleteCmsAttentionByAssocicationIdAndAttentionTypeAndCreateOprId(@Param("associcationId") Long associcationId, @Param("attentionType") int attentionType, @Param("createUserId") String createUserId);

	@Delete(" delete from cms_attention where assocication_id=#{associcationId,jdbcType=NUMERIC} AND attention_type=#{attentionType,jdbcType=NUMERIC}")
	void deleteCmsAttentionByAssocicationIdAndAttentionType(@Param("associcationId") Long associcationId, @Param("attentionType") int attentionType);

	@Select(" select attention_id,assocication_id,attention_type,active,create_time,create_user_id,update_time,update_user_id,remark from cms_attention where attention_id=#{attentionId,jdbcType=NUMERIC}")
	CmsAttention queryCmsAttentionByAttentionId(@Param("attentionId") Long attentionId);

	@Select(" select attention_id,assocication_id,attention_type,active,create_time,create_user_id,update_time,update_user_id,remark from cms_attention where assocication_id=#{associcationId,jdbcType=NUMERIC} AND attention_type=#{attentionType,jdbcType=NUMERIC} AND create_user_id=#{createUserId,jdbcType=VARCHAR} ORDER BY create_time DESC")
	CmsAttention queryCmsAttentionByAssocicationIdAndAttentionTypeAndCreateOprId(@Param("associcationId") Long associcationId, @Param("attentionType") int attentionType, @Param("createUserId") String createUserId);

	@Select(" select attention_id,assocication_id,attention_type,active,create_time,create_user_id,update_time,update_user_id,remark from cms_attention where attention_type=#{attentionType,jdbcType=NUMERIC} order by create_time desc")
	List<CmsAttention> queryCmsAttentionByAttentionType(@Param("attentionType") int attentionType);

	@Select(" select var.`*`,cat.attention_id,cat.assocication_id,cat.attention_type from cms_attention cat left join view_article var on cat.assocication_id =var.article_id  where cat.attention_type=1 and cat.create_user_id = #{createUserId,jdbcType=VARCHAR} order by cat.create_time desc")
	List<Map<String, Object>> queryCmsAttentionToStoreByUser(@Param("createUserId") String createUserId);

	@Select(" select sl.user_name,sl.img_url,sl.login_id,cat.attention_id,cat.assocication_id,cat.attention_type from cms_attention cat left join sys_login sl on cat.assocication_id = sl.id where cat.attention_type=3 and cat.create_user_id = #{createUserId,jdbcType=VARCHAR} order by cat.create_time desc")
	List<Map<String, Object>> queryCmsAttentionToAttenionByUser(@Param("createUserId") String createUserId);
}