/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.cms.service;

import com.xqsight.common.core.dao.Dao;
import com.xqsight.common.core.service.DefaultEntityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xqsight.cms.model.CmsPosition;
import com.xqsight.cms.mapper.CmsPositionMapper;


/**
 * <p>职位表实现类service</p>
 * <p>Table: cms_position - 职位表</p>
 * @since 2017-02-23 04:52:22
 * @author wangganggang
 */
@Service
public class CmsPositionService extends DefaultEntityService<CmsPosition, Long> {

	@Autowired
	private CmsPositionMapper cmsPositionMapper;

	@Override
	protected Dao<CmsPosition, Long> getDao() {
		return cmsPositionMapper;
	}
}