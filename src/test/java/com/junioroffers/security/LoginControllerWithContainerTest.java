package com.junioroffers.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.junioroffers.JobOffersApplication;
import com.junioroffers.security.domain.LoginRequestDto;
import com.junioroffers.security.domain.SampleLoginRequestDto;
import com.junioroffers.security.domain.SampleUser;
import com.junioroffers.security.exception.api.valid.LoginValidationResponse;
import com.junioroffers.security.jwt.JwtResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = JobOffersApplication.class)
@Testcontainers
@AutoConfigureMockMvc
@ActiveProfiles("container_test")
public class LoginControllerWithContainerTest implements SampleUser, SampleLoginRequestDto {

    @Container
    private static final MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.2")
            .withExposedPorts(27017);

    static {
        mongoDBContainer.start();
        System.setProperty("DB_PORT", String.valueOf(mongoDBContainer.getFirstMappedPort()));
    }

    @Test
    void should_return_generate_token_the_next_to_should_have_access_request_http_200(@Autowired MockMvc mvc,
                                                                                      @Autowired ObjectMapper objectMapper) throws Exception {
        final LoginRequestDto user = loginDtoParametersWithoutId("admin", "admin");
        final String signInExpected = objectMapper.writeValueAsString(user);

        final MvcResult login = mvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(signInExpected))
                .andDo(log())
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        final String token = login.getResponse().getContentAsString();

        final JwtResponse actualResponse = objectMapper.readValue(token, JwtResponse.class);
        assertThat(actualResponse.getUsername()).isEqualTo("admin");
        assertThat(actualResponse.getToken()).isNotBlank();
    }

    @Test
    void should_return_request_code_http_400_when_will_not_provide_a_username(@Autowired MockMvc mockMvc,
                                                                              @Autowired ObjectMapper objectMapper) throws Exception {
        final LoginRequestDto user = loginDtoParametersWithoutId("", "admin");
        final String signInExpected = objectMapper.writeValueAsString(user);
        final String expectedResponse = "Username may not be blank";

        final MvcResult login = mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(signInExpected))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        final String actualResponse = login.getResponse().getContentAsString();

        assertThat(actualResponse).containsIgnoringCase(expectedResponse);
        assertThat(actualResponse).isNotBlank();

    }


    @Test
    void should_return_code_http_200_when_added_new_user_to_the_db(@Autowired MockMvc mockMvc, @Autowired UserRepository userRepository, @Autowired ObjectMapper objectMapper) throws Exception {
        final LoginRequestDto register = userTestDto();
        final String expected = objectMapper.writeValueAsString(register);

        final MvcResult createUser = mockMvc.perform(post("/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(StandardCharsets.UTF_8.name())
                .content(expected))
                .andExpect(status().is2xxSuccessful())
                .andDo(print())
                .andReturn();

        final MessageResponse messageResponse = objectMapper.readValue(createUser.getResponse().getContentAsString(), MessageResponse.class);
        assertThat(messageResponse.getMessage().equals("User registered successfully!"));
        assertThat(userRepository.existsByUsername("test")).isTrue();
    }

    @Test
    void should_return_http_code_400_when_try_to_add_user_without_password(@Autowired MockMvc mockMvc, @Autowired ObjectMapper objectMapper, @Autowired UserRepository userRepository) throws Exception {
        final LoginValidationResponse response = new LoginValidationResponse(HttpStatus.BAD_REQUEST, "Password may not be blank");
        final String expectedResponse = objectMapper.writeValueAsString(response);

        final LoginRequestDto registerOne = loginDtoParametersWithoutId("jakub", "");
        final String theUserNotBeAdded = objectMapper.writeValueAsString(registerOne);

        final MvcResult result = mockMvc.perform(post("/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(theUserNotBeAdded))
                .andExpect(status().isBadRequest())
                .andReturn();

        final String actualResponse = result.getResponse().getContentAsString();
        assertThat(expectedResponse.equals(actualResponse));
    }
}
