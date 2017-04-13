package com.xqsight.cms.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xqsight.cms.model.CmsArticle;
import com.xqsight.cms.service.CmsArticleService;
import com.xqsight.common.core.orm.MatchType;
import com.xqsight.common.core.orm.PropertyFilter;
import com.xqsight.common.core.orm.PropertyType;
import com.xqsight.common.core.orm.Sort;
import com.xqsight.common.core.orm.builder.PropertyFilterBuilder;
import com.xqsight.common.core.orm.builder.SortBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangganggang
 * @since 2017-04-06 02:34:40
 *
 */
@RestController
@RequestMapping("/site/")
public class SiteController {

    @Autowired
    private CmsArticleService cmsArticleService;

    @RequestMapping(value = "article/link/{articleId}",method = RequestMethod.GET)
    public Object getArticleLink(@PathVariable String articleId){
        Page page = PageHelper.startPage(1,1);
        List<PropertyFilter> prePropertyFilters = PropertyFilterBuilder.create().matchTye(MatchType.LT)
                .propertyType(PropertyType.L).add("article_id",articleId).end();
        List<Sort> preSorts = SortBuilder.create().addDesc("article_id").end();
        List<CmsArticle> preArticles = cmsArticleService.getByFilters(prePropertyFilters,preSorts);
        Map preArticle = new HashMap();
        if(preArticles.size() > 0){
            preArticle.put("articleUrl",preArticles.get(0).getArticleUrl());
            preArticle.put("articleTitle",preArticles.get(0).getArticleTitle());
        }
        page = PageHelper.startPage(1,1);
        List<PropertyFilter> nextPropertyFilters = PropertyFilterBuilder.create().matchTye(MatchType.GT)
                .propertyType(PropertyType.L).add("article_id",articleId).end();
        List<Sort> nextSorts = SortBuilder.create().addAsc("article_id").end();
        List<CmsArticle> nextArticles = cmsArticleService.getByFilters(nextPropertyFilters,nextSorts);
        Map nextArticle = new HashMap();
        if(nextArticles.size() > 0){
            nextArticle.put("articleUrl",nextArticles.get(0).getArticleUrl());
            nextArticle.put("articleTitle",nextArticles.get(0).getArticleTitle());
        }
        Map retMap = new HashMap();
        retMap.put("preArticle",preArticle);
        retMap.put("nextArticle",nextArticle);
        return  retMap;
    }
}
