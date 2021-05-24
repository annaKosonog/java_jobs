package com.junioroffers.security.domain;

import com.junioroffers.security.mappers.UserMapper;

public interface SampleLoginRequestDto {

    UserMapper userMapper = null;


    default LoginRequestDto loginRequest(String username, String password){
        return new LoginRequestDto(username, password);
    }

    default LoginRequestDto userTestDto(){
        return loginRequest("test1", "test1");
    }
}
