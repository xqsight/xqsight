package com.saicfc.themis.data.neo4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by GTaurus on 2016/4/5.
 */
@Configuration
@ComponentScan(basePackages = "com.saicfc.themis")
@EnableNeo4jRepositories(basePackages = "com.saicfc.themis.data.neo4j.repository")
@EnableTransactionManagement
public class Neo4jConfig extends Neo4jConfiguration {

    private final Logger logger = LogManager.getLogger(Neo4jConfig.class);

    @Autowired
    private Environment env;

    @Bean
    public org.neo4j.ogm.config.Configuration getConfiguration() {
        org.neo4j.ogm.config.Configuration config = new org.neo4j.ogm.config.Configuration();
        config
                .driverConfiguration()
                .setDriverClassName(env.getProperty("driver", "org.neo4j.ogm.drivers.http.driver.HttpDriver"))
                .setURI(env.getProperty("URI", "http://neo4j:1@localhost:7474"));
        return config;
    }


    @Override
    @Bean
    @Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Session getSession() throws Exception {
        logger.info("Initialising session-scoped Session Bean");
        return super.getSession();
    }

    @Override
    @Bean
    public SessionFactory getSessionFactory() {
        return new SessionFactory("com.saicfc.themis.data.neo4j.domain");
    }

}
