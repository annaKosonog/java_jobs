//package com.junioroffers.offer.domain;
//
//import com.junioroffers.offer.OfferRepository;
//import com.junioroffers.offer.OfferService;
//import com.junioroffers.offer.domain.dao.Offer;
//import com.junioroffers.offer.domain.dao.SampleOffers;
//import com.junioroffers.offer.domain.dto.OfferDto;
//import com.junioroffers.offer.domain.dto.SampleOffersDto;
//import com.junioroffers.offer.domain.exceptions.api.response.OfferNotFoundException;
//import com.junioroffers.offer.domain.mappers.OfferMapper;
//import org.junit.jupiter.api.Test;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.hasSize;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//public class OfferServiceTest implements SampleOffersDto, SampleOffers {
//
//
//    OfferRepository offerRepository = mock(OfferRepository.class);
//    OfferService offerService = new OfferService(offerRepository);
//
//    @Test
//    void should_return_all_offers() {
//        //GIVEN
//        when(offerRepository.findAll()).thenReturn(Arrays.asList(cyberSourceDao(), cdqPolandDao()));
//        //WHEN
//        final List<OfferDto> allOffers = offerService.findAllOffers();
//        //THEN
//        assertThat(allOffers).isEqualTo(Arrays.asList(cyberSourceDtoMapper(), cdqPolandDtoMapper()));
//    }
//
//    @Test
//    void should_return_find_all_offers() {
//        //GIVEN
//        when(offerRepository.findAll()).thenReturn(Arrays.asList(cyberSourceDao(), cdqPolandDao()));
//        //WHEN
//        final List<OfferDto> allOffers = offerService.findAllOffers();
//        //THEN
//        assertThat(allOffers, hasSize(2));
//    }
//
//
//    @Test
//    void should_return_offer_with_id_one() {
//        //GIVEN
//        final String id = "7b3e02b3-6b1a-4e75-bdad-cef5b279b074";
//        when(offerRepository.findById(id)).thenReturn(Optional.of(cyberSourceDao()));
//        //WHEN
//        final OfferDto actual = offerService.findOfferById(id);
//        //THEN
//        assertEquals("7b3e02b3-6b1a-4e75-bdad-cef5b279b074", actual.getId());
//        assertEquals("Software Engineer - Mobile (m/f/d)", actual.getPosition());
//        assertEquals("Cybersource", actual.getCompanyName());
//        assertEquals("4k - 8k PLN", actual.getSalary());
//        assertEquals("https://nofluffjobs.com/pl/job/software-engineer-mobile-m-f-d-cybersource-poznan-entavdpn", actual.getOfferUrl());
//    }
//
//    @Test
//    void should_return_offer_with_id_two() {
//        //GIVEN
//        final String id = "24ee32b6-6b15-11eb-9439-0242ac130002";
//        when(offerRepository.findById(id)).thenReturn(Optional.of(cdqPolandDao()));
//        //WHEN
//        final OfferDto actual = offerService.findOfferById(id);
//        //THEN
//        assertEquals("24ee32b6-6b15-11eb-9439-0242ac130002", actual.getId());
//        assertEquals("Junior DevOps Engineer", actual.getPosition());
//        assertEquals("CDQ Poland", actual.getCompanyName());
//        assertEquals("8k - 14k PLN", actual.getSalary());
//        assertEquals("https://nofluffjobs.com/pl/job/junior-devops-engineer-cdq-poland-wroclaw-gnymtxqd", actual.getOfferUrl());
//    }
//
//
//    @Test
//    void should_throws_exception_when_offer_by_id_five() {
//        //GIVEN
//        //WHEN
//        final String id = "100";
//        when(offerRepository.findById(id)).thenReturn(Optional.empty());
//        //THEN
//        assertThrows(OfferNotFoundException.class, () -> offerService.findOfferById(id));
//    }
//
//    @Test
//    void should_return_correctly_parsed_object() {
//        //GIVEN
//        final OfferDto expectedResponse = cyberSourceDtoWithoutId();
//        //WHEN
//        final Offer fromOffer = OfferMapper.mapFromOffer(expectedResponse);
//        final OfferDto actualResponse = OfferMapper.mapToOfferDto(fromOffer);
//        //THEN
//        assertThat(actualResponse).isEqualTo(expectedResponse);
//    }
//
//    @Test
//    void should_add_an_offer_to_the_database() {
//        //GIVEN
//        cyberSourceDtoMapper();
//        when(offerRepository.existsByOfferUrl(expected.getOfferUrl())).thenReturn(false);
//        // WHEN
//        final OfferDto actual = offerService.addOffers(expected);
//        //THEN
//        assertThat(actual).isEqualTo(expected);
//
//    }
//
//    @Test
//    void should_not_offer_to_add_to_the_database_when_the_url_already_exists() {
//        //GIVEN
////        final Offer saveDb = aFirstCompanyWithId();
//        //WHEN
//        //THEN
//
//    }
//}
