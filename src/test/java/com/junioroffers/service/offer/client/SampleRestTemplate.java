package com.junioroffers.service.offer.client;

import com.junioroffers.model.dto.OfferDto;
import org.mockito.ArgumentMatchers;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public interface SampleRestTemplate  {
    default ResponseEntity<List<OfferDto>> getExchange(RestTemplate restTemplate) {
        return restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<ParameterizedTypeReference<List<OfferDto>>>any());
    }
}
