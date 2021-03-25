package com.junioroffers.service.offer.client;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.util.SocketUtils;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.BDDAssertions.then;

class OfferClientIntegrationTest extends SampleJobOffer {
    int port = SocketUtils.findAvailableTcpPort();
    WireMockServer wireMockServer;


    OfferClient service = new OfferClient("http://localhost:" + port + "/offers", closeRestTemplate());

    @BeforeEach
    void setup() {
        wireMockServer = new WireMockServer(options().port(port));
        wireMockServer.start();
        WireMock.configureFor(port);
    }

    @AfterEach
    void tearDown() {
        wireMockServer.stop();
    }

    private RestTemplate closeRestTemplate() {
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        return restTemplateBuilder
                .setConnectTimeout(Duration.ofMillis(1000))
                .setReadTimeout(Duration.ofMillis(1000))
                .build();
    }

    @Test
    void should_return_positive_verification_zero_offer() {
        WireMock.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(aZeroOffer())));
        then(service.getOffers()).containsExactlyInAnyOrderElementsOf(Collections.emptyList());
    }

    @Test
    void should_return_positive_verification_one_offer() {
        WireMock.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(aOneOfferJSON())));
        then(service.getOffers()).containsOnlyElementsOf(Collections.singletonList(aCybersource()));
    }

    @Test
    void should_return_positive_verification_two_offers() {
        WireMock.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(aTwoOffersJSON())));
        then(service.getOffers()).containsExactlyElementsOf(Arrays.asList(aCybersource(), aJuniorDevOpsEngineer()));
    }


}
