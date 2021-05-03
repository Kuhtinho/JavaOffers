package com.junioroffers.offer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.junioroffers.offer.domain.OfferService;
import com.junioroffers.offer.domain.dto.OfferDto;
import com.junioroffers.offer.domain.dto.SampleOfferDto;
import com.junioroffers.offer.domain.exceptions.OfferControllerErrorHandler;
import com.junioroffers.offer.domain.exceptions.OfferErrorResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ContextConfiguration(classes = MockMvcConfig.class)
class OfferControllerTest implements SampleOfferDto {

    @Test
    public void shouldReturnStatusOkWhenGetForOffers(@Autowired MockMvc mockMvc, @Autowired ObjectMapper objectMapper) throws Exception{
        final List<OfferDto> expectedOfferDtos = Arrays.asList(cybersourceOffer(), cdqPolandOffer());
        String expectedResponseBody = objectMapper.writeValueAsString(expectedOfferDtos);

        final MvcResult mvcResult = mockMvc.perform(get("/offers"))
                .andExpect(status().isOk())
                .andReturn();
        String actualResponseBody = mvcResult.getResponse().getContentAsString();
        assertThat(actualResponseBody).isEqualToIgnoringWhitespace(expectedResponseBody);
    }

    @Test
    public void shouldReturnStatusOkWhenGetForOfferWithId(@Autowired MockMvc mockMvc, @Autowired ObjectMapper objectMapper) throws Exception{
        String expectedResponseBody = objectMapper.writeValueAsString(cybersourceOffer());

        final MvcResult mvcResult = mockMvc.perform(get("/offers"))
                .andExpect(status().isOk())
                .andReturn();
        String actualResponseBody = mvcResult.getResponse().getContentAsString();
        assertThat(actualResponseBody).isEqualToIgnoringWhitespace(expectedResponseBody);
    }

    @Test
    public void shouldReturnStatusNotFoundWhenGetForOfferWithNotFoundId(@Autowired MockMvc mockMvc, @Autowired ObjectMapper objectMapper) throws Exception{
        OfferErrorResponse offerErrorResponse = new OfferErrorResponse("Offer with id 555 not found", HttpStatus.NOT_FOUND);
        String expectedResponseBody = objectMapper.writeValueAsString(cybersourceOffer());

        final MvcResult mvcResult = mockMvc.perform(get("/offers/555"))
                .andExpect(status().isNotFound())
                .andReturn();
        String actualResponseBody = mvcResult.getResponse().getContentAsString();
        assertThat(actualResponseBody).isEqualToIgnoringWhitespace(expectedResponseBody);
    }

}

@Configuration(proxyBeanMethods = false)
class MockMvcConfig implements SampleOfferDto {

    @Bean
    OfferControllerErrorHandler offerControllerErrorHandler(){
        return new OfferControllerErrorHandler();
    }

    @Bean
    OfferService offerService(){
        return new OfferService(){
            @Override
            public List<OfferDto> findAllOffers() {
                return Arrays.asList(cybersourceOffer(), cdqPolandOffer());
            }
        };
    }

    @Bean
    OfferController offerController(OfferService offerService){
        return new OfferController(offerService);
    }
}