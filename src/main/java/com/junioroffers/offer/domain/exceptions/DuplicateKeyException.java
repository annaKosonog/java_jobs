package com.junioroffers.offer.domain.exceptions;

import lombok.Getter;
import org.springframework.dao.DataIntegrityViolationException;

@Getter
public class DuplicateKeyException extends DataIntegrityViolationException {

    private final String infoCause;

    public DuplicateKeyException(String msg, String offerUrl, Throwable cause) {
        super(msg.concat("The given url already exists in the database: " + offerUrl));
        this.infoCause = cause.getLocalizedMessage();
    }
}
