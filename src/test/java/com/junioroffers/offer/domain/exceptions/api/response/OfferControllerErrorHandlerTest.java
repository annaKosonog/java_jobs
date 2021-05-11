package com.junioroffers.offer.domain.exceptions.api.response;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OfferControllerErrorHandlerTest implements SampleOfferErrorResponse, SampleOfferNotFoundException {

    @Test
    public void should_return_offer_error_response() {
        final String ID = "100";
        OfferControllerErrorHandler offerControllerErrorHandler = new OfferControllerErrorHandler();
        final OfferNotFoundException exception = sampleOfferNotFoundException(ID);
        final OfferErrorResponse expectedResponse = sampleOfferErrorResponse();
        final OfferErrorResponse actualResponse = offerControllerErrorHandler.offersNotFoundHandler(exception);
        assertThat(actualResponse.equals(expectedResponse));
    }
}
