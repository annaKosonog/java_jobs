package com.junioroffers.offer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.junioroffers.offer.domain.dto.OfferDto;
import com.junioroffers.offer.domain.dto.SampleOffersDto;
import com.junioroffers.offer.domain.exceptions.OfferControllerErrorHandler;
import com.junioroffers.offer.domain.exceptions.OfferNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ContextConfiguration(classes = MockMvcConfig.class)
//@SpringBootTest
//@AutoConfigureMockMvc
public class OfferControllerTestWebMvc implements SampleOffersDto {

    @Autowired
    private OfferService offerService;

    @Test
    void should_return_correct_message_during_found_all_offers(@Autowired MockMvc mockMvc, @Autowired ObjectMapper objectMapper) throws Exception {
        final List<OfferDto> responseHttp = Arrays.asList(cyberSource(), cdqPolandOffer());
        String expectedResponse = objectMapper.writeValueAsString(responseHttp);

        when(offerService.getOffers()).thenReturn(responseHttp);

        MvcResult result = mockMvc.perform(get("/offers")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String actualResponseBody = result.getResponse().getContentAsString();
        assertThat(actualResponseBody).isEqualTo(expectedResponse);
    }

    @Test
    void should_return_correct_message_during_found_offer_with_id_1(@Autowired MockMvc mockMvc) throws Exception {
        final long id = 1;
        when(offerService.getOfferById(1L)).thenReturn(cyberSource());
        MvcResult result = mockMvc.perform(get("/offers/{id}", id)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        String responseFromHttp = result.getResponse().getContentAsString();
        assertThat(responseFromHttp).contains("7b3e02b3-6b1a-4e75-bdad-cef5b279b074");
        verify(offerService, times(1)).getOfferById(id);
    }

    @Test
    void should_return_exception_when_could_not_found_offer_with_id_5(@Autowired MockMvc mockMvc) throws Exception {
        final long id = 5L;

        when(offerService.getOfferById(id)).thenThrow(new OfferNotFoundException(id));
        mockMvc.perform(get("/offers/{id}", id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
        verify(offerService, times(1)).getOfferById(id);
    }
}

@Configuration(proxyBeanMethods = false)
class MockMvcConfig implements SampleOffersDto {

    @Bean
    OfferService offerService() {
        return mock(OfferService.class);
    }

    @Bean
    OfferController offerController(OfferService offerService) {
        return new OfferController(offerService);
    }

    @Bean
    OfferControllerErrorHandler offerControllerErrorHandler() {
        return new OfferControllerErrorHandler();
    }
}
