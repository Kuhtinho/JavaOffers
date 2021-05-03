package com.junioroffers.offer.domain.exceptions;

import lombok.Getter;

@Getter
public class OfferNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 6378244667204645097L;
    private final long offerId;

    public OfferNotFoundException(long offerId){
        super(String.format("Offer with id %d not found", offerId));
        this.offerId = offerId;
    }

}
