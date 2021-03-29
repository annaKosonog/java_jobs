package com.junioroffers.offer.domain.exceptions;

public class OfferErrorResponse extends RuntimeException {


    private final long id;

    public OfferErrorResponse(long id) {
        super(String.format("No offer with the given %d id" + " was found", id));
        this.id = id;
    }
}
