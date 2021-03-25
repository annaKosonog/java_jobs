package com.junioroffers.service.offer.client;

import com.junioroffers.model.dto.OfferDto;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;


@Service
@AllArgsConstructor
public class OfferClient implements RemoteOfferClient {

    private final String url;
    private final RestTemplate restTemplate;


    public List<OfferDto> getOffers() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<HttpHeaders> headersHttpEntity = new HttpEntity<>(headers);
        try {
            ResponseEntity<List<OfferDto>> responseWithHttp = restTemplate.exchange(
                    url, HttpMethod.GET, headersHttpEntity,
                    new ParameterizedTypeReference<List<OfferDto>>() {});
            List<OfferDto> responseWithHttpInFormAList = responseWithHttp.getBody();
            return (responseWithHttpInFormAList !=null) ? responseWithHttpInFormAList: Collections.emptyList();
        } catch (RestClientException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
