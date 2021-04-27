package com.junioroffers.infrastructure.offer.dto;

import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class OfferDto {
    String id;
    String company;
    String salary;
    String offerUrl;
}
