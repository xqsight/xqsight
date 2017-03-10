/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.cms.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xqsight.cms.mapper.CmsArticleMapper;
import com.xqsight.cms.model.CmsArticle;
import com.xqsight.cms.model.CmsTag;
import com.xqsight.cms.support.GenerateTemplate;
import com.xqsight.common.core.dao.Dao;
import com.xqsight.common.core.orm.MatchType;
import com.xqsight.common.core.orm.PropertyFilter;
import com.xqsight.common.core.orm.PropertyType;
import com.xqsight.common.core.orm.builder.PropertyFilterBuilder;
import com.xqsight.common.core.service.DefaultEntityService;
import com.xqsight.common.freemarker.TemplateEngineException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * <p>文章表实现类service</p>
 * <p>Table: cms_article - 文章表</p>
 *
 * @author wangganggang
 * @since 2017-02-23 04:52:03
 */
@Service
public class CmsArticleService extends DefaultEntityService<CmsArticle, Long> {

    @Autowired
    private CmsArticleMapper cmsArticleMapper;

    @Autowired
    private CmsTagService cmsTagService;

    @Autowired
    private GenerateTemplate generateTemplate;

    @Override
    protected Dao<CmsArticle, Long> getDao() {
        return cmsArticleMapper;
    }

    public void saveArticle(CmsArticle cmsArticle) throws TemplateEngineException {
        this.save(cmsArticle, true);
        saveArticleTag(cmsArticle);
        generateHtmlContent(cmsArticle);
    }

    public void updateArticle(CmsArticle cmsArticle) throws TemplateEngineException {
        this.update(cmsArticle, true);
        saveArticleTag(cmsArticle);
        generateHtmlContent(cmsArticle);
    }

    public List queryTagByArticle(long articleId) {
        return cmsArticleMapper.queryArticleTag(articleId);
    }

    private void saveArticleTag(CmsArticle cmsArticle) {
        List<Long> tagId = new ArrayList<>();
        cmsArticle.getTags().stream().forEach(tag -> {
            List<PropertyFilter> propertyFilters = PropertyFilterBuilder.create().matchTye(MatchType.EQ)
                    .propertyType(PropertyType.S).add("tag_name", tag).end();
            List<CmsTag> cmsTags = cmsTagService.search(propertyFilters);
            if (cmsTags.size() > 0) {
                tagId.add(cmsTags.get(0).getTagId());
            } else {
                CmsTag cmsTag = new CmsTag();
                cmsTag.setTagName(tag);
                cmsTagService.save(cmsTag, true);
                tagId.add(cmsTag.getTagId());
            }
        });

        cmsArticleMapper.deleteArticleTag(cmsArticle.getArticleId());

        tagId.stream().forEach(x -> {
            cmsArticleMapper.saveArticleTag(cmsArticle.getArticleId(), x);
        });

    }

    private void generateHtmlContent(CmsArticle cmsArticle) throws TemplateEngineException {
        ObjectMapper oMapper = new ObjectMapper();
        Map modelMap = oMapper.convertValue(cmsArticle, Map.class);
        modelMap.put("publishTime", cmsArticle.getPublishTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        String fileName = "article_" + cmsArticle.getArticleId() + ".html";
        String articleUrl = generateTemplate.generate(modelMap, "article.html", fileName);

        CmsArticle updArticle = new CmsArticle();
        updArticle.setArticleId(cmsArticle.getArticleId());
        updArticle.setArticleUrl(articleUrl);
        cmsArticleMapper.updateByPrimaryKeySelective(updArticle);
    }
}