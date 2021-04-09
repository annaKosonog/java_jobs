package com.junioroffers.offer.domain;

import com.junioroffers.offer.OfferRepository;
import com.junioroffers.offer.OfferService;
import com.junioroffers.offer.domain.dao.SampleOffers;
import com.junioroffers.offer.domain.dto.OfferDto;
import com.junioroffers.offer.domain.dto.SampleOffersDto;
import com.junioroffers.offer.domain.exceptions.OfferNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.OPTIONAL;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class OfferServiceTest implements SampleOffersDto, SampleOffers {


    OfferRepository offerRepository = mock(OfferRepository.class);
    OfferService offerService = new OfferService(offerRepository);

    @Test
    void should_return_all_offers() {
        //given
        when(offerRepository.findAll()).thenReturn(Arrays.asList(cyberSourceDao(), cdqPolandDao()));
        //when
        final List<OfferDto> allOffers = offerService.findAllOffers();
        //then
        assertThat(allOffers).isEqualTo(Arrays.asList(cyberSourceDto(), cdqPolandDto()));
    }

    @Test
    void should_return_find_all_offers() {
        //given
        when(offerRepository.findAll()).thenReturn(Arrays.asList(cyberSourceDao(), cdqPolandDao()));
        //when
           final List<OfferDto> allOffers = offerService.findAllOffers();
        //then
           assertThat(allOffers, hasSize(2));
    }


    @Test
    void should_return_offer_with_id_one() {
        //given
        final String  id = "7b3e02b3-6b1a-4e75-bdad-cef5b279b074";
        when(offerRepository.findById(id)).thenReturn(Optional.of(cyberSourceDao()));
        //when
            final OfferDto actual = offerService.findOfferById(id);
        //then
           assertEquals("7b3e02b3-6b1a-4e75-bdad-cef5b279b074", actual.getId());
           assertEquals("Software Engineer - Mobile (m/f/d)", actual.getCompanyName());
           assertEquals("Cybersource", actual.getPosition());
           assertEquals("4k - 8k PLN", actual.getSalary());
          assertEquals("https://nofluffjobs.com/pl/job/software-engineer-mobile-m-f-d-cybersource-poznan-entavdpn", actual.getOfferUrl());
    }

    @Test
    void should_return_offer_with_id_two() {
        //given
       final  String id = "24ee32b6-6b15-11eb-9439-0242ac130002";
       when(offerRepository.findById(id)).thenReturn(Optional.of(cdqPolandDao()));

        //when
        final OfferDto actual = offerService.findOfferById(id);
        //then
        assertEquals("24ee32b6-6b15-11eb-9439-0242ac130002", actual.getId());
        assertEquals("Junior DevOps Engineer", actual.getCompanyName());
        assertEquals("CDQ Poland", actual.getPosition());
        assertEquals("8k - 14k PLN", actual.getSalary());
        assertEquals("https://nofluffjobs.com/pl/job/junior-devops-engineer-cdq-poland-wroclaw-gnymtxqd", actual.getOfferUrl());
    }


    @Test
    void should_throws_exception_when_offer_by_id_five() {
        //given
        //when
        final String id = "100";
        when(offerRepository.findById(id)).thenReturn(Optional.empty());
        //then
        assertThrows(OfferNotFoundException.class, ()-> offerService.findOfferById(id));
    }

}
