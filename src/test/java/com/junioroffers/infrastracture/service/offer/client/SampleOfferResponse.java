package com.junioroffers.infrastracture.service.offer.client;

import com.junioroffers.infrastracture.model.dto.OfferDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SampleOfferResponse extends SampleJobOffer {

    protected ResponseEntity<List<OfferDto>> responseWithOneOffer() {
        return new ResponseEntity<>(Collections.singletonList(emptyOffer()), HttpStatus.ACCEPTED);
    }

    protected ResponseEntity<List<OfferDto>> responseWithNoOffers() {
        return new ResponseEntity<>(Collections.emptyList(), HttpStatus.ACCEPTED);
    }

    protected ResponseEntity<List<OfferDto>> responseWithOffers(OfferDto... offerDto) {
        return new ResponseEntity<>(Arrays.asList(offerDto), HttpStatus.ACCEPTED);
    }
}
