package com.junioroffers.offer.scheduling;

import com.junioroffers.infrastracture.model.dto.JobOfferDto;
import com.junioroffers.infrastracture.service.offer.client.RemoteOfferClient;
import com.junioroffers.offer.OfferService;
import com.junioroffers.offer.domain.dao.Offer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class HttpOfferScheduler {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");


    private final RemoteOfferClient offer;
    private final OfferService offerService;


    @Scheduled(fixedDelayString = "${http.offer.downloads}")
    public void scheduleTheDownloadOfTheOfferUsingTheHttpClientWithAConstantDelay() {
        log.info("Start task executed at: " + dateFormat.format(new Date()));
        final List<JobOfferDto> downloadOffers = offer.getOffers();
        log.info("Download offers: " + downloadOffers);
        log.info("Checking whether a given offer is already in the database");
        final List<Offer> savedOffers = offerService.saveAllOffers(downloadOffers);
        log.info("Saved offers: " + savedOffers.size());
        log.info("Stop task executed at: " + dateFormat.format(new Date()));
    }
}
