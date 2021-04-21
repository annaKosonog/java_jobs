package com.junioroffers.infrastracture.service.offer.client;

import com.junioroffers.infrastracture.model.dto.JobOfferDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SampleOfferResponse extends SampleJobOffer {

    protected ResponseEntity<List<JobOfferDto>> responseWithOneOffer() {
        return new ResponseEntity<>(Collections.singletonList(emptyOffer()), HttpStatus.ACCEPTED);
    }

    protected ResponseEntity<List<JobOfferDto>> responseWithNoOffers() {
        return new ResponseEntity<>(Collections.emptyList(), HttpStatus.ACCEPTED);
    }

    protected ResponseEntity<List<JobOfferDto>> responseWithOffers(JobOfferDto... jobOfferDto) {
        return new ResponseEntity<>(Arrays.asList(jobOfferDto), HttpStatus.ACCEPTED);
    }
}
