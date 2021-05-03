package com.junioroffers.offer.domain;

import com.junioroffers.offer.domain.dto.OfferDto;
import com.junioroffers.offer.domain.dto.SampleOfferDto;
import com.junioroffers.offer.domain.exceptions.OfferNotFoundException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

class OfferServiceTest implements SampleOfferDto {

    @Test
    public void shouldReturnTwoOffers(){
        OfferService offerService = new OfferService();
        final List<OfferDto> expectedOffers = Arrays.asList(cdqPolandOffer(), cybersourceOffer());

        final List<OfferDto> allOffers = offerService.findAllOffers();

        then(allOffers).containsExactlyInAnyOrderElementsOf(expectedOffers);
    }

    @Test
    public void shouldReturnCorrectOfferForGivenIdTwo(){
        OfferService offerService = new OfferService();

        final OfferDto expectedOffer = offerService.findOfferById(2L);

        then(expectedOffer).isEqualTo(cybersourceOffer());
    }

    @Test
    public void shouldReturnCorrectOfferForGivenIdOne(){
        OfferService offerService = new OfferService();

        final OfferDto expectedOffer = offerService.findOfferById(1L);

        then(expectedOffer).isEqualTo(cdqPolandOffer());
    }

    @Test
    public void shouldThrowOfferNotFoundExceptionWhenNoOfferWithGivenId(){
        OfferService offerService = new OfferService();

        Throwable thrown = catchThrowable(() -> offerService.findOfferById(1000));

        assertThat(thrown)
                .isInstanceOf(OfferNotFoundException.class)
                .hasMessage("Offer with id 1000 not found");
    }
}