package com.tangchao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wangganggang
 * @date 2017年07月21日 15:34
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude={MultipartAutoConfiguration.class})
@ComponentScan(basePackages = {"com.xqsight","com.tangchao"})
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
