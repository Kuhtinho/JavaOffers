package com.junioroffers.offer.domain;

import com.junioroffers.offer.domain.dto.OfferDto;
import com.junioroffers.offer.domain.dto.SampleOfferDto;
import com.junioroffers.offer.domain.exceptions.OfferNotFoundException;
import com.junioroffers.offer.domain.service.OfferService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.mockito.Mockito.when;

class OfferServiceTest implements SampleOfferDto, SampleOffer {

    OfferRepository offerRepository = Mockito.mock(OfferRepository.class);

    @Test
    public void shouldReturnTwoOffers(){
        when(offerRepository.findAll()).thenReturn(Arrays.asList(cybersourceOffer(),cdqPolandOffer()));
        assertThat(offerRepository.findAll()).isEqualTo(Arrays.asList(cybersourceOffer(), cdqPolandOffer()));

        OfferService offerService = new OfferService(offerRepository);
        final List<OfferDto> expectedOffers = Arrays.asList(cybersourceOfferDto(), cdqPolandOfferDto());

        final List<OfferDto> allOffers = offerService.findAllOffers();

        then(allOffers).containsExactlyInAnyOrderElementsOf(expectedOffers);
    }

    @Test
    public void shouldReturnCorrectOfferForGivenIdTwo(){
        when(offerRepository.findById("7b3e02b3-6b1a-4e75-bdad-cef5b279b074")).
                thenReturn(Optional.of(cybersourceOffer()));
        assertThat(offerRepository.findById("7b3e02b3-6b1a-4e75-bdad-cef5b279b074")).
                isEqualTo(Optional.of(cybersourceOffer()));

        OfferService offerService = new OfferService(offerRepository);

        final OfferDto expectedOffer = offerService.
                findOfferById("7b3e02b3-6b1a-4e75-bdad-cef5b279b074");

        then(expectedOffer).isEqualTo(cybersourceOffer());
    }

    @Test
    public void shouldReturnCorrectOfferForGivenIdOne(){
        when(offerRepository.findById("24ee32b6-6b15-11eb-9439-0242ac130002")).
                thenReturn(Optional.of(cdqPolandOffer()));
        assertThat(offerRepository.findById("24ee32b6-6b15-11eb-9439-0242ac130002")).
                isEqualTo(Optional.of(cdqPolandOffer()));

        OfferService offerService = new OfferService(offerRepository);

        final OfferDto offerById = offerService.
                findOfferById("24ee32b6-6b15-11eb-9439-0242ac130002");

        then(offerById).isEqualTo(cdqPolandOfferDto());
    }

    @Test
    public void shouldThrowOfferNotFoundExceptionWhenNoOfferWithGivenId(){
        when(offerRepository.findById("1000")).thenReturn(Optional.empty());
        assertThat(offerRepository.findById("1000")).isEqualTo(Optional.empty());

        OfferService offerService = new OfferService(offerRepository);

        Throwable thrown = catchThrowable(() -> offerService.findOfferById("1000"));

        assertThat(thrown)
                .isInstanceOf(OfferNotFoundException.class)
                .hasMessage("Offer with id 1000 not found");
    }
}