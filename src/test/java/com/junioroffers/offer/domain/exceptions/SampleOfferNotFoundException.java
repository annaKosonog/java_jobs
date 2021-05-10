package com.junioroffers.offer.domain.exceptions;

import com.junioroffers.offer.domain.exceptions.api.response.OfferNotFoundException;

public interface SampleOfferNotFoundException {

    default OfferNotFoundException sampleOfferNotFoundException(String id) {
        return new OfferNotFoundException(id);
    }
}
