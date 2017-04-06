/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.cms.service.impl;

import com.xqsight.common.base.service.AbstractCrudService;
import org.springframework.stereotype.Service;

import com.xqsight.cms.model.CmsAd;
import com.xqsight.cms.service.CmsAdService;
import com.xqsight.cms.mapper.CmsAdMapper;

/**
 * <p>广告表实现类 service</p>
 * <p>Table: cms_ad - 广告表</p>
 * @since 2017-04-06 02:34:40
 * @author wangganggang
 */
@Service
public class CmsAdServiceImpl extends AbstractCrudService<CmsAdMapper,CmsAd, Long> implements CmsAdService {

}