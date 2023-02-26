package com.junioroffers.offer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.junioroffers.offer.domain.OfferRepository;
import com.junioroffers.offer.domain.OfferService;
import com.junioroffers.offer.domain.dto.OfferDto;
import com.junioroffers.offer.domain.dto.SampleOfferDto;
import com.junioroffers.offer.domain.exceptions.OfferControllerErrorHandler;
import com.junioroffers.offer.domain.exceptions.OfferErrorResponse;
import com.junioroffers.offer.domain.exceptions.SampleOfferNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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
        final List<OfferDto> expectedOfferDtos = Arrays.asList(cybersourceOfferDto(), cdqPolandOfferDto());
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

        final MvcResult mvcResult = mockMvc.perform(get("/offers/2"))
                .andExpect(status().isOk())
                .andReturn();
        String actualResponseBody = mvcResult.getResponse().getContentAsString();
        assertThat(actualResponseBody).isEqualToIgnoringWhitespace(expectedResponseBody);
    }

    @Test
    public void shouldReturnStatusNotFoundWhenGetForOfferWithNotFoundId(@Autowired MockMvc mockMvc, @Autowired ObjectMapper objectMapper) throws Exception{
        OfferErrorResponse offerErrorResponse = new OfferErrorResponse("Offer with id 555 not found", HttpStatus.NOT_FOUND);
        String expectedResponseBody = objectMapper.writeValueAsString(offerErrorResponse);

        final MvcResult mvcResult = mockMvc.perform(get("/offers/555"))
                .andExpect(status().isNotFound())
                .andReturn();
        String actualResponseBody = mvcResult.getResponse().getContentAsString();
        assertThat(actualResponseBody).isEqualToIgnoringWhitespace(expectedResponseBody);
    }

}

@Configuration(proxyBeanMethods = false)
class MockMvcConfig implements SampleOfferDto, SampleOfferNotFoundException {

    @Bean
    OfferControllerErrorHandler offerControllerErrorHandler(){
        return new OfferControllerErrorHandler();
    }

    @Bean
    OfferService offerService(){
        OfferRepository offerRepository = Mockito.mock(OfferRepository.class);
        return new OfferService(offerRepository){
            @Override
            public List<OfferDto> findAllOffers() {
                return Arrays.asList(cybersourceOfferDto(), cdqPolandOfferDto());
            }

            @Override
            public OfferDto findOfferById(String id) {
                if("24ee32b6-6b15-11eb-9439-0242ac130002".equals(id)){
                    return cdqPolandOfferDto();
                } else if("7b3e02b3-6b1a-4e75-bdad-cef5b279b074".equals(id)){
                    return cybersourceOfferDto();
                }
                throw sampleOfferNotFoundException(id);
            }
        };
    }

    @Bean
    OfferController offerController(OfferService offerService){
        return new OfferController(offerService);
    }
}