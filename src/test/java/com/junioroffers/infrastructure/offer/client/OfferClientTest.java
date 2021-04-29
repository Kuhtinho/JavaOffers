package com.junioroffers.infrastructure.offer.client;

import com.junioroffers.infrastructure.RemoteOfferClient;
import com.junioroffers.infrastructure.offer.dto.OfferDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

class OfferClientTest implements SampleOfferDto, SampleRestTemplateExchangeResponse, SampleOfferResponse {

    final RestTemplate restTemplate = Mockito.mock(RestTemplate.class);

    @Test
    public void shouldReturnOneElement(){
        //given
        ResponseEntity<List<OfferDto>> responseEntity = responseWithOneOffer();
        when(getExchange(restTemplate)).thenReturn(responseEntity);
        RemoteOfferClient remoteOfferClient = new OfferClient(restTemplate, "programming-masterpiece.com:5057");

        //when
        final List<OfferDto> offers = remoteOfferClient.getOffers();

        //then
        assertThat(offers.size()).isEqualTo(1);
    }

    @Test
    public void shouldReturnTwoOffers(){
        //given
        ResponseEntity<List<OfferDto>> responseEntity = responseWithOffers(emptyOffer(),emptyOffer());
        when(getExchange(restTemplate))
                .thenReturn(responseEntity);
        RemoteOfferClient remoteOfferClient = new OfferClient(restTemplate, "programming-masterpiece.com:5057");

        //when
        final List<OfferDto> offers = remoteOfferClient.getOffers();

        //then
        assertThat(offers.size()).isEqualTo(2);
    }

    @Test
    public void shouldReturnEmptyListOfOffers(){
        //given
        ResponseEntity<List<OfferDto>> responseEntity = responseWithNoOffers();
        when(getExchange(restTemplate))
                .thenReturn(responseEntity);
        RemoteOfferClient remoteOfferClient = new OfferClient(restTemplate, "programming-masterpiece.com:5057");

        //when
        final List<OfferDto> offers = remoteOfferClient.getOffers();

        //then
        assertThat(offers.size()).isEqualTo(0);
    }
}