package com.junioroffers.offer.domain.exceptions.api.response;

import lombok.Getter;

@Getter
public class OfferExistUrlException extends RuntimeException {

    private static final long serialVersionUID = -4856846361193249489L;
    private final String info;

    public OfferExistUrlException(String offerUrl) {
        super(String.format("The given url already exists in the database: " + "\n" + offerUrl));
        this.info = offerUrl;
    }
}
