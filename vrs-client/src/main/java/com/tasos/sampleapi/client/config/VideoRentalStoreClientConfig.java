package com.tasos.sampleapi.client.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("production")
public class VideoRentalStoreClientConfig {

    @Value("${vrs.server.host}")
    private String host;

    @Value("${vrs.server.port}")
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
