package com.xqsight.system;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by wangganggang on 2017/1/1.
 */
@ImportResource({"classpath*:xqsight-common-dao.xml",
        "classpath*:xqsight-data-ehcache.xml",
        "classpath*:eju-micro-sso-shiro.xml",
        "classpath:applicationContext.xml"})
@SpringBootApplication(scanBasePackages = {"com.xqsight"})
@EnableAutoConfiguration
public class Application {

    private static final Logger logger = LogManager.getLogger();

    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
        logger.info("The program can be stoped using <ctrl>+<c>");
    }
}
