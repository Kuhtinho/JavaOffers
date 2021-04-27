package com.junioroffers.config;

import com.junioroffers.infrastructure.RemoteOfferClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@Configuration
class ConfigTest extends Config {

    public RemoteOfferClient remoteOfferClient(String uri, int connectionTimeout, int readTimeout){
        final RestTemplate restTemplate = restTemplate(connectionTimeout, readTimeout, restTemplateResponseErrorHandler());
        return remoteOfferClient(restTemplate, uri);
    }
}