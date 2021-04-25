package com.junioroffers.offer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
@ActiveProfiles("container")
@Testcontainers
public class OfferServiceSaveAllOffersWithContainerTest {
    @Container
    private static final MongoDBContainer mongoContainer = new MongoDBContainer(DockerImageName.parse("mongo"));

    static {
        mongoContainer.start();
        System.setProperty("DB_PORT", String.valueOf(mongoContainer.getFirstMappedPort()));
    }

    @Test
    void should_return_save_all_offers_in_database(@Autowired OfferRepository offerRepository,
                                                   @Autowired OfferService offerService){

    }

}
