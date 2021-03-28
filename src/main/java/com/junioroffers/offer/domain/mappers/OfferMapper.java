package com.junioroffers.offer.domain.mappers;

import com.junioroffers.offer.domain.dto.OfferDto;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OfferMapper {

    public OfferDto mapToOfferDto (UUID id, String companyName, String salary, String urlOffers){
        return OfferDto.builder()
                .id(id)
                .companyName(companyName)
                .salary(salary)
                .urlOffers(urlOffers)
                .build();
    }
}
