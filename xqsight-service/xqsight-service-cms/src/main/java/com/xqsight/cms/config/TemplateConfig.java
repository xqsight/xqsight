package com.xqsight.cms.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by wangganggang on 2017/3/10.
 *
 */
@Component
public class TemplateConfig {

    @Value("${file.upload.store.path:}")
    private String storePath;

    @Value("${file.upload.access.domain:}")
    private String displayPath;

    @Value("${site.tpl.path:}")
    private String tplPath;


    public String getStorePath() {
        return storePath;
    }

    public void setStorePath(String storePath) {
        this.storePath = storePath;
    }

    public String getDisplayPath() {
        return displayPath;
    }

    public void setDisplayPath(String displayPath) {
        this.displayPath = displayPath;
    }

    public String getTplPath() {
        return tplPath;
    }

    public void setTplPath(String tplPath) {
        this.tplPath = tplPath;
    }
}
