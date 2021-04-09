package com.junioroffers.offer.domain.dto;

import com.junioroffers.offer.domain.dao.SampleOffers;

import com.junioroffers.offer.domain.mappers.OfferMapper;

public interface SampleOffersDto extends SampleOffers  {

    default OfferDto cyberSourceDto(){
        return OfferMapper.mapToOfferDto(cyberSourceDao());

    }

    default OfferDto cdqPolandDto(){
        return OfferMapper.mapToOfferDto(cdqPolandDao());
    }

}
