/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.cms.service;

import com.xqsight.common.core.dao.Dao;
import com.xqsight.common.core.service.DefaultEntityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xqsight.cms.model.CmsAd;
import com.xqsight.cms.mapper.CmsAdMapper;


/**
 * <p>广告表实现类service</p>
 * <p>Table: cms_ad - 广告表</p>
 * @since 2017-02-23 04:51:56
 * @author wangganggang
 */
@Service
public class CmsAdService extends DefaultEntityService<CmsAd, Long> {

	@Autowired
	private CmsAdMapper cmsAdMapper;

	@Override
	protected Dao<CmsAd, Long> getDao() {
		return cmsAdMapper;
	}
}