package com.junioroffers.infrastructure.offer.client;


import com.junioroffers.infrastructure.RemoteOfferClient;
import com.junioroffers.infrastructure.offer.dto.OfferDto;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Collections;

@AllArgsConstructor
public class OfferClient implements RemoteOfferClient {

    private final RestTemplate restTemplate;
    private final String uri;

    @Override
    public List<OfferDto> getOffers() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        final HttpEntity<HttpHeaders> requestEntity = new HttpEntity<>(headers);
        try{
            ResponseEntity<List<OfferDto>> response = restTemplate.exchange(uri, HttpMethod.GET, requestEntity,
                    new ParameterizedTypeReference<List<OfferDto>>() {});
            final List<OfferDto> body = response.getBody();
            return (body != null) ? body : Collections.emptyList();
        } catch (ResourceAccessException e){
            return Collections.emptyList();
        }
    }
}