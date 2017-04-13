package com.xqsight.cms.support;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

/**
 * @author wangganggang
 * @since 2017-04-06 02:35:07
 */
public class CmsGeneratInit implements CommandLineRunner {

    protected Logger logger = LogManager.getLogger(CmsGeneratInit.class);

    @Autowired
    private GenerateTemplate generateTemplate;

    @Override
    public void run(String... args) throws Exception {
        logger.debug("start generate service and aboutus html");
        generateTemplate.generate(null, "template/service.html", "service.html");
        generateTemplate.generate(null, "template/aboutus.html", "aboutus.html");
        logger.debug("generate service & event and aboutus html end");
    }

}
