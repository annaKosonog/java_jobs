package com.junioroffers.offer.domain;

import com.junioroffers.offer.OfferRepository;
import com.junioroffers.offer.OfferService;
import com.junioroffers.offer.domain.dao.SampleOffers;
import com.junioroffers.offer.domain.dto.OfferDto;
import com.junioroffers.offer.domain.dto.SampleOffersDto;
import com.junioroffers.offer.domain.exceptions.api.response.OfferNotFoundException;
import com.junioroffers.offer.domain.mappers.OfferMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(value = MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class OfferServiceTest implements SampleOffers, SampleOffersDto {


    OfferRepository offerRepository = mock(OfferRepository.class);
    OfferMapper offerMapper = mock(OfferMapper.class);
    OfferService offerService = new OfferService(offerRepository, offerMapper);

    @Test
    void should_return_all_offers() {
        //GIVEN
        when(offerRepository.findAll()).thenReturn(Arrays.asList(cyberSourceDao(), cdqPolandDao()));
        //WHEN
        final List<OfferDto> allOffers = offerService.findAllOffers();
        //THEN
        assertThat(allOffers).isEqualTo(Arrays.asList(cyberSourceDto(), cdqPolandDto()));
    }

    @Test
    void should_return_find_all_offers() {
        //GIVEN
        when(offerRepository.findAll()).thenReturn(Arrays.asList(cyberSourceDao(), cdqPolandDao()));
        //WHEN
        final List<OfferDto> allOffers = offerService.findAllOffers();
        //THEN
        assertThat(allOffers, hasSize(2));
    }


    @Test
    void should_return_offer_with_id_one() {
        //GIVEN
        final String id = "7b3e02b3-6b1a-4e75-bdad-cef5b279b074";
        when(offerRepository.findById(id)).thenReturn(Optional.of(cyberSourceDao()));
        when(offerMapper.mapToOfferDto(cyberSourceDao())).thenReturn(cyberSourceDto());
        //WHEN
        final OfferDto actual = offerService.findOfferById(id);
        //THEN
        assertEquals("7b3e02b3-6b1a-4e75-bdad-cef5b279b074", actual.getId());
        assertEquals("Software Engineer - Mobile (m/f/d)", actual.getPosition());
        assertEquals("Cybersource", actual.getCompanyName());
        assertEquals("4k - 8k PLN", actual.getSalary());
        assertEquals("https://nofluffjobs.com/pl/job/software-engineer-mobile-m-f-d-cybersource-poznan-entavdpn", actual.getOfferUrl());
    }

    @Test
    void should_return_offer_with_id_two() {
        //GIVEN
        final String id = "24ee32b6-6b15-11eb-9439-0242ac130002";
        when(offerRepository.findById(id)).thenReturn(Optional.of(cdqPolandDao()));
        when(offerMapper.mapToOfferDto(cdqPolandDao())).thenReturn(cdqPolandDto());
        //WHEN
        final OfferDto actual = offerService.findOfferById(id);
        //THEN
        assertEquals("24ee32b6-6b15-11eb-9439-0242ac130002", actual.getId());
        assertEquals("Junior DevOps Engineer", actual.getPosition());
        assertEquals("CDQ Poland", actual.getCompanyName());
        assertEquals("8k - 14k PLN", actual.getSalary());
        assertEquals("https://nofluffjobs.com/pl/job/junior-devops-engineer-cdq-poland-wroclaw-gnymtxqd", actual.getOfferUrl());
    }


    @Test
    void should_throws_exception_when_offer_by_id_five() {
        //GIVEN
        //WHEN
        final String id = "100";
        when(offerRepository.findById(id)).thenReturn(Optional.empty());
        //THEN
        assertThrows(OfferNotFoundException.class, () -> offerService.findOfferById(id));
    }

    @Test
    void should_add_an_offer_to_the_database() {
        //GIVEN
        when(offerMapper.mapFromOffer(aFirstCompanyDto())).thenReturn(aFirstCompanyWithoutId());
        when(offerRepository.save(aFirstCompanyWithoutId())).thenReturn(aFirstCompany());
        when(offerMapper.mapToOfferDto(aFirstCompany())).thenReturn(aFirstCompanyDto());
        // WHEN
        final OfferDto actual = offerService.addOffers(aFirstCompanyDto());
        //THEN
        assertThat(actual).isEqualTo(aFirstCompanyDto());
        verify(offerRepository, times(1)).save(any());
    }
}
