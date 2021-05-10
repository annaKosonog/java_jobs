package com.junioroffers.offer.domain.exceptions;

import com.junioroffers.offer.domain.exceptions.api.response.OfferErrorResponse;
import org.springframework.http.HttpStatus;

public interface SampleOfferErrorResponse {
    default OfferErrorResponse sampleOfferErrorResponse() {
        return new OfferErrorResponse(HttpStatus.NOT_FOUND, "Could not find offers id: ");
    }
}
