package com.junioroffers.offer.domain;

import com.junioroffers.offer.OfferService;
import com.junioroffers.offer.domain.dto.OfferDto;
import com.junioroffers.offer.domain.dto.SampleOffersDto;
import com.junioroffers.offer.domain.exceptions.OfferNotFoundException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//public class OfferServiceTest implements SampleOffersDto {
//
//    final List<OfferDto> expectedList() {
//        return Arrays.asList(cyberSource(), cdqPolandOffer());
//    }
//
//    @Test
//    void should_return_all_offers() {
//        //given
//        OfferService offerService = new OfferService();
//        //when
//        final List<OfferDto> allOffers = offerService.getOffers();
//        //then
//        assertThat(allOffers).isEqualTo(expectedList());
//    }
//
//    @Test
//    void should_return_find_all_offers() {
//        //given
//        OfferService offerService = new OfferService();
//        //when
//        final List<OfferDto> allOffers = offerService.getOffers();
//        //then
//        assertThat(allOffers, hasSize(2));
//    }
//
//    @Test
//    void should_return_offer_with_id_one() {
//        //given
//        long id = 1L;
//        OfferService offerService = new OfferService();
//        //when
//        final OfferDto actual = offerService.getOfferById(id);
//        //then
//        assertEquals("7b3e02b3-6b1a-4e75-bdad-cef5b279b074", actual.getId().toString());
//        assertEquals("Software Engineer - Mobile (m/f/d)", actual.getPosition());
//        assertEquals("Cybersource", actual.getCompanyName());
//        assertEquals("4k - 8k PLN", actual.getSalary());
//        assertEquals("https://nofluffjobs.com/pl/job/software-engineer-mobile-m-f-d-cybersource-poznan-entavdpn", actual.getOfferUrl());
//    }
//
//    @Test
//    void should_return_offer_with_id_two() {
//        //given
//        long id = 2L;
//        OfferService offerService = new OfferService();
//        //when
//        final OfferDto actual = offerService.getOfferById(id);
//        //then
//        assertEquals("24ee32b6-6b15-11eb-9439-0242ac130002", actual.getId().toString());
//        assertEquals("Junior DevOps Engineer", actual.getPosition());
//        assertEquals("CDQ Poland", actual.getCompanyName());
//        assertEquals("8k - 14k PLN", actual.getSalary());
//        assertEquals("https://nofluffjobs.com/pl/job/junior-devops-engineer-cdq-poland-wroclaw-gnymtxqd", actual.getOfferUrl());
//    }
//
//
//    @Test
//    void should_throws_exception_when_offer_by_id_five() {
//        //given
//        //when
//        final long id = 5L;
//        OfferService offerService = new OfferService();
//        //then
//        assertThrows(OfferNotFoundException.class, () -> offerService.getOfferById(id));
//    }
//}
