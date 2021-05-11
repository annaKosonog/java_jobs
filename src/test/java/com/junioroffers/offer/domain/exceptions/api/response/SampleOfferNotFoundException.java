package com.junioroffers.offer.domain.exceptions.api.response;

public interface SampleOfferNotFoundException {

    default OfferNotFoundException sampleOfferNotFoundException(String id) {
        return new OfferNotFoundException(id);
    }
}
