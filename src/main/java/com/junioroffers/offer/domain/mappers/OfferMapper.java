package com.junioroffers.offer.domain.mappers;

import com.junioroffers.infrastracture.model.dto.JobOfferDto;
import com.junioroffers.offer.domain.dao.Offer;
import com.junioroffers.offer.domain.dto.OfferDto;
import org.springframework.stereotype.Component;

@Component
public class OfferMapper {

    public static OfferDto mapToOfferDto(Offer from) {
        return OfferDto.builder()
                .id(from.getId())
                .companyName(from.getCompanyName())
                .position(from.getPosition())
                .salary(from.getSalary())
                .offerUrl(from.getOfferUrl())
                .build();
    }

    public static Offer mapToOffer(JobOfferDto to){
        return Offer.builder()
                .companyName(to.getCompany())
                .position(to.getTitle())
                .salary(to.getSalary())
                .offerUrl(to.getOfferUrl())
                .build();
    }
}
