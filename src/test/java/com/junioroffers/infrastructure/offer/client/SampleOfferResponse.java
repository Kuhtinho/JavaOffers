package com.junioroffers.infrastructure.offer.client;

import com.junioroffers.infrastructure.offer.dto.OfferDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public interface SampleOfferResponse extends SampleOfferDto{

    default ResponseEntity<List<OfferDto>> responseWithOneOffer(){
        return new ResponseEntity<>(Collections.singletonList(emptyOffer()), HttpStatus.ACCEPTED);
    }

    default ResponseEntity<List<OfferDto>> responseWithNoOffers(){
        return new ResponseEntity<>(Collections.emptyList(), HttpStatus.ACCEPTED);
    }

    default ResponseEntity<List<OfferDto>> responseWithOffers(OfferDto... offerDtos){
        return new ResponseEntity<>(Arrays.asList(offerDtos), HttpStatus.ACCEPTED);
    }
}
