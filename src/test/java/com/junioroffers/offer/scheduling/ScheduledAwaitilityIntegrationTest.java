package com.junioroffers.offer.scheduling;

import com.junioroffers.JobOffersApplication;
import com.junioroffers.infrastracture.service.offer.client.RemoteOfferClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.context.ActiveProfiles;

import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.ONE_MINUTE;
import static org.awaitility.Duration.TEN_SECONDS;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = JobOffersApplication.class)
@ActiveProfiles("scheduled_test")
public class ScheduledAwaitilityIntegrationTest {

    @SpyBean
    private HttpOfferScheduler scheduler;


    @MockBean
    private RemoteOfferClient offerClient;

    @Test
    void should_return_a_job_offer_every_ten_seconds() {
        await()
                .atMost(ONE_MINUTE)
                .untilAsserted(() -> verify(scheduler, atLeast(1)).scheduleTheDownloadOfTheOfferUsingTheHttpClientWithAConstantDelay());
    }

    @Test
    void should_check_the_method_for_downloading_offers() {
        await()
                .atMost(TEN_SECONDS)
                .untilAsserted(
                        () -> verify(offerClient, times(1)).getOffers());
    }
}

@Configuration
@EnableScheduling
class ScheduledConfig {
}
