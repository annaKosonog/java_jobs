package com.junioroffers.security;

import com.junioroffers.security.domain.LoginRequestDto;
import com.junioroffers.security.domain.SampleAuthentication;
import com.junioroffers.security.domain.SampleJwtResponse;
import com.junioroffers.security.domain.SampleLoginRequestDto;
import com.junioroffers.security.domain.SampleMessageResponse;
import com.junioroffers.security.domain.SampleUser;
import com.junioroffers.security.jwt.JwtResponse;
import com.junioroffers.security.jwt.JwtUtils;
import com.junioroffers.security.mappers.UserMapper;
import com.junioroffers.security.services.UserDetailsImp;
import com.junioroffers.security.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class LoginControllerTest implements SampleLoginRequestDto, SampleUser, SampleAuthentication, SampleJwtResponse, SampleMessageResponse {

    @Test
    public void should_return_correctly_generate_jwt_token() {

        final ResponseEntity<JwtResponse> expectedResponse = sampleJwtResponse("jwtToken", "user");
        final UserDetailsImp user = getUser("user", "user", Collections.EMPTY_LIST);
        final Authentication authentication = sampleAuthentication(user);

        final AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
        when(authenticationManager.authenticate((any()))).thenReturn(authentication);

        final JwtUtils jwtUtils = mock(JwtUtils.class);
        when(jwtUtils.generateJwtToken(authentication)).thenReturn("jwtToken");

        final SecurityContext securityContextHolder = mock(SecurityContext.class);
        when(securityContextHolder.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContextHolder);

        final UserRepository userRepository = mock(UserRepository.class);
        final UserMapper userMapper = mock(UserMapper.class);
        final UserService userService = new UserService(userRepository, userMapper);

        final LoginRequestDto loginRequestDto = loginRequest("user", "user");
        final LoginController loginController = new LoginController(jwtUtils, authenticationManager, userRepository, userService);

        //WHEN
        final ResponseEntity<JwtResponse> actualResponse = loginController.authenticateUser(loginRequestDto);

        //THEN
        assertThat(actualResponse.toString()).isEqualTo(expectedResponse.toString());

    }
}
