package com.junioroffers.offer.domain.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OfferErrorResponse  {

    public final String status;
    public final String message;
}

