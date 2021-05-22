package com.junioroffers.security.exception.api.response;

import lombok.Getter;

@Getter
public class UserExistsByUsername extends RuntimeException {
    private final String info;

    public UserExistsByUsername(String username) {
        super(String.format("Error: Username is already taken !!! " + username));
        this.info = username;
    }
}
