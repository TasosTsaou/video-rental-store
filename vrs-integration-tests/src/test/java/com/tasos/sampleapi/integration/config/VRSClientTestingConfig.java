package com.tasos.sampleapi.integration.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.tasos.sampleapi.client.config.UrlRootInfo;

/**
 * API Version configuration for testing.
 */
@Profile("test")
@Configuration
@ComponentScan({ "com.tasos.sampleapi.client.impl", "com.tasos.sampleapi.client.config" })
public class VRSClientTestingConfig {
    @Value("${vrs.server.host:localhost}")
    private String host;

    @Value("${vrs.server.port:9081}")
    private String port;

    // @Autowired
    // @Qualifier("VRSRestTemplate")
    // RestTemplate restTemplate;

    @Bean
    public UrlRootInfo getUrlRootInfo() {
        UrlRootInfo urlRootInfo = new UrlRootInfo();
        urlRootInfo.setHost(host);
        urlRootInfo.setPort(port);
        return urlRootInfo;
    }

}
