package com.junioroffers.offer.domain.dao;

public interface SampleOffers {

    default Offer allParametersOfTheObject(String id, String companyName, String position, String salary, String offerUrl) {
        return new Offer(id, companyName, position, salary, offerUrl);
    }

    default Offer objectParametersWithoutId(String companyName, String position, String salary, String offerUrl) {
        final Offer offer = new Offer();
        offer.setCompanyName(companyName);
        offer.setPosition(position);
        offer.setSalary(salary);
        offer.setOfferUrl(offerUrl);
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
}
