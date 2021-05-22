package com.junioroffers.security.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class JwtResponse {

    private  final String token;
    private String type = "Bearer";
    private String id;
    private final  String username;

}
