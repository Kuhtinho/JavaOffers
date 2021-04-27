package com.junioroffers.config;

import com.junioroffers.infrastructure.RemoteOfferClient;
import com.junioroffers.infrastructure.offer.client.OfferClient;
import com.junioroffers.infrastructure.offer.error.RestTemplateResponseErrorHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class Config {

    @Bean
    RestTemplateResponseErrorHandler restTemplateResponseErrorHandler(){
        return new RestTemplateResponseErrorHandler();
    }

    @Bean
    RestTemplate restTemplate(@Value("${offer.http.client.config.connectionTimeout}") long connectionTimeout,
                              @Value("${offer.http.client.config.readTimeout}") long readTimeout,
                              RestTemplateResponseErrorHandler restTemplateResponseErrorHandler){
        return new RestTemplateBuilder()
                .errorHandler(restTemplateResponseErrorHandler)
                .setConnectTimeout(Duration.ofMillis(connectionTimeout))
                .setReadTimeout(Duration.ofMillis(readTimeout))
                .build();
    }

    @Bean
    RemoteOfferClient remoteOfferClient(RestTemplate restTemplate, @Value("{offer.http.client.config.uti:http://offer.com}") String uri){
        return new OfferClient(restTemplate, uri);
    }

}
