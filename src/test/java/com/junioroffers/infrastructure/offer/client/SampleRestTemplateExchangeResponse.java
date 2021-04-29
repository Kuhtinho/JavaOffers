package com.junioroffers.infrastructure.offer.client;

import com.junioroffers.infrastructure.offer.dto.OfferDto;
import org.apache.catalina.LifecycleState;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public interface SampleRestTemplateExchangeResponse {

    default ResponseEntity<List<OfferDto>> getExchange(RestTemplate restTemplate){
        return restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<ParameterizedTypeReference<List<OfferDto>>>any());
    }
}
