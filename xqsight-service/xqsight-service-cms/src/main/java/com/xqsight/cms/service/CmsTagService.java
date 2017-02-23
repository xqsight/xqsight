/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.cms.service;

import com.xqsight.common.core.dao.Dao;
import com.xqsight.common.core.service.DefaultEntityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xqsight.cms.model.CmsTag;
import com.xqsight.cms.mapper.CmsTagMapper;


/**
 * <p>标签表实现类service</p>
 * <p>Table: cms_tag - 标签表</p>
 * @since 2017-02-23 04:52:38
 * @author wangganggang
 */
@Service
public class CmsTagService extends DefaultEntityService<CmsTag, Long> {

	@Autowired
	private CmsTagMapper cmsTagMapper;

	@Override
	protected Dao<CmsTag, Long> getDao() {
		return cmsTagMapper;
	}
}