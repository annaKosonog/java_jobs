package com.junioroffers.offer.domain.exceptions.api.response;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OfferControllerExistUrlErrorrHandler implements SampleOfferExistUrlException, SampleOfferErrorResponse {

    @Test
    public void should_return_offer_error_response_code_http_409() {

        //GIVEN
        final String offerUrl = "https://nofluffjobs.com/pl/job/software-engineer-mobile-m-f-d-cybersource-poznan-entavdpn";

        OfferControllerErrorHandler offerControllerErrorHandler = new OfferControllerErrorHandler();

        final OfferExistUrlException urlException = sampleOfferExistUrlException(offerUrl);

        final OfferErrorResponse expectedResponse = sampleOfferErrorResponse();
        //WHEN
        final OfferErrorResponse actualResponse = offerControllerErrorHandler.offersConflict(urlException);
        //THEN
        assertThat(actualResponse.equals(expectedResponse));

    }
}
