/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.cms.service;

import com.xqsight.common.core.dao.Dao;
import com.xqsight.common.core.service.DefaultEntityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xqsight.cms.model.CmsJob;
import com.xqsight.cms.mapper.CmsJobMapper;


/**
 * <p>招聘表实现类service</p>
 * <p>Table: cms_job - 招聘表</p>
 * @since 2017-02-23 04:52:11
 * @author wangganggang
 */
@Service
public class CmsJobService extends DefaultEntityService<CmsJob, Long> {

	@Autowired
	private CmsJobMapper cmsJobMapper;

	@Override
	protected Dao<CmsJob, Long> getDao() {
		return cmsJobMapper;
	}
}