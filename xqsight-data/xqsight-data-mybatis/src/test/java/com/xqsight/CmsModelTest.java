package com.xqsight;

import com.xqsight.cms.mapper.CmsModelMapper;
import com.xqsight.cms.model.CmsModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author wangganggang
 * @date 2017/03/24
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes=Application.class)
public class CmsModelTest {

    @Autowired
    private CmsModelMapper cmsModelMapper;

    @Test
    public void testQuery(){
        List<CmsModel> cmsModel = cmsModelMapper.search(null);
        System.out.println(cmsModel.size());
    }

}
