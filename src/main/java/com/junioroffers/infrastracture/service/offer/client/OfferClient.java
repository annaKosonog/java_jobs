package com.junioroffers.infrastracture.service.offer.client;

import com.junioroffers.infrastracture.model.dto.JobOfferDto;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
public class OfferClient implements RemoteOfferClient {

    private final String uri;
    private final int port;
    private final RestTemplate restTemplate;


    public List<JobOfferDto> getOffers() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        final HttpEntity<HttpHeaders> headersHttpEntity = new HttpEntity<>(headers);
        try {
            final String url = UriComponentsBuilder.fromHttpUrl(createAddressUrl("/offers")).toUriString();
            ResponseEntity<List<JobOfferDto>> responseWithHttp = restTemplate.exchange(
                    url, HttpMethod.GET, headersHttpEntity,
                    new ParameterizedTypeReference<List<JobOfferDto>>() {
                    });
            final List<JobOfferDto> responseWithHttpInFormAList = responseWithHttp.getBody();
            return (responseWithHttpInFormAList != null) ? responseWithHttpInFormAList : Collections.emptyList();
        } catch (RestClientException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    private String createAddressUrl(String address){
        return uri + ":" + port + address;
    }
}
