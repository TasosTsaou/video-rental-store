package com.tasos.sampleapi.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by tasos on 9/1/2016.
 */

@EnableTransactionManagement
@EnableJpaRepositories
@SpringBootApplication
public class VRSServer {

    public static final Logger logger = LoggerFactory.getLogger(VRSServer.class);

    public static void main(String[] args) {
        logger.debug("**** Starting VRS server ****");
        SpringApplication.run(VRSServer.class, args);
    }
}
