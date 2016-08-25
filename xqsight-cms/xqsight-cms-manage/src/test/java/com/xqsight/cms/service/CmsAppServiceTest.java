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
import com.xqsight.cms.model.CmsApp;

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
public class CmsAppServiceTest {

	@Autowired
	private CmsAppService cmsAppService;
	
	@Test
	public void Test(){
		System.out.println("----------------新增----------------");
		CmsApp cmsApp = new CmsApp();
		cmsApp.setCreateOprId("1");
		cmsApp.setCreateTime(new Date());
		cmsApp.setAppCopyright("123");
		cmsApp.setAppDescription("test");
		cmsApp.setAppDomain("xqsight.cn");
		cmsAppService.saveCmsApp(cmsApp);
		System.out.println(cmsApp.getAppId());
		System.out.println("----------------修改----------------");
		cmsApp.setUpdateTime(new Date());
		cmsApp.setUpdOprId("1");
		cmsApp.setAppCopyright("12312312");
		cmsApp.setAppDescription("testesttesttestt");
		cmsAppService.updateCmsApp(cmsApp);
		System.out.println("--------------删除------------------");
		cmsAppService.deleteCmsApp(2L);
		System.out.println("--------------查询------------------");
		CmsApp cmsApp2 = cmsAppService.queryCmsAppById(cmsApp.getAppId());
		System.out.println(JSON.toJSONString(cmsApp2));
		System.out.println("--------------查询2------------------");
		List<CmsApp> cmsApps = cmsAppService.queryCmsApp();
		System.out.println(JSON.toJSONString(cmsApps));
	}
}
