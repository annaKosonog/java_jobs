package com.junioroffers.security.jwt;

import com.junioroffers.security.UserRepository;
import com.junioroffers.security.services.MongoDetailsServiceImp;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestConfiguration
public
class JwtTestConfig {

    @Bean
    AuthenticationEntryPoint authenticationEntryPoint() {
        return mock(AuthenticationEntryPoint.class);
    }

    @Bean
    JwtUtils jwtUtils() {
        JwtUtils jwtUtils = mock(JwtUtils.class);
        when(jwtUtils.validateJwtToken(any())).thenReturn(true);
        when(jwtUtils.getUserNameFromJwtToken(any())).thenReturn("user");
        return jwtUtils;
    }

    @Bean
    AuthTokenFilter authTokenFilter() {
        AuthTokenFilter authTokenFilter = mock(AuthTokenFilter.class);
        when(authTokenFilter.parseJwt(any())).thenReturn("jwt");
        return authTokenFilter;
    }

    @Bean
    AuthTokenFilter filter() {
        return new AuthTokenFilter(jwtUtils(), mongoDetailsServiceImp());
    }

    @Bean
    MongoDetailsServiceImp mongoDetailsServiceImp() {
        UserRepository userRepository = mock(UserRepository.class);
        return new MongoDetailsServiceImp(userRepository) {
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return new User("user", "password", Collections.emptyList());
            }
        };
    }
}
