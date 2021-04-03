package com.junioroffers.offer.domain.exceptions;

public class OfferNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -4856846361193249489L;
    private final long infoRequestId;

    public OfferNotFoundException(long id) {
        super(String.format("Could not find offers id %d !!!", id));
        this.infoRequestId = id;
    }

}
