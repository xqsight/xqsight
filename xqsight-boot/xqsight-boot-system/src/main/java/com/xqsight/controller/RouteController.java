package com.xqsight.controller;

import com.github.pagehelper.Page;
import com.xqsight.cms.model.CmsAd;
import com.xqsight.cms.model.CmsArticle;
import com.xqsight.cms.service.CmsAdService;
import com.xqsight.cms.service.CmsArticleService;
import com.xqsight.common.core.orm.MatchType;
import com.xqsight.common.core.orm.PropertyFilter;
import com.xqsight.common.core.orm.PropertyType;
import com.xqsight.common.core.orm.Sort;
import com.xqsight.common.core.orm.builder.PropertyFilterBuilder;
import com.xqsight.common.core.orm.builder.SortBuilder;
import com.xqsight.common.core.support.XqsightPageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by wangganggang on 2017/3/14.
 */
@Controller
public class RouteController {

    @Autowired
    private CmsArticleService cmsArticleService;

    @Autowired
    private CmsAdService cmsAdService;

    @RequestMapping("/")
    public String redirectIndex(Model model) {
        List<PropertyFilter> propertyFilters = PropertyFilterBuilder.create().matchTye(MatchType.EQ)
                .propertyType(PropertyType.I).add("article_hit","0").end();
        List<Sort> sorts = SortBuilder.create().addDesc("article_id").end();
        List<CmsArticle> articles = cmsArticleService.search(propertyFilters,sorts);
        articles.stream().forEach(cmsArticle -> {
            List<String> tags = cmsArticleService.queryTagByArticle(cmsArticle.getArticleId());
            cmsArticle.setTags(tags);
        });
        Page page = XqsightPageHelper.startPage(1,3);
        propertyFilters = PropertyFilterBuilder.create().matchTye(MatchType.EQ)
                .propertyType(PropertyType.I).add("type","1").end();
        sorts = SortBuilder.create().addDesc("sort").end();
        List<CmsAd> cmsAds = cmsAdService.search(propertyFilters,sorts);
        model.addAttribute("articles",articles);
        model.addAttribute("cmsAds",cmsAds);
        return "index";
    }
}
