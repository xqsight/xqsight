/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.cms.service.impl;


import com.xqsight.cms.mapper.CmsModelMapper;
import com.xqsight.cms.model.CmsModel;
import com.xqsight.cms.service.CmsModelService;
import com.xqsight.common.base.service.AbstractCrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * <p>广告表实现类service</p>
 * <p>Table: cms_ad - 广告表</p>
 * @since 2017-02-23 04:51:56
 * @author wangganggang
 */
@Service
public class CmsModelServiceImpl extends AbstractCrudService<CmsModelMapper,CmsModel, Long> implements CmsModelService {

    @Override
    @Transactional
    public void saveTest(CmsModel cmsModel)throws Exception{
        this.add(cmsModel);
        throw new Exception();
//        int i=1/0;
    }
}