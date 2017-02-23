/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.cms.service;

import com.xqsight.common.core.dao.Dao;
import com.xqsight.common.core.service.DefaultEntityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xqsight.cms.model.CmsModel;
import com.xqsight.cms.mapper.CmsModelMapper;


/**
 * <p>模块表实现类service</p>
 * <p>Table: cms_model - 模块表</p>
 * @since 2017-02-23 04:52:18
 * @author wangganggang
 */
@Service
public class CmsModelService extends DefaultEntityService<CmsModel, Long> {

	@Autowired
	private CmsModelMapper cmsModelMapper;

	@Override
	protected Dao<CmsModel, Long> getDao() {
		return cmsModelMapper;
	}
}