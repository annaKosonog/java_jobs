package com.junioroffers.mongodb.config;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.junioroffers.offer.OfferRepository;
import com.junioroffers.offer.domain.dao.Offer;

import java.util.Arrays;

@ChangeLog(order = "1")
public class ChangelogMongodb {
    @ChangeSet(order = "0,01", id = "exampleDate", author = "anna.kosonóg")
    public void allOffers(OfferRepository offerRepository) {
        offerRepository.insert(Arrays.asList(cyberSource(),cdqPoland()));
    }

    private Offer cyberSource() {
        final Offer cyberSource = new Offer();
        cyberSource.setCompanyName("Cybersource");
        cyberSource.setPosition("Software Engineer - Mobile (m/f/d)");
        cyberSource.setSalary("4k - 8k PLN");
        cyberSource.setOfferUrl("https://nofluffjobs.com/pl/job/software-engineer-mobile-m-f-d-cybersource-poznan-entavdpn");
        return cyberSource;
    }

    private Offer cdqPoland() {
        final Offer cdqPoland = new Offer();
        cdqPoland.setPosition("Junior DevOps Engineer");
        cdqPoland.setCompanyName("CDQ Poland");
        cdqPoland.setSalary("8k - 14k PLN");
        cdqPoland.setOfferUrl("https://nofluffjobs.com/pl/job/junior-devops-engineer-cdq-poland-wroclaw-gnymtxqd");
        return cdqPoland;
    }
}
