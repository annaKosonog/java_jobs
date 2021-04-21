package com.junioroffers.offer.scheduling;

import com.junioroffers.infrastracture.service.offer.client.OfferClient;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HttpOfferScheduler {


    private static OfferClient offer;

    private static final Logger logger = LoggerFactory.getLogger(HttpOfferScheduler.class);

    @Scheduled(fixedRateString = "${fixedRate.in.miliseconds}")
    public void scheduleTheDownloadOfTheOfferUsingTheHttpClientWithAConstantDelay() {

    }
}
