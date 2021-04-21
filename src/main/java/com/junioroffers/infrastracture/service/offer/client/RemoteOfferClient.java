package com.junioroffers.infrastracture.service.offer.client;

import com.junioroffers.infrastracture.model.dto.JobOfferDto;

import java.util.List;

public interface RemoteOfferClient {
    List<JobOfferDto> getOffers();
}
