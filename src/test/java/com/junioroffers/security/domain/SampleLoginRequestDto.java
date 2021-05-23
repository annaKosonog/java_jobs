package com.junioroffers.security.domain;

import com.junioroffers.security.mappers.UserMapper;

public interface SampleLoginRequestDto {

    UserMapper userMaper = null;

    default LoginRequestDto loginRequest(String username, String password){
        return new LoginRequestDto(username, password);
    }

    default LoginRequestDto userTestDto(){
        return loginRequest("test", "test");
    }

    default User userTestDao(){
        return userMaper.mapToUser(userTestDto());
    }
}
