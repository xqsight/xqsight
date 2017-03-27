/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.cms.service.impl;


import com.xqsight.cms.mapper.CmsAdMapper;
import com.xqsight.cms.model.CmsAd;
import com.xqsight.cms.service.CmsAdService;
import com.xqsight.common.base.service.AbstractCrudService;
import org.springframework.stereotype.Service;


/**
 * <p>广告表实现类service</p>
 * <p>Table: cms_ad - 广告表</p>
 * @since 2017-02-23 04:51:56
 * @author wangganggang
 */
@Service
public class CmsAdServiceImpl extends AbstractCrudService<CmsAdMapper,CmsAd, Long> implements CmsAdService {

}