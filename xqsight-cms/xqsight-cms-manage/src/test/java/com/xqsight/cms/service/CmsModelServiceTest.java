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
import com.xqsight.cms.model.CmsModel;

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
public class CmsModelServiceTest {

	@Autowired
	private CmsModelService cmsModelService;
	
	@Test
	public void Test(){
		System.out.println("----------------新增----------------");
		CmsModel cmsModel = new CmsModel();
		cmsModel.setCreateOprId("1");
		cmsModel.setAppId(1L);
		cmsModel.setCreateTime(new Date());
		cmsModel.setModelDescription("test");
		cmsModel.setModelTitle("test");
		cmsModelService.saveCmsModel(cmsModel);
		System.out.println(cmsModel.getModelId());
		System.out.println("----------------修改----------------");
		cmsModel.setUpdateTime(new Date());
		cmsModel.setUpdOprId("1");
		cmsModel.setModelDescription("testesttesttestt");
		cmsModel.setModelTitle("test update");
		cmsModelService.updateCmsModel(cmsModel);
		System.out.println("--------------删除------------------");
		cmsModelService.deleteCmsModel(2L);
		System.out.println("--------------查询------------------");
		CmsModel cmsModel2 = cmsModelService.queryCmsModelById(cmsModel.getModelId());
		System.out.println(JSON.toJSONString(cmsModel2));
		System.out.println("--------------查询2------------------");

	}
}
