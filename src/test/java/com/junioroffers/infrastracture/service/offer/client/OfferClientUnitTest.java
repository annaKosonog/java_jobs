package com.junioroffers.infrastracture.service.offer.client;

import com.junioroffers.infrastracture.model.dto.JobOfferDto;
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
        // GIVEN
        ResponseEntity<List<JobOfferDto>> responseEntity = responseWithNoOffers();
        when(getExchange(restTemplate))
                .thenReturn(responseEntity);
        // WHEN
        final List<JobOfferDto> offers = offerClient.getOffers();
        // THEN
        assertThat(offers.size()).isEqualTo(0);
    }

    @Test
    public void should_return_one_element_list_of_offers() {
        // GIVEN
        ResponseEntity<List<JobOfferDto>> responseEntity = responseWithOneOffer();
        when(getExchange(restTemplate))
                .thenReturn(responseEntity);
        // WHEN
        List<JobOfferDto> offers = offerClient.getOffers();
        //THEN
        assertThat(offers.size()).isEqualTo(1);
    }


    @Test
    public void should_return_two_offers() {
        //GIVEN
        ResponseEntity<List<JobOfferDto>> responseEntity = responseWithOffers(emptyOffer(), emptyOffer());
        when(getExchange(restTemplate))
                .thenReturn(responseEntity);
        //WHEN
        final List<JobOfferDto> offers = offerClient.getOffers();
        //THEN
        assertThat(offers.size()).isEqualTo(2);
    }
}
