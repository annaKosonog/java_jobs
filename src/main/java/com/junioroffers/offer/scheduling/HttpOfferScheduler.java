package com.junioroffers.offer.scheduling;

import com.junioroffers.infrastracture.model.dto.JobOfferDto;
import com.junioroffers.infrastracture.service.offer.client.OfferClient;
import com.junioroffers.infrastracture.service.offer.client.RemoteOfferClient;
import com.junioroffers.offer.OfferService;
import com.junioroffers.offer.domain.dao.Offer;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class HttpOfferScheduler {

    private OfferClient offer;
    private OfferService offerService;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private static final Logger logger = LoggerFactory.getLogger(HttpOfferScheduler.class);


    @Scheduled(fixedDelayString = "${fixed.task}")
    public void scheduleTheDownloadOfTheOfferUsingTheHttpClientWithAConstantDelay() {
        logger.info("Start task executed at {}", dateFormat.format(new Date()));
        final List<JobOfferDto> downloadOffers = offer.getOffers();
        logger.info("Download offers: " + downloadOffers);
        logger.info("Checking whether a given offer is already in the database");
        final List<Offer> savedOffers = offerService.saveAllOffers(downloadOffers);
        logger.info("Saved offers: " + savedOffers);
        logger.info("Stop task executed at {}" + dateFormat.format(new Date()));
    }
}
