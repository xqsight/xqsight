/**
 * 新启工作室
 * Copyright (c) 1994-2016 All Rights Reserved.
 */
package com.xqsight.cms.mysqlmapper.builder;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import com.xqsight.cms.model.CmsArticle;

/**
 * @Description: TODO
 * @author wangganggang
 * @date 2016年5月12日 上午8:27:01
 *
 */
public class CmsArticleSQLBuilder {

	@SuppressWarnings("unchecked")
	public String queryCmsArticle(Map<String, Object> paramsMap){
		final Map<String, Object> paramMap = ((MapUtils.isEmpty((Map<String, Object>) paramsMap.get("paramsMap"))) ? new HashMap<String, Object>()
				: (Map<String, Object>) paramsMap.get("paramsMap"));
		String sql = new SQL(){
			{
				SELECT("cart.article_title,cart.article_id,cart.article_description,cart.create_time");
				SELECT("sl.img_url,sl.user_name,sl.login_id");
				SELECT("sf.file_name,sf.file_url,sf.file_id");
				SELECT("(select count(*) from cms_attention where attention_type=2 and assocication_id=cart.article_id)attention_count");
				SELECT("(select count(*) from cms_comment where assocication_id=cart.article_id)comment_count");
				FROM("cms_article cart ");
				LEFT_OUTER_JOIN("cms_model cmo on cart.model_id = cmo.model_id");
				LEFT_OUTER_JOIN("cms_app capp on cmo.app_id = capp.app_id");
				LEFT_OUTER_JOIN("sys_login sl on cart.create_opr_id = sl.id");
				LEFT_OUTER_JOIN("sys_file_assocication sfa on cart.article_id = sfa.assocication_id");
				LEFT_OUTER_JOIN("sys_file sf on sf.file_id = sfa.file_id");
				
				if(StringUtils.isNoneBlank(MapUtils.getString(paramMap, "createOprId")))
					WHERE("cart.create_opr_id = #{paramsMap.createOprId,jdbcType=VARCHAR}");
				
				if(StringUtils.isNoneBlank(MapUtils.getString(paramMap, "modelCode")))
					WHERE("cmo.model_code=#{paramsMap.modelCode,jdbcType=VARCHAR}");
				
				if(StringUtils.isNoneBlank(MapUtils.getString(paramMap, "appCode")))
					WHERE("capp.app_code = #{paramsMap.appCode,jdbcType=VARCHAR}");
				
				if(StringUtils.isNoneBlank(MapUtils.getString(paramMap, "orderBy")))
					ORDER_BY("${paramsMap.orderBy} ${paramsMap.sortType}");
				
				GROUP_BY("cart.article_id");
			}
		}.toString();
		return sql;
	}
	
	
	 public static void main(String[] args) {
		System.out.println(new CmsArticleSQLBuilder().queryCmsArticle(new HashMap<String, Object>()));
	}
	
	@SuppressWarnings("unchecked")
	public String queryCmsArticleByModel(Map<String, Object> paramsMap)
	{
		final Map<String, Object> paramMap = MapUtils.isEmpty((Map<String, Object>) paramsMap.get("paramsMap")) ? new HashMap<String, Object>()
				: (Map<String, Object>) paramsMap.get("paramsMap");
		String sql = new SQL(){
			{
				SELECT(" model_id,file_id,article_id,article_title,article_author,article_description,article_content,article_type,article_url,article_keyword,article_sourcle,article_hit,article_has_pic,active,create_time,create_opr_id,update_time,upd_opr_id,remark ");
				FROM ("cms_article article");
				if(paramMap.get("appId").equals("1"))
				{
					WHERE("article.model_id in (3,4,5,14)");
				}
				else if(paramMap.get("appId").equals("2"))
				{
					WHERE("article.model_id not in (3,4,5,14)");
				}
				if(!((CmsArticle) paramMap.get("cmsArticle")).getArticleTitle().equals(""))
						WHERE("article.article_title like '%"+((CmsArticle) paramMap.get("cmsArticle")).getArticleTitle()+"%'");
						
				ORDER_BY("article.article_id");
			}
		}.toString();
		return sql;
	}
}
