package com.junioroffers.offer.domain.exceptions;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class OfferControllerErrorHandlerTest implements SampleErrorResponse, SampleOfferNotFoundException{

    @Test
    public void shouldReturnCorrectErrorResponse(){
        OfferControllerErrorHandler offerControllerErrorHandler = new OfferControllerErrorHandler();
        final OfferNotFoundException givenException= sampleOfferNotFoundException("1000");
        final OfferErrorResponse expectedResponse = sampleOfferErrorResponse();

        final OfferErrorResponse actualResponse = offerControllerErrorHandler.offerNotFound(givenException);

        assertThat(actualResponse).isEqualTo(expectedResponse);
    }
}