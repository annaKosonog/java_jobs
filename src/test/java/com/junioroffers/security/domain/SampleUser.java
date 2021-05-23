package com.junioroffers.security.domain;

import org.springframework.security.crypto.password.PasswordEncoder;

public interface SampleUser {

    default User allParametersOfTheObjectUser(String id, String username, String password) {
        return new User(id, username, password);
    }

    default User parametersUserWithoutId(String username, String password) {
        final User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }


    default User aUser(User user, PasswordEncoder passwordEncoder){
        user.setUsername("user");
        passwordEncoder.encode(user.getPassword());
        return user;
    }


}
