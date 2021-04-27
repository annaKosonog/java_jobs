package com.junioroffers.infrastracture.service.offer.config;

import com.junioroffers.config.OfferClientConfig;
import com.junioroffers.infrastracture.service.offer.client.OfferClient;
import com.junioroffers.infrastracture.service.offer.client.RemoteOfferClient;
import org.springframework.web.client.RestTemplate;

public class OfferClientConfigTest extends OfferClientConfig {
    RemoteOfferClient remoteOfferClientTest(  String uri, int port, RestTemplate restTemplate){
        return remoteOfferClient(  restTemplate, uri, port);
    }
}
