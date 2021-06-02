package com.junioroffers.security.domain;

import com.junioroffers.security.jwt.JwtResponse;
import org.springframework.http.ResponseEntity;

public interface SampleJwtResponse {

    default ResponseEntity<JwtResponse> sampleJwtResponse(String jwtToken, String username){
        return ResponseEntity.ok(new JwtResponse(jwtToken, username));
    }
}
