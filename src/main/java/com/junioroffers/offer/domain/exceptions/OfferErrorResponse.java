package com.junioroffers.offer.domain.exceptions;

public class OfferErrorResponse  {

    public final String status;
    public final String message;

    public OfferErrorResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
}

