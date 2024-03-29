package com.junioroffers.offer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.junioroffers.config.MessageSourceConfig;
import com.junioroffers.offer.domain.dto.OfferDto;
import com.junioroffers.offer.domain.dto.SampleOffersDto;
import com.junioroffers.offer.domain.exceptions.api.response.OfferControllerErrorHandler;
import com.junioroffers.offer.domain.exceptions.api.response.OfferErrorResponse;
import com.junioroffers.offer.domain.exceptions.api.response.SampleOfferNotFoundException;
import com.junioroffers.offer.domain.exceptions.api.valid.ApiOfferControllerErrorHandler;
import com.junioroffers.offer.domain.mappers.OfferMapper;
import com.junioroffers.security.config.SecurityConfig;
import com.junioroffers.security.jwt.JwtTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ContextConfiguration(classes = MockMvcConfig.class)
public class OfferControllerTestWebMvc implements SampleOffersDto {

    @Test
    void should_return_correct_message_during_found_all_offers(@Autowired MockMvc mockMvc, @Autowired ObjectMapper objectMapper) throws Exception {
        final List<OfferDto> responseHttp = Arrays.asList(cyberSourceDto(), cdqPolandDto());
        String expectedResponse = objectMapper.writeValueAsString(responseHttp);

        MvcResult result = mockMvc.perform(get("/offers")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        final String actualResponseBody = result.getResponse().getContentAsString();
        assertThat(actualResponseBody.equals(expectedResponse));
    }

    @Test
    void should_return_correct_message_during_found_offer_with_id(@Autowired MockMvc mockMvc, @Autowired ObjectMapper objectMapper) throws Exception {
        final String id = "7b3e02b3-6b1a-4e75-bdad-cef5b279b074";
        String expectedResponse = objectMapper.writeValueAsString(cyberSourceDto());

        MvcResult result = mockMvc.perform(get("/offers/{id}", id)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        String actualResponseBody = result.getResponse().getContentAsString();
        assertThat(actualResponseBody.equals(expectedResponse));
    }

    @Test
    void should_return_exception_when_could_not_found_offer_with_id_five(@Autowired MockMvc mockMvc, @Autowired ObjectMapper objectMapper) throws Exception {
        final String id = "5";
        OfferErrorResponse offerErrorResponse = new OfferErrorResponse(HttpStatus.NOT_FOUND, "Could not find offers id: " + id);
        String expectedResponse = objectMapper.writeValueAsString(offerErrorResponse);

        MvcResult result = mockMvc.perform(get("/offers/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();

        String actualResponseBody = result.getResponse().getContentAsString();
        assertThat(actualResponseBody.equals(expectedResponse));
    }
}

@Import({MessageSourceConfig.class, SecurityConfig.class, JwtTestConfig.class})
class MockMvcConfig implements SampleOffersDto, SampleOfferNotFoundException {

    @Bean
    OfferService offerService() {
        OfferRepository offerRepository = mock(OfferRepository.class);
        OfferMapper offerMapper = mock(OfferMapper.class);
        return new OfferService(offerRepository, offerMapper) {
            @Override
            public OfferDto findOfferById(String id) {
                if (id.equals("7b3e02b3-6b1a-4e75-bdad-cef5b279b074")) {
                    return cyberSourceDto();
                } else if (id.equals("24ee32b6-6b15-11eb-9439-0242ac130002")) {
                    return cdqPolandDto();
                }
                throw sampleOfferNotFoundException(id);
            }

            @Override
            public OfferDto addOffers(OfferDto offerDto) {
                return cyberSourceDto();
            }
        };
    }

    @Bean
    OfferController offerController(OfferService offerService) {
        return new OfferController(offerService);
    }

    @Bean
    OfferControllerErrorHandler offerControllerErrorHandler() {
        return new OfferControllerErrorHandler();
    }

    @Bean
    ApiOfferControllerErrorHandler apiOfferControllerErrorHandler() {
        return new ApiOfferControllerErrorHandler();
    }
}
