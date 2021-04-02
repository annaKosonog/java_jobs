package com.junioroffers.offer.domain.exceptions;

public class OfferNotFoundException extends RuntimeException {

    private final long infoRequestId;

    public OfferNotFoundException(long id) {
        super(String.format("Could not find offers id %d !!!", id));
        this.infoRequestId = id;
    }

}
