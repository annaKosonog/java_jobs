package com.junioroffers.security.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class JwtResponse {

    private  final String token;
    private String type = "Bearer";
    private String id;
    private final  String username;

}
