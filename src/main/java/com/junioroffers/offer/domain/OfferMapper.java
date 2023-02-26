package com.junioroffers.offer.domain;

import com.junioroffers.offer.domain.dto.OfferDto;

public class OfferMapper {

    public static OfferDto mapToOfferDto(Offer offer){
        return OfferDto.builder()
                .id(offer.getId())
                .companyName(offer.getCompanyName())
                .position(offer.getPosition())
                .salary(offer.getSalary())
                .offerUrl(offer.getOfferUrl())
                .build();
    }
}
