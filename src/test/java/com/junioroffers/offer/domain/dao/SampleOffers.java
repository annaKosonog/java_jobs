package com.junioroffers.offer.domain.dao;

public interface SampleOffers {


    default Offer aCrosswordCyberSecurity() {
        return Offer.builder()
                .companyName("Crossword Cybersecurity")
                .position("Junior Java Developer")
                .salary("6k - 8k PLN")
                .offerUrl("https://nofluffjobs.com/pl/job/junior-java-developer-crossword-cybersecurity-krakow-htbplhpd")
                .build();
    }

    default Offer cyberSourceDao() {
        return Offer.builder()
                .id("7b3e02b3-6b1a-4e75-bdad-cef5b279b074")
                .companyName("Cybersource")
                .position("Software Engineer - Mobile (m/f/d)")
                .salary("4k - 8k PLN")
                .offerUrl("https://nofluffjobs.com/pl/job/software-engineer-mobile-m-f-d-cybersource-poznan-entavdpn")
                .build();
    }

    default Offer cdqPolandDao() {
        return Offer.builder()
                .id("24ee32b6-6b15-11eb-9439-0242ac130002")
                .companyName("CDQ Poland")
                .position("Junior DevOps Engineer")
                .salary("8k - 14k PLN")
                .offerUrl("https://nofluffjobs.com/pl/job/junior-devops-engineer-cdq-poland-wroclaw-gnymtxqd")
                .build();
    }

    default Offer aFirstCompanyWithoutId() {
        return Offer.builder()
                .id("")
                .companyName("First company")
                .position("First position")
                .salary("First salary")
                .offerUrl("Unique url")
                .build();
    }

    default Offer aFirstCompany(){
        return Offer.builder()
                .id("35ee32b7-6b15-11eb-9439-0242ac131112")
                .companyName("First company")
                .position("First position")
                .salary("First salary")
                .offerUrl("Unique url")
                .build();
    }
}
