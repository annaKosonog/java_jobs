package com.junioroffers.offer.domain.exceptions;


public class OfferNotFoundException extends RuntimeException {

    public OfferNotFoundException(long id){
        super("Could not find offers id" + id);
    }

}
