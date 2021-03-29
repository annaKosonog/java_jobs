package com.junioroffers.offer;

import com.junioroffers.offer.domain.dto.OfferDto;
import com.junioroffers.offer.domain.exceptions.OfferErrorResponse;
import com.junioroffers.offer.domain.exceptions.OfferNotFoundException;
import com.junioroffers.offer.domain.mappers.OfferMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OfferService {

    private OfferMapper offerMapper;

    public OfferService(OfferMapper offerMapper) {
        this.offerMapper = offerMapper;
    }

    public List<OfferDto> getOffers() {
        final OfferDto cyberSourceDTO = offerMapper.mapToOfferDto(UUID.fromString("rfkofurijfiuoi324324324"),
                "Software Engineer - Mobile (m/f/d",
                "Cybersource", "4k - 8k PLN",
                "https://nofluffjobs.com/pl/job/software-engineer-mobile-m-f-d-cybersource-poznan-entavdpn");
        final OfferDto cdqPolandDTO = offerMapper.mapToOfferDto(UUID.fromString("3324fjwjfhufhskjh"),
                "Junior DevOps Engineer", "CDQ Poland",
                "8k - 14k PLN", "https://nofluffjobs.com/pl/job/junior-devops-engineer-cdq-poland-wroclaw-gnymtxqd");
        return Arrays.asList(cyberSourceDTO, cdqPolandDTO);
    }

    public OfferDto getOfferById(long id) {
    if(id ==1L){
        return  offerMapper.mapToOfferDto(UUID.fromString("rfkofurijfiuoi324324324"),
                "Software Engineer - Mobile (m/f/d",
                "Cybersource", "4k - 8k PLN",
                "https://nofluffjobs.com/pl/job/software-engineer-mobile-m-f-d-cybersource-poznan-entavdpn");
    }
    else if (id ==2L){
        return   offerMapper.mapToOfferDto(UUID.fromString("3324fjwjfhufhskjh"),
                    "Junior DevOps Engineer", "CDQ Poland",
                    "8k - 14k PLN", "https://nofluffjobs.com/pl/job/junior-devops-engineer-cdq-poland-wroclaw-gnymtxqd");
        }
    throw  new OfferNotFoundException(id);
    }
}
