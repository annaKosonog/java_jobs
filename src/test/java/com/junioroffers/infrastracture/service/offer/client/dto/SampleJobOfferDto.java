package com.junioroffers.infrastracture.service.offer.client.dto;

import com.junioroffers.infrastracture.model.dto.JobOfferDto;

public interface SampleJobOfferDto {
    default JobOfferDto aJobOfferDto(String offerUrl, String company, String title, String salary){
        return JobOfferDto.builder()
                .offerUrl(offerUrl)
                .company(company)
                .title(title)
                .salary(salary)
                .build();
    }
}
