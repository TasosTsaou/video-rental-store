package com.tasos.sampleapi.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class VRSClient {

    private static Logger logger = (Logger) LoggerFactory.getLogger(VRSClient.class);

    public static void main(String[] args) {
        logger.debug("**** Starting Video Rental Store client ****");
        SpringApplication.run(VRSClient.class, args);
    }

}
