package com.xqsight.cms.support;

import com.xqsight.cms.model.CmsArticle;
import com.xqsight.common.freemarker.FreeMarkerImpl;
import com.xqsight.common.freemarker.TemplateElement;
import com.xqsight.common.freemarker.TemplateEngine;
import com.xqsight.common.freemarker.TemplateEngineException;

import java.io.File;
import java.util.Map;

/**
 * Created by wangganggang on 2017/3/7.
 */
public class GenerateArticleSupport {

    private String templateFile;

    public String generateTempl(CmsArticle cmsArticle) throws TemplateEngineException {
        TemplateEngine templateEngine = new FreeMarkerImpl(templateFile);
        TemplateElement templateElement = getTemplate();
        templateEngine.processToFile(getRendMode(cmsArticle), templateElement);
        return templateElement.getTargetPath() + File.separator + templateElement.getTargetFileName();
    }

    private Map getRendMode(CmsArticle cmsArticle) {
        return null;
    }

    private TemplateElement getTemplate() {
        return null;
    }


}
