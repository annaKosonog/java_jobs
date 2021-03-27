package com.junioroffers.service.offer.client;

import com.junioroffers.model.dto.OfferDto;

public abstract class SampleJobOffer {
    protected OfferDto aOfferDTo(String title, String company, String salary, String offerUrl) {
        return  OfferDto.builder()
                .title(title)
                .company(company)
                .salary(salary)
                .offerUrl(offerUrl)
                .build();
    }
    protected OfferDto emptyOffer(){
        return new OfferDto();
    }


    protected  String aZeroOffer(){
        return "[]";
    }


    protected String aOneOfferJSON(){
        return "[{\n" +
                "    \"title\": \"Software Engineer - Mobile (m/f/d)\",\n" +
                "    \"company\": \"Cybersource\",\n" +
                "    \"salary\": \"4k - 8k PLN\",\n" +
                "    \"offerUrl\": \"https://nofluffjobs.com/pl/job/software-engineer-mobile-m-f-d-cybersource-poznan-entavdpn\"\n" +
                "  }]";
    }

    protected String aTwoOffersJSON(){
        return "[{\n" +
                "    \"title\": \"Software Engineer - Mobile (m/f/d)\",\n" +
                "    \"company\": \"Cybersource\",\n" +
                "    \"salary\": \"4k - 8k PLN\",\n" +
                "    \"offerUrl\": \"https://nofluffjobs.com/pl/job/software-engineer-mobile-m-f-d-cybersource-poznan-entavdpn\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"title\": \"Junior DevOps Engineer\",\n" +
                "    \"company\": \"CDQ Poland\",\n" +
                "    \"salary\": \"8k - 14k PLN\",\n" +
                "    \"offerUrl\": \"https://nofluffjobs.com/pl/job/junior-devops-engineer-cdq-poland-wroclaw-gnymtxqd\"\n" +
                "  }]";
    }

    protected OfferDto aCybersource(){
        return aOfferDTo("Software Engineer - Mobile (m/f/d)", "Cybersource", "4k - 8k PLN",
                "https://nofluffjobs.com/pl/job/software-engineer-mobile-m-f-d-cybersource-poznan-entavdpn");
    }

    protected OfferDto aJuniorDevOpsEngineer(){
        return aOfferDTo("Junior DevOps Engineer", "CDQ Poland", "8k - 14k PLN",
                "https://nofluffjobs.com/pl/job/junior-devops-engineer-cdq-poland-wroclaw-gnymtxqd");
    }
}
