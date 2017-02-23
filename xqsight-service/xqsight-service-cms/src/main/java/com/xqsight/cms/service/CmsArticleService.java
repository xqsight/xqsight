/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.cms.service;

import com.xqsight.common.core.dao.Dao;
import com.xqsight.common.core.service.DefaultEntityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xqsight.cms.model.CmsArticle;
import com.xqsight.cms.mapper.CmsArticleMapper;


/**
 * <p>文章表实现类service</p>
 * <p>Table: cms_article - 文章表</p>
 * @since 2017-02-23 04:52:03
 * @author wangganggang
 */
@Service
public class CmsArticleService extends DefaultEntityService<CmsArticle, Long> {

	@Autowired
	private CmsArticleMapper cmsArticleMapper;

	@Override
	protected Dao<CmsArticle, Long> getDao() {
		return cmsArticleMapper;
	}
}