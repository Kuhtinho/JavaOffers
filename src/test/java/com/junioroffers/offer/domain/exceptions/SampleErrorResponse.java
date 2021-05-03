package com.junioroffers.offer.domain.exceptions;

import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

public interface SampleErrorResponse {

    default OfferErrorResponse sampleOfferErrorResponse(){
        return new OfferErrorResponse("Offer with id 1000 not found", HttpStatus.NOT_FOUND);
    }
}