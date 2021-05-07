package com.junioroffers.offer;

import com.junioroffers.JobOffersApplication;
import com.junioroffers.offer.domain.dao.Offer;
import com.junioroffers.offer.domain.dao.SampleOffers;
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
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = JobOffersApplication.class)
@ActiveProfiles("container_test")
@Testcontainers
public class OfferServiceSaveAllWithContainerTests implements SampleOffers {

    @Container
    private static final MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo"));

    static {
        mongoDBContainer.start();
        System.setProperty("DB_PORT", String.valueOf(mongoDBContainer.getFirstMappedPort()));
    }

    @Test
    void should_save_all_in_database(@Autowired OfferRepository offerRepository,
                                            @Autowired OfferService offerService) {
        //GIVEN
        Offer cross = aCrosswordCyberSecurity();
        Offer sages = aSages();
        final List<Offer> expectedList = Arrays.asList(cross, sages);
        then(offerRepository.findAll()).doesNotContainAnyElementsOf(expectedList);
        //WHEN
        final List<Offer> actual = offerService.saveAll(expectedList);
        //THEN
        assertEquals(actual, expectedList);
    }
}
