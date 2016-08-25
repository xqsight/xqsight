/**
 * 新启工作室
 * Copyright (c) 1994-2016 All Rights Reserved.
 */
package com.xqsight.cms.service;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.xqsight.cms.model.CmsArticle;

/**
 * @Description: TODO
 * @author wangganggang
 * @date 2016年5月10日 上午9:45:20
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
		"classpath:config/global-config.xml" ,
		"classpath:spring/webmvc-config.xml" })
public class CmsArticleServiceTest {

	@Autowired
	private CmsArticleService cmsArticleService;
	
	
	@Test
	public void Test(){
		System.out.println("----------------新增----------------");
		CmsArticle cmsArticle = new CmsArticle();
		cmsArticle.setArticleId(1L);
		System.out.println("--------------查询------------------");
		CmsArticle cmsArticle2 = cmsArticleService.queryCmsArticleById(cmsArticle.getArticleId());
		System.out.println(JSON.toJSONString(cmsArticle2));
		System.out.println("--------------查询2------------------");
		List<CmsArticle> cmsArticles = cmsArticleService.queryCmsArticle();
		System.out.println(JSON.toJSONString(cmsArticles));
	}
}
