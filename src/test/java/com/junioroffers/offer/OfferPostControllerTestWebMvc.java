package com.junioroffers.offer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.junioroffers.offer.domain.dto.OfferDto;
import com.junioroffers.offer.domain.dto.SampleOffersDto;
import com.junioroffers.offer.domain.exceptions.api.valid.ApiValidationResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ContextConfiguration(classes = MockMvcConfig.class)
public class OfferPostControllerTestWebMvc implements SampleOffersDto {

    @Test
    void should_valid_request_returns_200_Ok(@Autowired MockMvc mockMvc, @Autowired ObjectMapper objectMapper) throws Exception {
        //GIVEN
        OfferDto newOfferDto = cyberSourceDto();
        String expected = objectMapper.writeValueAsString(newOfferDto);

        //WHEN
        final MvcResult result = mockMvc.perform(post("/offers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(expected))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        //THEN
        String actual = result.getResponse().getContentAsString();
        assertThat(actual).isEqualToIgnoringWhitespace(expected);
    }

    @Test
    void should_valid_request_and_return_code_http_400_bad_request_when_company_name_is_blank(@Autowired MockMvc mvc, @Autowired ObjectMapper objectMapper) throws Exception {
        //GIVEN
        final OfferDto offerDto = cyberSourceDtoWithoutCompanyName();
        final String offerDtoJson = objectMapper.writeValueAsString(offerDto);

        mvc.perform(post("/offers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(offerDtoJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn();
    }

    @Test
    void should_valid_request_and_return_code_http_400_bad_request_when_position_is_blank(@Autowired MockMvc mvc, @Autowired ObjectMapper objectMapper) throws Exception {
        //GIVEN
        ApiValidationResponse apiValidationResponse = new ApiValidationResponse(HttpStatus.BAD_REQUEST, Collections.singletonList("{position.not.blank}"));
        String expectedResponse = objectMapper.writeValueAsString(apiValidationResponse);

        final OfferDto offerDto = cyberSourceDtoWithoutPosition();
        final String offerDtoJson = objectMapper.writeValueAsString(offerDto);

        //WHEN
        final MvcResult result = mvc.perform(post("/offers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(offerDtoJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn();

        //THEN
        final String actualResponse = result.getResponse().getContentAsString();
        assertThat(actualResponse).isEqualToIgnoringWhitespace(expectedResponse);
    }

    @Test
    void should_valid_request_and_return_code_http_400_bad_request_when_salary_is_blank(@Autowired MockMvc mvc, @Autowired ObjectMapper objectMapper) throws Exception {
        //GIVEN
        ApiValidationResponse apiValidationResponse = new ApiValidationResponse(HttpStatus.BAD_REQUEST, Collections.singletonList("{salary.not.blank}"));
        String expectedResponse = objectMapper.writeValueAsString(apiValidationResponse);

        final OfferDto offerDto = cyberSourceDtoWithoutSalary();
        final String offerDtoJson = objectMapper.writeValueAsString(offerDto);

        //WHEN
        final MvcResult result = mvc.perform(post("/offers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(offerDtoJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn();

        //THEN
        final String actualResponse = result.getResponse().getContentAsString();
        assertThat(actualResponse).isEqualToNormalizingWhitespace(expectedResponse);
    }

    @Test
    void should_valid_request_and_return_code_http_400_bad_request_when_offer_url_is_blank(@Autowired MockMvc mvc, @Autowired ObjectMapper objectMapper) throws Exception {
        //GIVEN
        ApiValidationResponse apiValidationResponse = new ApiValidationResponse(HttpStatus.BAD_REQUEST, Collections.singletonList("{offerUrl.not.blank}"));
        String expectedResponse = objectMapper.writeValueAsString(apiValidationResponse);

        final OfferDto offerDto = cyberSourceDtoWithoutOfferUrl();
        final String offerDtoJson = objectMapper.writeValueAsString(offerDto);

        //WHEN
        final MvcResult result = mvc.perform(post("/offers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(offerDtoJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn();

        //THEN
        final String actualResponse = result.getResponse().getContentAsString();
        assertThat(actualResponse).isEqualTo(expectedResponse);
    }
}

