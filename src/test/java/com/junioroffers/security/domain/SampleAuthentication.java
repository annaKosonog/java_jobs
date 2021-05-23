package com.junioroffers.security.domain;

import com.junioroffers.security.services.UserDetailsImp;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public interface SampleAuthentication {

    default UsernamePasswordAuthenticationToken sampleAuthentication(UserDetailsImp user) {
        return new UsernamePasswordAuthenticationToken(user, null);
    }
}
