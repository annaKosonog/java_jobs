package com.junioroffers.offer;

import com.junioroffers.offer.domain.dto.OfferDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class OfferController {

    @GetMapping("/offers")
    public List<OfferDto> getOffers() {
        return null;
    }

    @GetMapping("/offers/{id}")
    public OfferDto getOfferById(@PathVariable UUID id ) {
        return null;
    }

}
