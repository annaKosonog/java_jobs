package com.junioroffers.security.domain;

import com.junioroffers.security.services.UserDetailsImp;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collections;
import java.util.List;

public interface SampleUser {

    default UserDetailsImp getUser(String username, String password, List<GrantedAuthority> authorities) {
        return new UserDetailsImp(null, username, password, Collections.EMPTY_LIST);
    }

    default User userParametersWithoutId(String username, String password){
        final User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }

    default User allUserParameters(String id, String username, String password){
        return new User(id, username, password);
    }

    default User saveToUserDb(){
        return allUserParameters("123", "test1", "test1");
    }
}
