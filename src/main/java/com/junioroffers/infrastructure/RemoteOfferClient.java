package com.junioroffers.infrastructure;

import com.junioroffers.infrastructure.offer.dto.OfferDto;

import java.util.List;

public interface RemoteOfferClient {
    List<OfferDto> getOffers();
}
