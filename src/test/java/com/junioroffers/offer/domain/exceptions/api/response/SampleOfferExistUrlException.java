package com.junioroffers.offer.domain.exceptions.api.response;

public interface SampleOfferExistUrlException {
    default OfferExistUrlException sampleOfferExistUrlException(String offerUrl){
        return new OfferExistUrlException(offerUrl);
    }
}
