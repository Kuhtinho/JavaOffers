package com.junioroffers.infrastructure.offer.client;

import com.junioroffers.infrastructure.offer.dto.OfferDto;

public interface SampleOfferDto {

    default OfferDto emptyOffer(){
        return new OfferDto();
    }

    default OfferDto offerWithParameters(String id, String salary, String url, String company){
        return OfferDto.builder()
                .id(id)
                .salary(salary)
                .offerUrl(url)
                .company(company)
                .build();
    }
}
