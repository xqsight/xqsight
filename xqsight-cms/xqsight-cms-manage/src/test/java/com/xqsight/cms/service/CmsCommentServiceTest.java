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
import com.xqsight.cms.model.CmsComment;

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
public class CmsCommentServiceTest {

	@Autowired
	private CmsCommentService cmsCommentService;
	
	@Test
	public void Test(){
		System.out.println("----------------新增----------------");
		CmsComment cmsComment = new CmsComment();
		cmsComment.setCreateOprId("1");
		cmsComment.setCreateTime(new Date());
		cmsComment.setAssocicationId(1L);
		cmsComment.setComment("test");
		cmsCommentService.saveCmsComment(cmsComment);
		System.out.println(cmsComment.getCommentId());
		System.out.println("----------------修改----------------");
		cmsComment.setUpdateTime(new Date());
		cmsComment.setUpdOprId("1");
		cmsComment.setAssocicationId(1L);
		cmsComment.setComment("test upd");
		cmsCommentService.updateCmsComment(cmsComment);
		System.out.println("--------------删除------------------");
		cmsCommentService.deleteCmsComment(2L);

	}
}
