package com.junioroffers.infrastracture.service.offer.client;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.http.Fault;
import com.junioroffers.infrastracture.service.offer.config.OfferClientConfigTest;
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

    RemoteOfferClient remote = new OfferClientConfigTest().remoteOfferClient(closeRestTemplate(), "http://localhost", port);

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
        then(remote.getOffers()).containsExactlyInAnyOrderElementsOf(Collections.emptyList());
    }

    @Test
    void should_return_positive_verification_one_offer() {
        WireMock.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(aOneOfferJSON())));
        then(remote.getOffers()).containsExactlyElementsOf(Collections.singletonList(aCybersource()));
    }

    @Test
    void should_return_positive_verification_two_offers() {
        WireMock.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(aTwoOffersJSON())));
        then(remote.getOffers()).containsExactlyElementsOf(Arrays.asList(aCybersource(), aJuniorDevOpsEngineer()));
    }

    @Test
    void should_return_empty_list_when_not_found_any_jobs() {
        WireMock.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withFault(Fault.EMPTY_RESPONSE)));
        then(remote.getOffers().size()).isEqualTo(0);
    }

    @Test
    void should_return_invalid_JSON_from_the_response() {
        WireMock.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withFault(Fault.MALFORMED_RESPONSE_CHUNK)));
        then(remote.getOffers().size()).isEqualTo(0);
    }

    @Test
    void should_return_error_when_get_random_date() {
        WireMock.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withFault(Fault.RANDOM_DATA_THEN_CLOSE)));
        then(remote.getOffers().size()).isEqualTo(0);
    }

    @Test
    void should_return_an_empty_list_when_the_connection_fails() {
        WireMock.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withFault(Fault.CONNECTION_RESET_BY_PEER)));
        then(remote.getOffers().size()).isEqualTo(0);
    }
}
