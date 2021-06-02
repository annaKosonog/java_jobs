package com.junioroffers.security.services;

import com.junioroffers.security.UserRepository;
import com.junioroffers.security.domain.SampleLoginRequestDto;
import com.junioroffers.security.domain.SampleUser;
import com.junioroffers.security.mappers.UserMapper;

import static org.mockito.Mockito.mock;

public class UserServicesAddUserTest implements SampleUser, SampleLoginRequestDto {


    UserRepository userRepository = mock(UserRepository.class);
    UserMapper userMapper = mock(UserMapper.class);

    UserService userService = new UserService(userRepository, userMapper);
}
