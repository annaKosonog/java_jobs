package com.junioroffers.offer.domain.exceptions.api.valid;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class ApiValidationResponse {
    public final HttpStatus status;
    public final List<String> message;

}
