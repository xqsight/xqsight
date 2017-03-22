package com.xqsight.cms.support;

import com.xqsight.cms.config.TemplateConfig;
import com.xqsight.common.freemarker.FreeMarkerImpl;
import com.xqsight.common.freemarker.TemplateElement;
import com.xqsight.common.freemarker.TemplateEngine;
import com.xqsight.common.freemarker.TemplateEngineException;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.URI;
import java.util.Map;

/**
 * Created by wangganggang on 2017/3/7.
 */
@Component
public class GenerateTemplate {

    protected Logger logger = LogManager.getLogger(GenerateTemplate.class);

    @Autowired
    private TemplateConfig templateConfig;

    private String SEPARATOR = "/";

    public String generate(Map model, String tplFileName, String fileName) throws TemplateEngineException {
        logger.debug("model:{},tplFileName:{},fileName:{}",model,tplFileName,fileName);
        java.net.URI uri = URI.create(templateConfig.getDisplayPath());
        model.put("domain",uri.getScheme() + "://" + uri.getHost());
        TemplateElement templateElement = new TemplateElement("", "freemark", tplFileName, templateConfig.getStorePath(), fileName, "utf-8");
        delFile(fileName);
        TemplateEngine templateEngine = new FreeMarkerImpl(getTemplatePath());
        templateEngine.processToFile(model, templateElement);

        String displayPath = templateConfig.getDisplayPath();
        StringBuffer sbpath = new StringBuffer(displayPath);
        if (!StringUtils.startsWith(displayPath, SEPARATOR)) {
            sbpath.append(File.separator);
        }
        return sbpath.append(fileName).toString();
    }

    private void delFile(String fileName) {
        String displayPath = templateConfig.getStorePath();
        StringBuffer sbpath = new StringBuffer(displayPath);
        if (!StringUtils.startsWith(displayPath, SEPARATOR)) {
            sbpath.append(File.separator);
        }
        File file = new File(sbpath.append(fileName).toString());
        if (file.exists()) {
            file.delete();
        }
    }

    private String getTemplatePath() {
        String CLASS_PATH = "classpath:";
        String tplPath = templateConfig.getTplPath();
        if (StringUtils.startsWith(tplPath, CLASS_PATH)) {
            tplPath = GenerateTemplate.class.getResource(StringUtils.replace(tplPath,CLASS_PATH,"")).getFile();
        }
        logger.debug("tplPath:{}",tplPath);
        return tplPath;
    }
}
