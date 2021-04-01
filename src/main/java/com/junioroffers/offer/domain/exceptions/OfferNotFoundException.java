package com.junioroffers.offer.domain.exceptions;

public class OfferNotFoundException extends RuntimeException {

    public OfferNotFoundException(long id) {
        super(String.format("Could not find offers id %d !!!", id));
    }

}
