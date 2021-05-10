package com.junioroffers.offer.domain.exceptions.api.response;

import lombok.Getter;

@Getter
public class DuplicateKeyException extends RuntimeException {

    private static final long serialVersionUID = -4856846361193249489L;
    private final String info;

    public DuplicateKeyException(String offerUrl) {
        super(String.format("The given url already exists in the database: " + offerUrl));
        this.info = offerUrl;
    }
}
