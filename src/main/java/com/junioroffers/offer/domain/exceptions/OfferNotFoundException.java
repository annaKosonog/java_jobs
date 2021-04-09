package com.junioroffers.offer.domain.exceptions;

public class OfferNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -4856846361193249489L;
    private final String infoRequestId;

    public OfferNotFoundException(String id) {
        super(id);
        this.infoRequestId = id;
    }

}
