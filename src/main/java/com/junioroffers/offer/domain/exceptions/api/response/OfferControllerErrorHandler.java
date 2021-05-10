package com.junioroffers.offer.domain.exceptions.api.response;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class OfferControllerErrorHandler {

    @ExceptionHandler({NullPointerException.class, IllegalArgumentException.class, IllegalStateException.class, OfferNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public OfferErrorResponse offersNotFoundHandler(OfferNotFoundException exception) {
        String message = "Could not find offers id: " + exception.getInfoRequestId();
        log.info(message);
        log.error("500 Status Code", exception);
        return new OfferErrorResponse(HttpStatus.NOT_FOUND, message);
    }

    @ExceptionHandler({InvalidDataAccessApiUsageException.class, DataAccessException.class, DuplicateKeyException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public OfferErrorResponse offersConflict(DuplicateKeyException exception) {
        String message = "The given url already exists in the database" + exception.getMessage();
        log.info("Conflict: {}", exception.getMessage());
        log.debug("Conflict: ", exception);
        return new OfferErrorResponse(HttpStatus.CONFLICT, message);
    }


}
