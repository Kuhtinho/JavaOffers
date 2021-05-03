package com.junioroffers.offer.domain;

import com.junioroffers.offer.domain.dto.OfferDto;
import com.junioroffers.offer.domain.exceptions.OfferNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class OfferService {

    public List<OfferDto> findAllOffers(){
        final OfferDto cybersourceDto = OfferMapper.mapToOfferDto(
                UUID.fromString("7b3e02b3-6b1a-4e75-bdad-cef5b279b074"),
                "Software Engineer - Mobile(m/f/d)",
                "4k - 8k PLN",
                "https://nofluffjobs.com/pl/job/software-engineer-mobile-m-f-d-cybersource-poznan-entavdpn",
                "Cybersource"
        );

        final OfferDto cdqPolandDto = OfferMapper.mapToOfferDto(
                UUID.fromString("24ee32b6-6b15-11eb-9439-0242ac130002"),
                "Junior DevOps Engineer",
                "8k - 14k PLN",
                "https://nofluffjobs.com/pl/job/junior-devops-engineer-cdq-poland-wroclaw-gnymtxqd",
                "CDQ Poland"
        );

        return Arrays.asList(cybersourceDto, cdqPolandDto);
    }

    public OfferDto findOfferById(long id){
        if(id == 1L){
            return OfferMapper.mapToOfferDto(
                    UUID.fromString("7b3e02b3-6b1a-4e75-bdad-cef5b279b074"),
                    "Software Engineer - Mobile(m/f/d)",
                    "4k - 8k PLN",
                    "https://nofluffjobs.com/pl/job/software-engineer-mobile-m-f-d-cybersource-poznan-entavdpn",
                    "Cybersource"
            );
        }
        else if(id == 2L){
            return OfferMapper.mapToOfferDto(UUID.fromString("24ee32b6-6b15-11eb-9439-0242ac130002"),
                    "Junior DevOps Engineer",
                    "8k - 14k PLN",
                    "https://nofluffjobs.com/pl/job/junior-devops-engineer-cdq-poland-wroclaw-gnymtxqd",
                    "CDQ Poland"
            );
        }
        throw new OfferNotFoundException(id);
    }
}
