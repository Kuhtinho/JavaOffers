package com.junioroffers.offer.domain.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
@EqualsAndHashCode
public class OfferDto {

    UUID uuid;
    String companyName;
    String position;
    String salary;
    String offerUrl;
}
