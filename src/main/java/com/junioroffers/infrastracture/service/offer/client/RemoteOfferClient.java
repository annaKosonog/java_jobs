package com.junioroffers.infrastracture.service.offer.client;

import com.junioroffers.infrastracture.model.dto.OfferDto;

import java.util.List;

public interface RemoteOfferClient {
    List<OfferDto> getOffers();
}
