package com.junioroffers.infrastracture.service.offer.config;

import com.junioroffers.config.OfferClientConfig;
import com.junioroffers.infrastracture.service.offer.client.RemoteOfferClient;
import org.springframework.web.client.RestTemplate;

public class OfferClientConfigTest extends OfferClientConfig {
   public  RemoteOfferClient remoteOfferClientTest(String uri, int port, RestTemplate restTemplate) {
        return remoteOfferClient(restTemplate, uri, port);
    }
}
