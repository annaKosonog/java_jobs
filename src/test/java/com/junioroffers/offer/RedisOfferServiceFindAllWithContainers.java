package com.junioroffers.offer;

import com.junioroffers.JobOffersApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TEN_SECONDS;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = JobOffersApplication.class)
@Testcontainers
@ActiveProfiles("redis_test")
public class RedisOfferServiceFindAllWithContainers {

    @MockBean
    OfferRepository offerRepository;
    @Container
    private static final GenericContainer redis;

    static {
        redis = new GenericContainer(DockerImageName.parse("redis:5.0.5"))
                .withExposedPorts(6379);
        redis.start();
        System.setProperty("spring.redis.port", String.valueOf(redis.getFirstMappedPort()));
    }

    @Test
    void checks_if_the_method_called_twice_uses_the_cached_values(@Autowired CacheManager cacheManager,
                                                                  @Autowired OfferService offerService) {
        final String expectedCacheManager = "jobs";

        assertThat(cacheManager.getCacheNames().isEmpty()).isTrue();

        offerService.findAllOffers();
        offerService.findAllOffers();

        assertThat(cacheManager.getCacheNames().contains(expectedCacheManager)).isTrue();
        verify(offerRepository, times(1)).findAll();
    }

    @Test
    void check_if_the_cache_expires_correctly_after_the_set_time_to_live_value_has_expired(@Autowired CacheManager cacheManager,
                                                                                           @Autowired OfferService offerService) {
        final String expectedCacheManager = "jobs";
        offerService.findAllOffers();
        assertThat(cacheManager.getCacheNames().contains(expectedCacheManager)).isTrue();
        await()
                .atMost(TEN_SECONDS)
                .untilAsserted(() -> {
                            offerService.findAllOffers();
                            verify(offerRepository, times(2)).findAll();

                        }
                );
    }
}

