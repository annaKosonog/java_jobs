package com.junioroffers.security.domain;

import com.junioroffers.security.services.UserDetailsImp;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collections;
import java.util.List;

public interface SampleUser {

    default UserDetailsImp getUser(String username, String password, List<GrantedAuthority> authorities) {
        return new UserDetailsImp(null, username, password, Collections.EMPTY_LIST);
    }
}
