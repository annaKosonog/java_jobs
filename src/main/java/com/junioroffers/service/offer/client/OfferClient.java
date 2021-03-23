package com.junioroffers.service.offer.client;

import com.junioroffers.model.dto.OfferDto;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Service
public class OfferClient implements RemoteOfferClient {

    private final String url = "http://programing-masterpiece.com:5057/offers";

    public List<OfferDto> getOffers() {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<List<OfferDto>> responseWithHttp = restTemplate.exchange(
                    url, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<OfferDto>>() {});
            List<OfferDto> responseWithHttpInFormAList = responseWithHttp.getBody();
            return (responseWithHttpInFormAList !=null) ? responseWithHttpInFormAList: Collections.emptyList();
        } catch (RestClientException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
