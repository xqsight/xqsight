package com.xqsight.mybatis.test;

import com.xqsight.Application;
import com.xqsight.cms.model.CmsModel;
import com.xqsight.cms.service.CmsAdService;
import com.xqsight.cms.service.CmsModelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wangganggang
 * @date 2017/03/27
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class)
public class CmsModeTest {

    @Autowired
    private CmsModelService cmsModelService;

    @Test
    public void queryTest() throws Exception{
        CmsModel cmsModel = new CmsModel();
        cmsModel.setSiteId(1L);
        cmsModel.setParentId("0");

        cmsModel.setModelCode("1123");
        cmsModel.setModelName("123123");
        cmsModelService.saveTest(cmsModel);
        System.out.println(cmsModel.getModelCode());
    }
}
