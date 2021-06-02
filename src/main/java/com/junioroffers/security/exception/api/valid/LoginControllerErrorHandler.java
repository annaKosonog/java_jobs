package com.junioroffers.security.exception.api.valid;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class LoginControllerErrorHandler {

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public LoginValidationResponse loginUnauthorized(BadCredentialsException ex) {
        String message = "The given data is incorrect: " + ex.getMessage();
        log.info("Unauthorized: {}", ex);
        return new LoginValidationResponse(HttpStatus.UNAUTHORIZED, message);
    }
}
