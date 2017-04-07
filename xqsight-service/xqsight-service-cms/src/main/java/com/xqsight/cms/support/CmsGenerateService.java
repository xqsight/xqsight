package com.xqsight.cms.support;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xqsight.cms.model.CmsAd;
import com.xqsight.cms.model.CmsArticle;
import com.xqsight.cms.service.CmsAdService;
import com.xqsight.cms.service.CmsArticleService;
import com.xqsight.cms.service.CmsJobService;
import com.xqsight.common.core.orm.MatchType;
import com.xqsight.common.core.orm.PropertyFilter;
import com.xqsight.common.core.orm.PropertyType;
import com.xqsight.common.core.orm.Sort;
import com.xqsight.common.core.orm.builder.PropertyFilterBuilder;
import com.xqsight.common.core.orm.builder.SortBuilder;
import com.xqsight.common.exception.TemplateEngineException;
import com.xqsight.common.utils.MapKeyHandle;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @since 2017-04-06 02:35:07
 * @author wangganggang
 */
@Component
public class CmsGenerateService {

    protected Logger logger = LogManager.getLogger(CmsGenerateService.class);

    @Autowired
    private CmsArticleService cmsArticleService;

    @Autowired
    private CmsJobService cmsJobService;

    @Autowired
    private CmsAdService cmsAdService;

    @Autowired
    private GenerateTemplate generateTemplate;

    @PostConstruct
    public void generateStaticHtml()throws TemplateEngineException {
        logger.debug("start generate service & event and aboutus html");
        generateTemplate.generate(null,"template/service.html", "service.html");
        generateTemplate.generate(null,"template/event.html", "event.html");
        generateTemplate.generate(null,"template/aboutus.html", "aboutus.html");
        logger.debug("generate service & event and aboutus html end");
    }

    public void generateIndex() throws TemplateEngineException {
        logger.debug("开始生成首页");
        Map modelMap = new HashMap();
        Page page = PageHelper.startPage(1,1000);
        List<PropertyFilter> propertyFilters = PropertyFilterBuilder.create().matchTye(MatchType.EQ)
                .propertyType(PropertyType.I).add("article_hit","0").end();
        List<Sort> sorts = SortBuilder.create().addDesc("article_id").end();
        List<CmsArticle> articles = cmsArticleService.getByFilters(propertyFilters,sorts);
        articles.stream().forEach(cmsArticle -> {
            List<String> tags = cmsArticleService.queryArticleTag(cmsArticle.getArticleId());
            cmsArticle.setTags(tags);
        });
        page = PageHelper.startPage(1,3);
        propertyFilters = PropertyFilterBuilder.create().matchTye(MatchType.EQ)
                .propertyType(PropertyType.I).add("type","1").end();
        sorts = SortBuilder.create().addDesc("sort").end();
        List<CmsAd> cmsAds = cmsAdService.getByFilters(propertyFilters,sorts);
        modelMap.put("articles",articles);
        modelMap.put("cmsAds",cmsAds);

        String articleUrl = generateTemplate.generate(modelMap, "template/index.html", "index.html");
        //保存到站点
    }

    public void generateJob() throws TemplateEngineException {
        Map modelMap = new HashMap();
        List<Map> jobs = cmsJobService.queryJob(null);
        Map position = new HashMap();

        List<Map> jobMaps = jobs.parallelStream().map(map -> {
            position.put(MapUtils.getString(map, "position_id"), MapUtils.getString(map, "position_name"));

            String createTime = MapUtils.getString(map, "create_time");
            map.put("create_time", StringUtils.substring(createTime, 0, 10));
            return MapKeyHandle.keyToJavaProperty(map);
        }).collect(Collectors.toList());

        modelMap.put("position", position);
        modelMap.put("jobs", jobMaps);
        generateTemplate.generate(modelMap, "template/joinus.html", "joinus.html");
    }

    public void generateArticle(CmsArticle cmsArticle) throws TemplateEngineException {
        ObjectMapper oMapper = new ObjectMapper();
        Map modelMap = oMapper.convertValue(cmsArticle, Map.class);
        modelMap.put("publishTime", cmsArticle.getPublishTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        String fileName = "article_" + cmsArticle.getArticleId() + ".html";
        String articleUrl = generateTemplate.generate(modelMap, "template/article.html", fileName);

        CmsArticle updArticle = new CmsArticle();
        updArticle.setArticleId(cmsArticle.getArticleId());
        updArticle.setArticleUrl(articleUrl);
        cmsArticleService.editById(updArticle);
    }
}
