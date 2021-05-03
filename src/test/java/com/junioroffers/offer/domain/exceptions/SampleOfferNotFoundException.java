package com.junioroffers.offer.domain.exceptions;

import static org.junit.jupiter.api.Assertions.*;

public interface SampleOfferNotFoundException {

    default OfferNotFoundException sampleOfferNotFoundException(){
        return new OfferNotFoundException(1000L);
    }
}