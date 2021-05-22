package com.junioroffers.security.exception.api.valid;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
public class LoginValidationResponse {

    public final HttpStatus status;
    public final String message;
}
