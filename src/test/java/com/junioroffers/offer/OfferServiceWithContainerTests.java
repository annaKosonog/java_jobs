package com.junioroffers.offer;

import com.junioroffers.JobOffersApplication;
import com.junioroffers.offer.domain.dao.Offer;
import com.junioroffers.offer.domain.dao.SampleOffers;
import com.junioroffers.offer.domain.dto.OfferDto;
import com.junioroffers.offer.domain.dto.SampleOffersDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;


@SpringBootTest(classes = JobOffersApplication.class)
@Testcontainers
@ActiveProfiles("container")
public class OfferServiceWithContainerTests implements SampleOffers, SampleOffersDto {

    @Container
    private static final MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo"));

    static {
        mongoDBContainer.start();
        System.setProperty("DB_PORT", String.valueOf(mongoDBContainer.getFirstMappedPort()));
    }

    @Test
    void should_find_all_offers_by_database(@Autowired OfferRepository offerRepository,
                                            @Autowired OfferService offerService) {
        // GIVEN
        Offer cyber = cyberSourceDao();
        Offer cdq = cdqPolandDao();
        then(offerRepository.findAll()).containsAll(Arrays.asList(cyber, cdq));
        //WHEN
        final List<OfferDto> actualOffers = offerService.findAllOffers();
        //THEN
        assertSame(actualOffers, Arrays.asList(cyberSourceDto(), cdqPolandDto()));
    }

    @Test
    void should_return_offer_from_the_database_after_the_id(@Autowired OfferRepository offerRepository,
                                                            @Autowired OfferService offerService) {
        //GIVEN
        final String ID = "7b3e02b3-6b1a-4e75-bdad-cef5b279b074";
        then(offerRepository.findById(ID)).isPresent();
        //WHEN
        final OfferDto actual = offerService.findOfferById(ID);
        //THEN
        assertEquals(actual, cyberSourceDto());
    }


}
