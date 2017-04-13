/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.cms.service;

import com.xqsight.common.base.service.ICrudService;
import com.xqsight.cms.model.CmsArticle;

import java.util.List;

/**
* <p>文章表 service</p>
* <p>Table: cms_article - 文章表</p>
* @since 2017-04-06 02:34:47
* @author wangganggang
*/
public interface CmsArticleService extends ICrudService<CmsArticle, Long> {

    void saveArticleTag(CmsArticle cmsArticle);

    List<String> queryArticleTag(long articleId);
}