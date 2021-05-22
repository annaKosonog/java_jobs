package com.junioroffers.security.mappers;

import com.junioroffers.security.model.LoginRequestDto;
import com.junioroffers.security.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {


    private final PasswordEncoder passwordEncoder;


    public User mapToUser(LoginRequestDto loginRequestDto) {
        return new User(
                loginRequestDto.getUsername(),
                passwordEncoder.encode(loginRequestDto.getPassword()));
    }
}
