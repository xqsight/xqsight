package com.xqsight.cms.support;

import com.xqsight.common.freemarker.FreeMarkerImpl;
import com.xqsight.common.freemarker.TemplateEngine;
import org.springframework.stereotype.Component;

/**
 * Created by wangganggang on 2017/3/7.
 */
@Component
public class GenerateTemplate {

    private void init() {

    }

    public String generateTemp() {
        TemplateEngine templateEngine = new FreeMarkerImpl("");
        return null;
    }

}
