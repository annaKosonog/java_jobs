package com.junioroffers.offer.domain.exceptions.api.response;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class OfferErrorResponse {

    public final HttpStatus status;
    public final String message;
}

