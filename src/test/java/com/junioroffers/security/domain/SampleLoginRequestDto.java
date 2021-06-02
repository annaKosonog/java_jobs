package com.junioroffers.security.domain;

public interface SampleLoginRequestDto {

    default LoginRequestDto loginDtoParametersWithoutId(String username, String password){
        return new LoginRequestDto(username, password);
    }

    default LoginRequestDto userTestDto(){
        return loginDtoParametersWithoutId("test", "test");
    }
}
