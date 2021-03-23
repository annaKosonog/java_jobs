package com.junioroffers.service.offer.client;

import com.junioroffers.model.dto.OfferDto;

import java.util.List;

public interface RemoteOfferClient {
    List<OfferDto> getOffers();
}
