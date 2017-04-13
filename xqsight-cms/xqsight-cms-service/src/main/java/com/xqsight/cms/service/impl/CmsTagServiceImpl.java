/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.cms.service.impl;

import com.xqsight.common.base.service.AbstractCrudService;
import org.springframework.stereotype.Service;

import com.xqsight.cms.model.CmsTag;
import com.xqsight.cms.service.CmsTagService;
import com.xqsight.cms.mapper.CmsTagMapper;

/**
 * <p>标签表实现类 service</p>
 * <p>Table: cms_tag - 标签表</p>
 * @since 2017-04-06 02:35:12
 * @author wangganggang
 */
@Service
public class CmsTagServiceImpl extends AbstractCrudService<CmsTagMapper,CmsTag, Long> implements CmsTagService {

}