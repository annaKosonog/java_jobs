package com.junioroffers.infrastracture.service.offer.client;

import com.junioroffers.infrastracture.model.dto.OfferDto;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
public class OfferClient implements RemoteOfferClient {

    private final String url;
    private final RestTemplate restTemplate;


    public List<OfferDto> getOffers() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<HttpHeaders> headersHttpEntity = new HttpEntity<>(headers);
        try {
            ResponseEntity<List<OfferDto>> responseWithHttp = restTemplate.exchange(
                    url, HttpMethod.GET, headersHttpEntity,
                    new ParameterizedTypeReference<List<OfferDto>>() {
                    });
            final List<OfferDto> responseWithHttpInFormAList = responseWithHttp.getBody();
            return (responseWithHttpInFormAList != null) ? responseWithHttpInFormAList : Collections.emptyList();
        } catch (RestClientException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
