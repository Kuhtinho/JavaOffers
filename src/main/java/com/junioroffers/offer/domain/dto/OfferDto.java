package com.junioroffers.offer.domain.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class OfferDto {

    private final String id;
    private final String companyName;
    private final String position;
    private final String salary;
    private final String offerUrl;
}
