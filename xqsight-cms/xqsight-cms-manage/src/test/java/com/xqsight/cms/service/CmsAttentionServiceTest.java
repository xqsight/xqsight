/**
 * 新启工作室
 * Copyright (c) 1994-2016 All Rights Reserved.
 */
package com.xqsight.cms.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.xqsight.cms.model.CmsAttention;

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
public class CmsAttentionServiceTest {

	@Autowired
	private CmsAttentionService cmsAttentionService;
	
	public void Test(){
		System.out.println("----------------新增----------------");
		CmsAttention cmsAttention = new CmsAttention();
		cmsAttention.setCreateOprId("1");
		cmsAttention.setCreateTime(new Date());
		cmsAttention.setAssocicationId(2L);
		cmsAttention.setAttentionType(1);//收藏
		cmsAttentionService.saveCmsAttention(cmsAttention);
		System.out.println(cmsAttention.getAttentionId());

		cmsAttention.setAttentionType(2);//顶
		cmsAttentionService.saveCmsAttention(cmsAttention);
		System.out.println("----------------修改----------------");
		cmsAttention.setUpdateTime(new Date());
		cmsAttention.setUpdOprId("1");
		cmsAttentionService.updateCmsAttention(cmsAttention);

	}
}
