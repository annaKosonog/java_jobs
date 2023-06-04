package com.junioroffers.offer.domain.dto;

import com.junioroffers.offer.domain.dao.SampleOffers;

public interface SampleOffersDto extends SampleOffers {

    default OfferDto cyberSourceDto() {
        return OfferDto.builder()
                .id("7b3e02b3-6b1a-4e75-bdad-cef5b279b074")
                .companyName("Cybersource")
                .position("Software Engineer - Mobile (m/f/d)")
                .salary("4k - 8k PLN")
                .offerUrl("https://nofluffjobs.com/pl/job/software-engineer-mobile-m-f-d-cybersource-poznan-entavdpn")
                .build();
    }


    default OfferDto aFirstCompanyDto() {
        return OfferDto.builder()
                .id("35ee32b7-6b15-11eb-9439-0242ac131112")
                .companyName("First company")
                .position("First position")
                .salary("First salary")
                .offerUrl("Unique url")
                .build();
    }

    default OfferDto cyberSourceDtoWithoutCompanyName() {
        return OfferDto.builder()
                .id("7b3e02b3-6b1a-4e75-bdad-cef5b279b074")
                .position("Software Engineer - Mobile (m/f/d)")
                .companyName("")
                .salary("4k - 8k PLN")
                .offerUrl("https://nofluffjobs.com/pl/job/software-engineer-mobile-m-f-d-cybersource-poznan-entavdpn")
                .build();
    }


    default OfferDto cyberSourceDtoWithoutPosition() {
        return OfferDto.builder()
                .id("7b3e02b3-6b1a-4e75-bdad-cef5b279b074")
                .companyName("Cybersource")
                .position("")
                .salary("4k - 8k PLN")
                .offerUrl("https://nofluffjobs.com/pl/job/software-engineer-mobile-m-f-d-cybersource-poznan-entavdpn")
                .build();
    }

    default OfferDto cyberSourceDtoWithoutSalary() {
        return OfferDto.builder()
                .id("7b3e02b3-6b1a-4e75-bdad-cef5b279b074")
                .companyName("Cybersource")
                .position("Software Engineer - Mobile (m/f/d)")
                .salary("")
                .offerUrl("https://nofluffjobs.com/pl/job/software-engineer-mobile-m-f-d-cybersource-poznan-entavdpn")
                .build();
    }

    default OfferDto cyberSourceDtoWithoutOfferUrl() {
        return OfferDto.builder()
                .id("7b3e02b3-6b1a-4e75-bdad-cef5b279b074")
                .companyName("Cybersource")
                .position("Software Engineer - Mobile (m/f/d)")
                .salary("4k - 8k PLN")
                .offerUrl("")
                .build();
    }

    default OfferDto cyberSourceDtoWithoutId() {
        return OfferDto.builder()
                .id("")
                .companyName("Cybersource")
                .position("Software Engineer - Mobile (m/f/d)")
                .salary("4k - 8k PLN")
                .offerUrl("https://nofluffjobs.com/pl/job/software-engineer-mobile-m-f-d-cybersource-poznan-entavdpn")
                .build();
    }


    default OfferDto cdqPolandDto() {
        return OfferDto.builder()
                .id("24ee32b6-6b15-11eb-9439-0242ac130002")
                .companyName("CDQ Poland")
                .position("Junior DevOps Engineer")
                .salary("8k - 14k PLN")
                .offerUrl("https://nofluffjobs.com/pl/job/junior-devops-engineer-cdq-poland-wroclaw-gnymtxqd")
                .build();
    }
}
