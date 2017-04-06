/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.cms.service.impl;

import com.xqsight.cms.model.CmsTag;
import com.xqsight.cms.service.CmsTagService;
import com.xqsight.common.base.service.AbstractCrudService;
import com.xqsight.common.core.orm.MatchType;
import com.xqsight.common.core.orm.PropertyFilter;
import com.xqsight.common.core.orm.PropertyType;
import com.xqsight.common.core.orm.builder.PropertyFilterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xqsight.cms.model.CmsArticle;
import com.xqsight.cms.service.CmsArticleService;
import com.xqsight.cms.mapper.CmsArticleMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>文章表实现类 service</p>
 * <p>Table: cms_article - 文章表</p>
 * @since 2017-04-06 02:34:47
 * @author wangganggang
 */
@Service
public class CmsArticleServiceImpl extends AbstractCrudService<CmsArticleMapper,CmsArticle, Long> implements CmsArticleService {

    @Autowired
    private CmsTagService cmsTagService;

    @Override
    public void saveArticleTag(CmsArticle cmsArticle) {
        List<Long> tagId = new ArrayList<>();
        cmsArticle.getTags().stream().forEach(tag -> {
            List<PropertyFilter> propertyFilters = PropertyFilterBuilder.create().matchTye(MatchType.EQ)
                    .propertyType(PropertyType.S).add("tag_name", tag).end();
            List<CmsTag> cmsTags = cmsTagService.getByFilters(propertyFilters);
            if (cmsTags.size() > 0) {
                tagId.add(cmsTags.get(0).getTagId());
            } else {
                CmsTag cmsTag = new CmsTag();
                cmsTag.setTagName(tag);
                cmsTagService.add(cmsTag);
                tagId.add(cmsTag.getTagId());
            }
        });

        this.dao.deleteArticleTag(cmsArticle.getArticleId());

        tagId.stream().forEach(x -> this.dao.saveArticleTag(cmsArticle.getArticleId(), x) );
    }

    @Override
    public List<String> queryArticleTag(long articleId) {
        return this.dao.queryArticleTag(articleId);
    }
}