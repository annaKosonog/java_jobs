package com.junioroffers.offer.domain.dto;

import com.junioroffers.offer.domain.mappers.OfferMapper;
import java.util.UUID;

public interface SampleOffersDto {

    default OfferDto cyberSource() {

        return OfferMapper.mapToOfferDto(UUID.fromString("7b3e02b3-6b1a-4e75-bdad-cef5b279b074"),
                "Software Engineer - Mobile (m/f/d)",
                "Cybersource", "4k - 8k PLN",
                "https://nofluffjobs.com/pl/job/software-engineer-mobile-m-f-d-cybersource-poznan-entavdpn");
    }

    default OfferDto cdqPolandOffer() {
        return OfferMapper.mapToOfferDto(UUID.fromString("24ee32b6-6b15-11eb-9439-0242ac130002"),
                "Junior DevOps Engineer",
                "CDQ Poland","8k - 14k PLN",
                "https://nofluffjobs.com/pl/job/junior-devops-engineer-cdq-poland-wroclaw-gnymtxqd");
    }
}
