package com.junioroffers.offer;

import com.junioroffers.offer.domain.dto.OfferDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@RequestMapping("offers")
public class OfferController {

    private OfferService offerService;

    @Autowired
    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }


    @GetMapping
    public ResponseEntity<List<OfferDto>> getOffers() {
        return ResponseEntity.ok(offerService.getOffers());

    }

    @GetMapping("/{id}")
    public ResponseEntity<OfferDto> getOfferById(@PathVariable long id) {
        return ResponseEntity.ok(offerService.getOfferById(id));
    }

}
