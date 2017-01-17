package com.tasos.sampleapi.integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * VRS Integration Test main class.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class ITestVRSServer {

    public static void main(String[] args) {
        SpringApplication.run(ITestVRSServer.class, args);
    }

}
