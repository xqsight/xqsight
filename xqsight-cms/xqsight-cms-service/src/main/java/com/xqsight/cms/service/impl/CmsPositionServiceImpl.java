/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.cms.service.impl;

import com.xqsight.common.base.service.AbstractCrudService;
import org.springframework.stereotype.Service;

import com.xqsight.cms.model.CmsPosition;
import com.xqsight.cms.service.CmsPositionService;
import com.xqsight.cms.mapper.CmsPositionMapper;

/**
 * <p>职位表实现类 service</p>
 * <p>Table: cms_position - 职位表</p>
 * @since 2017-04-06 02:35:02
 * @author wangganggang
 */
@Service
public class CmsPositionServiceImpl extends AbstractCrudService<CmsPositionMapper,CmsPosition, Long> implements CmsPositionService {

}