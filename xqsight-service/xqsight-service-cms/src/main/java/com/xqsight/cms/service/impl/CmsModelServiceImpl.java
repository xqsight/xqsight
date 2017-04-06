/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.cms.service.impl;

import com.xqsight.common.base.service.AbstractCrudService;
import org.springframework.stereotype.Service;

import com.xqsight.cms.model.CmsModel;
import com.xqsight.cms.service.CmsModelService;
import com.xqsight.cms.mapper.CmsModelMapper;

/**
 * <p>模块表实现类 service</p>
 * <p>Table: cms_model - 模块表</p>
 * @since 2017-04-06 02:34:57
 * @author wangganggang
 */
@Service
public class CmsModelServiceImpl extends AbstractCrudService<CmsModelMapper,CmsModel, Long> implements CmsModelService {

}