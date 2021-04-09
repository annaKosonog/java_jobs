package com.junioroffers.offer.domain.mappers;

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
}
