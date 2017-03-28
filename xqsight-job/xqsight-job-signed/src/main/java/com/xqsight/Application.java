package com.xqsight;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by wangganggang on 2017/1/1.
 */
@SpringBootApplication
@EnableScheduling
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
