package com.xqsight.cms.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by wangganggang on 2017/3/10.
 *
 */
@Component
@Data
public class TemplateConfig {

    @Value("${file.upload.store.path}")
    private String storePath;

    @Value("${file.upload.access.domain}")
    private String displayPath;

    @Value("${site.tpl.path}")
    private String tplPath;

}
