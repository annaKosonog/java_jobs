package com.junioroffers.offer.domain.exceptions;

import org.springframework.http.HttpStatus;

public interface SampleOfferErrorResponse {
    default OfferErrorResponse sampleOfferErrorResponse() {
        return new OfferErrorResponse(HttpStatus.NOT_FOUND, "Could not find offers id: ");
    }
}
