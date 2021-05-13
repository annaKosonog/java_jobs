package com.junioroffers.offer.domain.dto;

import com.junioroffers.offer.domain.dao.Offer;
import com.junioroffers.offer.domain.dao.SampleOffers;
import com.junioroffers.offer.domain.mappers.OfferMapper;

public interface SampleOffersDto extends SampleOffers {

    default OfferDto cyberSourceDtoMapper() {
        return OfferMapper.mapToOfferDto(cyberSourceDao());

    }

    default OfferDto cdqPolandDtoMapper() {
        return OfferMapper.mapToOfferDto(cdqPolandDao());
    }

    default Offer cyberSourceDtoWithoutIdMapper(){
        return OfferMapper.mapFromOffer(cyberSourceDtoWithoutId());
    }

}
