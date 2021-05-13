package com.junioroffers.offer.domain.dao;

import com.junioroffers.offer.domain.dto.OfferDto;

public interface SampleOffers {

    default Offer allParametersOfTheObject(String id, String companyName, String position, String salary, String offerUrl) {
        return new Offer(id, companyName, position, salary, offerUrl);
    }

    default Offer objectParametersWithoutId(String companyName, String position, String salary, String offerUrl) {
        final Offer offer = new Offer();
        offer.setCompanyName(companyName);
        offer.setOfferUrl(offerUrl);
        offer.setPosition(position);
        offer.setSalary(salary);
        return offer;
    }

    default Offer aCrosswordCyberSecurity() {
        return objectParametersWithoutId(
                "Crossword Cybersecurity",
                "Junior Java Developer",
                "6k - 8k PLN",
                "https://nofluffjobs.com/pl/job/junior-java-developer-crossword-cybersecurity-krakow-htbplhpd");
    }

    default Offer aSages() {
        return objectParametersWithoutId(
                "Crossword Cybersecurity",
                "Java Developer",
                "7k - 10k PLN",
                "https://nofluffjobs.com/pl/job/java-developer-sages-warszawa-endmddln");
    }

    default Offer cyberSourceDao() {

        return allParametersOfTheObject("7b3e02b3-6b1a-4e75-bdad-cef5b279b074",
                "Cybersource",
                "Software Engineer - Mobile (m/f/d)",
                "4k - 8k PLN",
                "https://nofluffjobs.com/pl/job/software-engineer-mobile-m-f-d-cybersource-poznan-entavdpn");
    }

    default Offer cdqPolandDao() {
        return allParametersOfTheObject("24ee32b6-6b15-11eb-9439-0242ac130002",
                "CDQ Poland",
                "Junior DevOps Engineer", "8k - 14k PLN",
                "https://nofluffjobs.com/pl/job/junior-devops-engineer-cdq-poland-wroclaw-gnymtxqd");
    }

    default OfferDto allParametersOfTheOfferDto(String id, String companyName,
                                                String position, String salary, String offerUrl) {
        return OfferDto.builder()
                .id(id)
                .companyName(companyName)
                .position(position)
                .salary(salary)
                .offerUrl(offerUrl)
                .build();
    }

    default OfferDto allParametersWhereHaveNotId(String companyName, String position,
                                                 String salary, String offerUrl) {
        return OfferDto.builder()
                .companyName(companyName)
                .position(position)
                .salary(salary)
                .offerUrl(offerUrl)
                .build();
    }

    default OfferDto aFirstCompanyWithId() {
        return allParametersOfTheOfferDto(
                "24ee32b6-6b15-11eb-9439-0242ac130002",
                "First company",
                "First position",
                "First salary",
                "Unique url"
        );
    }

    default OfferDto cyberSourceDto() {

        return allParametersOfTheOfferDto("7b3e02b3-6b1a-4e75-bdad-cef5b279b074",
                "Cybersource",
                "Software Engineer - Mobile (m/f/d)",
                "4k - 8k PLN",
                "https://nofluffjobs.com/pl/job/software-engineer-mobile-m-f-d-cybersource-poznan-entavdpn");
    }

    default OfferDto cyberSourceDtoWithoutCompanyName() {

        return allParametersOfTheOfferDto("7b3e02b3-6b1a-4e75-bdad-cef5b279b074",
                "",
                "Software Engineer - Mobile (m/f/d)",
                "4k - 8k PLN",
                "https://nofluffjobs.com/pl/job/software-engineer-mobile-m-f-d-cybersource-poznan-entavdpn");
    }

    default OfferDto cyberSourceDtoWithoutPosition() {

        return allParametersOfTheOfferDto("7b3e02b3-6b1a-4e75-bdad-cef5b279b074",
                "Cybersource",
                "",
                "4k - 8k PLN",
                "https://nofluffjobs.com/pl/job/software-engineer-mobile-m-f-d-cybersource-poznan-entavdpn");
    }

    default OfferDto cyberSourceDtoWithoutSalary() {

        return allParametersOfTheOfferDto("7b3e02b3-6b1a-4e75-bdad-cef5b279b074",
                "Cybersource",
                "Software Engineer - Mobile (m/f/d)",
                "",
                "https://nofluffjobs.com/pl/job/software-engineer-mobile-m-f-d-cybersource-poznan-entavdpn");
    }

    default OfferDto cyberSourceDtoWithoutOfferUrl() {

        return allParametersOfTheOfferDto("7b3e02b3-6b1a-4e75-bdad-cef5b279b074",
                "Cybersource",
                "Software Engineer - Mobile (m/f/d)",
                "4k - 8k PLN",
                "");
    }

    default OfferDto cyberSourceDtoWithoutId() {
        return allParametersWhereHaveNotId(
                "Cybersource",
                "Software Engineer - Mobile (m/f/d)",
                "4k - 8k PLN",
                "https://nofluffjobs.com/pl/job/software-engineer-mobile-m-f-d-cybersource-poznan-entavdpn");
    }

    default OfferDto Juthisho() {
        return allParametersOfTheOfferDto("609d32365c62d131949856f6",
                "Juthisho",
                "Game position",
                "much",
                "unique_url");
    }
}
