package com.junioroffers.offer;

import com.junioroffers.JobOffersApplication;
import com.junioroffers.offer.domain.dao.Offer;
import com.junioroffers.offer.domain.dao.SampleOffers;
import com.junioroffers.offer.domain.dto.OfferDto;
import com.junioroffers.offer.domain.exceptions.api.response.OfferExistUrlException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(classes = OfferServiceAddOffersWithContainerTest.TestConfig.class)
@Testcontainers
@ActiveProfiles("container_test")
public class OfferServiceAddOffersWithContainerTest implements SampleOffers {


    @Container
    private static final MongoDBContainer mongoDbContainer = new MongoDBContainer("mongo:4.2")
            .withExposedPorts(27017);

    static {
        mongoDbContainer.start();
        System.setProperty("DB_PORT", String.valueOf(mongoDbContainer.getFirstMappedPort()));
    }

    @Test
    void should_save_offers_in_database_when_url_is_unique(@Autowired OfferRepository offerRepository,
                                                           @Autowired OfferService offerService) {
        //GIVEN
        final Offer offerInTheDb = objectParametersWithoutId("Skarbek",
                "Game",
                "much",
                "exist_url");
        offerRepository.save(offerInTheDb);

        final OfferDto checkOfferDto = aFirstCompany();
        then(offerRepository.existsByOfferUrl("exist_url")).isTrue();

        //WHEN
        final OfferDto can_be_save = offerService.addOffers(checkOfferDto);

        //THEN
        assertThat(offerRepository.findById("609d32365c62d131949856f6").isPresent());
        assertThat(checkOfferDto.getOfferUrl()).isEqualTo(can_be_save.getOfferUrl());
        assertThat(offerRepository.existsByOfferUrl("unique_url")).isTrue();

    }

    @Test
    void should_thrown_an_exception_when_we_want_to_save_a_duplicate_url(@Autowired OfferRepository offerRepository,

                                                                         @Autowired OfferService offerService) {
        //GIVEN
        final OfferDto checkOfferDto = allParametersWhereHaveNotId("Juthisho",
                "Game position",
                "much",
                "no_unique_url");
        then(offerRepository.existsByOfferUrl(checkOfferDto.getOfferUrl())).isFalse();
        final OfferDto saveOfferDtoToDb = offerService.addOffers(checkOfferDto);

        final OfferDto noSave = allParametersWhereHaveNotId("Juthisho",
                "Game position",
                "much",
                "no_unique_url");

        //WHEN
        Throwable thrown = catchThrowable(() -> offerService.addOffers(noSave));

        //THEN
        assertThat(thrown).isInstanceOf(OfferExistUrlException.class)
                .hasMessage("The given url already exists in the database: " + "\n" + noSave.getOfferUrl());
        assertThat(offerRepository.existsByOfferUrl("no_unique_url")).isTrue();

    }


    @Import(JobOffersApplication.class)
    static class TestConfig {

    }
}

