package com.junioroffers.service.offer.client;

import com.junioroffers.model.dto.OfferDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

public class OfferClientUnitTest extends SampleOfferResponse implements SampleRestTemplate {

    final RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
    RemoteOfferClient offerClient = new OfferClient("https://programming-masterpiece.com:1111/test", restTemplate);

    @Test
    public void should_return_empty_list_of_offers() {
        // given
        ResponseEntity<List<OfferDto>> responseEntity = responseWithNoOffers();
        when(getExchange(restTemplate))
                .thenReturn(responseEntity);

        // when
        final List<OfferDto> offers = offerClient.getOffers();

        // then
        assertThat(offers.size()).isEqualTo(0);
    }

    @Test
    public void should_return_one_element_list_of_offers() {
        // given
        ResponseEntity<List<OfferDto>> responseEntity = responseWithOneOffer();
        when(getExchange(restTemplate))
                .thenReturn(responseEntity);

        // when
        List<OfferDto> offers = offerClient.getOffers();

        // then
        assertThat(offers.size()).isEqualTo(1);
    }


    @Test
    public void should_return_two_offers() {
        // given
        ResponseEntity<List<OfferDto>> responseEntity = responseWithOffers(emptyOffer(), emptyOffer());
        when(getExchange(restTemplate))
                .thenReturn(responseEntity);

        // when
        final List<OfferDto> offers = offerClient.getOffers();

        // then
        assertThat(offers.size()).isEqualTo(2);
    }
}
