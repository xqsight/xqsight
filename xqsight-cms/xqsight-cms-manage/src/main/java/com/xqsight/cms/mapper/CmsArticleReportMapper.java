/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
 
package com.xqsight.cms.mapper;

import java.util.List;

import com.xqsight.cms.model.vo.CmsArticleReportVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xqsight.cms.model.CmsArticleReport;

/**
 * <p>数据库Mapper类</p>
 * <p></p>
 * @since 2016-09-08 08:42:11
 */
public interface CmsArticleReportMapper {

	@Insert(" insert into cms_article_report(assocication_id,report_type,report_content,deal_status,active,create_time,create_opr_id,remark)values(#{associcationId,jdbcType=NUMERIC},#{reportType,jdbcType=NUMERIC},#{reportContent,jdbcType=VARCHAR},#{dealStatus,jdbcType=NUMERIC},#{active,jdbcType=NUMERIC},#{createTime,jdbcType=TIMESTAMP},#{createOprId,jdbcType=VARCHAR},#{remark,jdbcType=VARCHAR})")
	void saveCmsArticleReport(CmsArticleReport cmsArticleReport);
	
	@Update(" update cms_article_report set deal_status=0,update_time=#{updateTime,jdbcType=TIMESTAMP},upd_opr_id=#{updOprId,jdbcType=VARCHAR},remark=#{remark,jdbcType=VARCHAR} where report_id=#{reportId,jdbcType=NUMERIC}")
	void updateCmsArticleReport(CmsArticleReport cmsArticleReport);
	
	@Delete(" delete from cms_article_report where report_id =#{reportId,jdbcType=NUMERIC}")
	void deleteCmsArticleReport(@Param("reportId") Long reportId);
	
	@Select(" select report_id,report_content,assocication_id,report_type,deal_status,active,create_time,create_opr_id,update_time,upd_opr_id,remark from cms_article_report where report_id=#{reportId,jdbcType=NUMERIC}")
	CmsArticleReport queryCmsArticleReportById(@Param("reportId") Long reportId);

	@Select(" select car.report_id,car.report_content,car.assocication_id,car.deal_status,car.create_time,car.remark,cr.article_title,cr.article_id from cms_article_report car left join cms_article cr on car.assocication_id = cr.article_id  where car.report_content like '%${reportContent}%' order by car.create_time desc")
	List<CmsArticleReportVo> queryCmsArticleReport(@Param("reportContent") String reportContent);
}