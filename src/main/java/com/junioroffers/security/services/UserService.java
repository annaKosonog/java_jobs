package com.junioroffers.security.services;

import com.junioroffers.security.UserRepository;
import com.junioroffers.security.domain.LoginRequestDto;
import com.junioroffers.security.domain.User;
import com.junioroffers.security.exception.api.response.UserExistsByUsername;
import com.junioroffers.security.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public User addUsers(LoginRequestDto register) {
        try {
            final User mapToUser = userMapper.mapToUser(register);
            return userRepository.save(mapToUser);
        } catch (DuplicateKeyException e) {
            throw new UserExistsByUsername(register.getUsername());
        }
    }
}


