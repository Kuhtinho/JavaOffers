package com.junioroffers.offer.domain.exceptions;

public interface SampleOfferNotFoundException {

    default OfferNotFoundException sampleOfferNotFoundException(String id){
        return new OfferNotFoundException(id);
    }
}