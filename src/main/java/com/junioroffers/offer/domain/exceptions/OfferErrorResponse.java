package com.junioroffers.offer.domain.exceptions;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class OfferErrorResponse  {

    public final HttpStatus status;
    public final String message;
}

