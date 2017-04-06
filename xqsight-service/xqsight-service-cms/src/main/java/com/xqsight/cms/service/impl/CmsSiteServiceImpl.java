/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.cms.service.impl;

import com.xqsight.common.base.service.AbstractCrudService;
import org.springframework.stereotype.Service;

import com.xqsight.cms.model.CmsSite;
import com.xqsight.cms.service.CmsSiteService;
import com.xqsight.cms.mapper.CmsSiteMapper;

/**
 * <p>站点表实现类 service</p>
 * <p>Table: cms_site - 站点表</p>
 * @since 2017-04-06 02:35:07
 * @author wangganggang
 */
@Service
public class CmsSiteServiceImpl extends AbstractCrudService<CmsSiteMapper,CmsSite, Long> implements CmsSiteService {

}