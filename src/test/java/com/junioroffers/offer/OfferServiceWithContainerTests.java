package com.junioroffers.offer;

import com.junioroffers.JobOffersApplication;
import com.junioroffers.config.OfferClientConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest(classes = JobOffersApplication.class)
@Testcontainers
@ActiveProfiles("container")
public class OfferServiceWithContainerTests {

    @Container
    private static final MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo"));

    static {
        mongoDBContainer.start();
        System.setProperty("DB_PORT", String.valueOf(mongoDBContainer.getFirstMappedPort()));
    }

    @Test
    void should_unsuccessfully_verify_a_product_when_previously_verified(){

    }

    @Configuration(proxyBeanMethods = false)
    @EnableAutoConfiguration
    static class Config extends OfferClientConfig {

    }

}
