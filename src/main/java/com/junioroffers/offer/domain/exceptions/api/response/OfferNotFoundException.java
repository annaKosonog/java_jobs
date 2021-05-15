package com.junioroffers.offer.domain.exceptions.api.response;

import lombok.Getter;

@Getter
public class OfferNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -4856846361193249489L;
    private final String infoRequestId;

    public OfferNotFoundException(String id) {
        super(String.format("Could not find offers id", id));
        this.infoRequestId = id;
    }
}
