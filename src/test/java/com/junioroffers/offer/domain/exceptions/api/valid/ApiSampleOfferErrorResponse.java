package com.junioroffers.offer.domain.exceptions.api.valid;

import org.springframework.http.HttpStatus;

import java.util.Collections;

public interface ApiSampleOfferErrorResponse {
    default ApiValidationResponse apiSampleOfferErrorResponse(){
        return new ApiValidationResponse(HttpStatus.BAD_REQUEST, Collections.singletonList("Errors"));
    }
}
