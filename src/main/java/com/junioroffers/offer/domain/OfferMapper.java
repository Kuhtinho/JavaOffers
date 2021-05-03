package com.junioroffers.offer.domain;

import com.junioroffers.offer.domain.dto.OfferDto;

import java.util.UUID;

public class OfferMapper {

    public static OfferDto mapToOfferDto(UUID uuid, String companyName, String position, String salary, String offerUrl){
        return OfferDto.builder()
                .companyName(companyName)
                .position(position)
                .salary(salary)
                .offerUrl(offerUrl)
                .build();
    }
}
