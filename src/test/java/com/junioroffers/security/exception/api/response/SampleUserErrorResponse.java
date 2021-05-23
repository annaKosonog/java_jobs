package com.junioroffers.security.exception.api.response;

import org.springframework.http.HttpStatus;

public interface SampleUserErrorResponse {
    default UserErrorResponse sampleUserErrorResponse(){
        return new UserErrorResponse(HttpStatus.CONFLICT, "Error: Username is already taken!!!");
    }
}
