package com.xqsight.cms.service;

import com.github.pagehelper.Page;
import com.xqsight.cms.model.CmsAd;
import com.xqsight.cms.model.CmsArticle;
import com.xqsight.cms.support.GenerateTemplate;
import com.xqsight.common.core.orm.MatchType;
import com.xqsight.common.core.orm.PropertyFilter;
import com.xqsight.common.core.orm.PropertyType;
import com.xqsight.common.core.orm.Sort;
import com.xqsight.common.core.orm.builder.PropertyFilterBuilder;
import com.xqsight.common.core.orm.builder.SortBuilder;
import com.xqsight.common.core.support.XqsightPageHelper;
import com.xqsight.common.freemarker.TemplateEngineException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangganggang on 2017/3/17.
 *
 */
@Component
public class CmsGenerateService {

    protected Logger logger = LogManager.getLogger(CmsGenerateService.class);

    @Autowired
    private CmsArticleService cmsArticleService;

    @Autowired
    private CmsAdService cmsAdService;

    @Autowired
    private GenerateTemplate generateTemplate;

    public void generateIndex() throws TemplateEngineException {
        logger.debug("开始生成首页");
        Map modelMap = new HashMap();
        Page page = XqsightPageHelper.startPage(1,10);
        List<PropertyFilter> propertyFilters = PropertyFilterBuilder.create().matchTye(MatchType.EQ)
                .propertyType(PropertyType.I).add("article_hit","0").end();
        List<Sort> sorts = SortBuilder.create().addDesc("article_id").end();
        List<CmsArticle> articles = cmsArticleService.search(propertyFilters,sorts);
        articles.stream().forEach(cmsArticle -> {
            List<String> tags = cmsArticleService.queryTagByArticle(cmsArticle.getArticleId());
            cmsArticle.setTags(tags);
        });
        page = XqsightPageHelper.startPage(1,3);
        propertyFilters = PropertyFilterBuilder.create().matchTye(MatchType.EQ)
                .propertyType(PropertyType.I).add("type","1").end();
        sorts = SortBuilder.create().addDesc("sort").end();
        List<CmsAd> cmsAds = cmsAdService.search(propertyFilters,sorts);
        modelMap.put("articles",articles);
        modelMap.put("cmsAds",cmsAds);

        String articleUrl = generateTemplate.generate(modelMap, "template/index.html", "index.html");
        //保存到站点
    }
}
