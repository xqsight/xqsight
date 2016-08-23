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
				SELECT("cart.ARTICLE_TITLE,cart.ARTICLE_ID,cart.ARTICLE_DESCRIPTION,cart.CREATE_TIME");
				SELECT("sl.IMG_URL,sl.USER_NAME,sl.LOGIN_ID");
				SELECT("sf.FILE_NAME,sf.FILE_URL,sf.FILE_ID");
				SELECT("(SELECT COUNT(*) FROM CMS_ATTENTION WHERE ATTENTION_TYPE=2 and ASSOCICATION_ID=cart.ARTICLE_ID)ATTENTION_COUNT");
				SELECT("(SELECT COUNT(*) FROM CMS_COMMENT WHERE ASSOCICATION_ID=cart.ARTICLE_ID)COMMENT_COUNT");
				FROM("CMS_ARTICLE cart ");
				LEFT_OUTER_JOIN("CMS_MODEL cmo ON cart.MODEL_ID = cmo.MODEL_ID");
				LEFT_OUTER_JOIN("CMS_APP capp ON cmo.APP_ID = capp.APP_ID");
				LEFT_OUTER_JOIN("SYS_LOGIN sl ON cart.CREATE_OPR_ID = sl.ID");
				LEFT_OUTER_JOIN("SYS_FILE_ASSOCICATION sfa ON cart.ARTICLE_ID = sfa.ASSOCICATION_ID");
				LEFT_OUTER_JOIN("SYS_FILE sf ON sf.FILE_ID = sfa.FILE_ID");
				
				if(StringUtils.isNoneBlank(MapUtils.getString(paramMap, "createOprId")))
					WHERE("cart.CREATE_OPR_ID = #{paramsMap.createOprId,jdbcType=VARCHAR}");
				
				if(StringUtils.isNoneBlank(MapUtils.getString(paramMap, "modelCode")))
					WHERE("cmo.MODEL_CODE=#{paramsMap.modelCode,jdbcType=VARCHAR}");
				
				if(StringUtils.isNoneBlank(MapUtils.getString(paramMap, "appCode")))
					WHERE("capp.APP_CODE = #{paramsMap.appCode,jdbcType=VARCHAR}");
				
				if(StringUtils.isNoneBlank(MapUtils.getString(paramMap, "orderBy")))
					ORDER_BY("${paramsMap.orderBy} ${paramsMap.sortType}");
				
				GROUP_BY("cart.ARTICLE_ID");
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
				SELECT(" MODEL_ID,FILE_ID,ARTICLE_ID,ARTICLE_TITLE,ARTICLE_AUTHOR,ARTICLE_DESCRIPTION,ARTICLE_CONTENT,ARTICLE_TYPE,ARTICLE_URL,ARTICLE_KEYWORD,ARTICLE_SOURCLE,ARTICLE_HIT,ARTICLE_HAS_PIC,ACTIVE,CREATE_TIME,CREATE_OPR_ID,UPDATE_TIME,UPD_OPR_ID,REMARK ");
				FROM ("CMS_ARTICLE article");
				if(paramMap.get("appId").equals("1"))
				{
					WHERE("article.MODEL_ID in (3,4,5,14)");
				}
				else if(paramMap.get("appId").equals("2"))
				{
					WHERE("article.MODEL_ID not in (3,4,5,14)");
				}
				if(!((CmsArticle) paramMap.get("cmsArticle")).getArticleTitle().equals(""))
						WHERE("article.ARTICLE_TITLE like '%"+((CmsArticle) paramMap.get("cmsArticle")).getArticleTitle()+"%'");
						
				ORDER_BY("article.ARTICLE_ID");
			}
		}.toString();
		return sql;
	}
}
