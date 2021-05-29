package com.junioroffers.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.junioroffers.JobOffersApplication;
import com.junioroffers.security.domain.LoginRequestDto;
import com.junioroffers.security.domain.SampleLoginRequestDto;
import com.junioroffers.security.jwt.JwtResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = JobOffersApplication.class)
@Testcontainers
@AutoConfigureMockMvc
@ActiveProfiles("container_test")
public class LoginControllerIntegrationTest implements SampleLoginRequestDto {

    @Container
    private static final MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.2")
            .withExposedPorts(27017);

    static {
        mongoDBContainer.start();
        System.setProperty("DB_PORT", String.valueOf(mongoDBContainer.getFirstMappedPort()));
    }

    @Test
    void should_return_code_http_200(@Autowired MockMvc mockMvc,
                                     @Autowired ObjectMapper objectMapper) throws Exception {
        final LoginRequestDto user = loginDtoParametersWithoutId("admin", "admin");
        final String signInExpected = objectMapper.writeValueAsString(user);

        final MvcResult login = mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(signInExpected))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        final String contentWithHttp = login.getResponse().getContentAsString();
        final JwtResponse actualResponse = objectMapper.readValue(contentWithHttp, JwtResponse.class);
        String token = actualResponse.getToken();

        mockMvc.perform(get("/offers")
                .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void should_throws_exception_when_incorrect_data_was_entered(@Autowired MockMvc mockMvc,
                                                                 @Autowired ObjectMapper objectMapper) throws Exception{
        final LoginRequestDto user = loginDtoParametersWithoutId("", "admin");
        final String signInExpected = objectMapper.writeValueAsString(user);
        final MvcResult login = mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(signInExpected))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        final String contentWithHttp = login.getResponse().getContentAsString();
        final JwtResponse actualResponse = objectMapper.readValue(contentWithHttp, JwtResponse.class);
        String token = actualResponse.getToken();

        mockMvc.perform(get("/offers")
                .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andReturn();

    }


}
