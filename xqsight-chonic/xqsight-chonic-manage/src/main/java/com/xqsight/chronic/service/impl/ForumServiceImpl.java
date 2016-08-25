/**
 * 新启工作室
 * Copyright (c) 1994-2016 All Rights Reserved.
 */
package com.xqsight.chronic.service.impl;

import com.alibaba.fastjson.JSON;
import com.xqsight.chronic.service.ForumService;
import com.xqsight.cms.model.CmsArticle;
import com.xqsight.cms.mysqlmapper.CmsArticleMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: TODO
 * @author wangganggang
 * @date 2016年5月18日 上午10:56:27
 *
 */
@Service
public class ForumServiceImpl implements ForumService {

	private static Logger logger = LogManager.getLogger(ForumServiceImpl.class);

	@Autowired
	private CmsArticleMapper cmsArticleMapper;

	@Override
	public void saveForum(CmsArticle cmsArticle) {
		logger.debug("出入参数:cmsArticle={}", JSON.toJSONString(cmsArticle));
		cmsArticleMapper.saveCmsArticle(cmsArticle);
	}
}
