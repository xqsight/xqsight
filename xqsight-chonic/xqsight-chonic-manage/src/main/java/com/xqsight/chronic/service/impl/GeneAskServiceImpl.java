/**
 * 新启工作室
 * Copyright (c) 1994-2016 All Rights Reserved.
 */
package com.xqsight.chronic.service.impl;

import com.alibaba.fastjson.JSON;
import com.xqsight.chronic.service.GeneAskService;
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
public class GeneAskServiceImpl implements GeneAskService{

	private static Logger logger = LogManager.getLogger(GeneAskServiceImpl.class); 
	
	@Autowired
	private CmsArticleMapper cmsArticleMapper;

	/**
	 * <p>Title: saveGenAsk</p>
	 * <p>Description: </p>
	 * @param cmsArticle
	 * @see com.xqsight.chronic.service.GeneAskService#saveGenAsk(CmsArticle)
	 */
	@Override
	public void saveGenAsk(CmsArticle cmsArticle) {
		logger.debug("出入参数:cmsArticle={}", JSON.toJSONString(cmsArticle));
		cmsArticleMapper.saveCmsArticle(cmsArticle);
	}

}
