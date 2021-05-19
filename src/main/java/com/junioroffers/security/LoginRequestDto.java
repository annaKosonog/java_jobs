package com.junioroffers.security;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class LoginRequestDto {

    @NotBlank
    private final String username;
    @NotBlank
    private final String password;
}
