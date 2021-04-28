package com.junioroffers.offer;

import com.junioroffers.JobOffersApplication;
import com.junioroffers.infrastracture.model.dto.JobOfferDto;
import com.junioroffers.infrastracture.service.offer.client.dto.SampleJobOfferDto;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(classes = JobOffersApplication.class)
@ActiveProfiles("container")
@Testcontainers
public class OfferServiceSaveAllOffersWithContainerTest implements SampleOffers, SampleJobOfferDto {
    @Container
    private static final MongoDBContainer mongoContainer = new MongoDBContainer(DockerImageName.parse("mongo"));

    static {
        mongoContainer.start();
        System.setProperty("DB_PORT", String.valueOf(mongoContainer.getFirstMappedPort()));
    }

    @Test
    void should_return_save_all_offers_in_database(@Autowired OfferRepository offerRepository,
                                                   @Autowired OfferService offerService) {

        //GIVEN
        final JobOfferDto specialUrlOne = aJobOfferDto("special url",
                "Two turkey",
                "One company",
                "a lot of");

        final JobOfferDto specialUrlTwo = aJobOfferDto("exists_url",
                "Game night",
                "Two company",
                "much");

        final Offer existsWithDatabase = objectParametersWithoutId(
                "Three company",
                "junior test",
                "343232",
                "exists_url");
        offerRepository.save(existsWithDatabase);
        then(offerRepository.existsByOfferUrl(existsWithDatabase.getOfferUrl())).isTrue();

        //WHEN
        final List<Offer> savedOffersList = offerService.saveAllOffers(Arrays.asList(specialUrlOne, specialUrlTwo));
        //WHEN
        assertThat(savedOffersList.size()).isEqualTo(1);
        assertThat(offerRepository.existsByOfferUrl("special url")).isTrue();

    }

}
