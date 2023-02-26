package com.junioroffers.offer.domain.dto;

import com.junioroffers.offer.domain.SampleOffer;


public interface SampleOfferDto extends SampleOffer {

    default OfferDto cdqPolandOfferDto(){
        return OfferMapper.mapToOfferDto(cdqPolandOffer());
    }

    default OfferDto cybersourceOfferDto(){
        return OfferMapper.mapToOfferDto(cybersourceOffer());
    }
}