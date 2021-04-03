package com.junioroffers.offer.domain.mappers;

import com.junioroffers.offer.domain.dto.OfferDto;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class OfferMapper {

    public static OfferDto mapToOfferDto(UUID id, String position, String companyName, String salary, String urlOffers) {
        return OfferDto.builder()
                .id(id)
                .position(position)
                .companyName(companyName)
                .salary(salary)
                .url(urlOffers)
                .build();
    }
}
