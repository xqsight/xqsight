/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.cms.service;

import com.xqsight.common.core.dao.Dao;
import com.xqsight.common.core.service.DefaultEntityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xqsight.cms.model.CmsSite;
import com.xqsight.cms.mapper.CmsSiteMapper;


/**
 * <p>站点表实现类service</p>
 * <p>Table: cms_site - 站点表</p>
 * @since 2017-02-23 04:52:30
 * @author wangganggang
 */
@Service
public class CmsSiteService extends DefaultEntityService<CmsSite, Long> {

	@Autowired
	private CmsSiteMapper cmsSiteMapper;

	@Override
	protected Dao<CmsSite, Long> getDao() {
		return cmsSiteMapper;
	}
}