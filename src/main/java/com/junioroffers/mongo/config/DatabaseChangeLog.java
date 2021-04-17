package com.junioroffers.mongo.config;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.junioroffers.offer.OfferRepository;
import com.junioroffers.offer.domain.dao.Offer;

import java.util.Arrays;

@ChangeLog(order = "1")
public class DatabaseChangeLog {

    @ChangeSet(order = "001", id = "dataBase", author = "Sai")
    public void dataBase(OfferRepository offerRepository) {
        offerRepository.insert(Arrays.asList(cyberSource(), cdqPoland()));
    }

    private Offer cyberSource() {
        final Offer cybersource = new Offer();
        cybersource.setId("7b3e02b3-6b1a-4e75-bdad-cef5b279b074");
        cybersource.setOfferUrl("https://nofluffjobs.com/pl/job/software-engineer-mobile-m-f-d-cybersource-poznan-entavdpn");
        cybersource.setPosition("Software Engineer - Mobile (m/f/d)");
        cybersource.setSalary("4k - 8k PLN");
        cybersource.setCompanyName("Cybersource");
        return cybersource;
    }

    private Offer cdqPoland() {
        final Offer cdqSource = new Offer();
        cdqSource.setId("24ee32b6-6b15-11eb-9439-0242ac130002");
        cdqSource.setOfferUrl("https://nofluffjobs.com/pl/job/junior-devops-engineer-cdq-poland-wroclaw-gnymtxqd");
        cdqSource.setPosition("Junior DevOps Engineer");
        cdqSource.setSalary("8k - 14k PLN");
        cdqSource.setCompanyName("CDQ Poland");
        return cdqSource;
    }
}
