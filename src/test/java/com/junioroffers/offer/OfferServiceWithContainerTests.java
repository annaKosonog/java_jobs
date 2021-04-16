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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.then;


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
    void should_find_all_offers_by_database(@Autowired OfferRepository offerRepository, @Autowired OfferService offerService) {
        // GIVEN
        final Offer cyberSource = cyberSourceDao();
        final Offer cdqPolandDao = cdqPolandDao();
        then(offerRepository.findAll()).isEqualTo(Arrays.asList(cyberSource, cdqPolandDao));
        //WHEN
        final List<OfferDto> actualOffers = offerService.findAllOffers();
        //THEN
        assertThat(actualOffers).isEqualTo(Arrays.asList(cyberSourceDto(), cdqPolandDto()));
    }
}
