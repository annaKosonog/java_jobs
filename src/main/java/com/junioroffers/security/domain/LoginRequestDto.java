package com.junioroffers.security.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class LoginRequestDto {

    @NotBlank(message = "{username.not.blank}")
    private final String username;
    @NotBlank(message = "{password.not.blank}")
    private final String password;
}
