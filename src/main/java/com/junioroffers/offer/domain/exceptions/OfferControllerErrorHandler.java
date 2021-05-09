package com.junioroffers.offer.domain.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class OfferControllerErrorHandler {

    @ExceptionHandler(OfferNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public OfferErrorResponse offersNotFoundHandler(OfferNotFoundException exception) {
        String message = "Could not find offers id: " + exception.getInfoRequestId();
        log.info(message);
        return new OfferErrorResponse(HttpStatus.NOT_FOUND, message);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public OfferErrorResponse offersConflict(DuplicateKeyException exception) {
        String message = "The given url already exists in the database" + exception.getMessage();
        log.info(exception.getMessage(), exception);
        return new OfferErrorResponse(HttpStatus.BAD_REQUEST, message);
    }

}
