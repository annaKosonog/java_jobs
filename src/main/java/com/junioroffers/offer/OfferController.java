package com.junioroffers.offer;

import com.junioroffers.offer.domain.dto.OfferDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/offers")
public class OfferController {

    OfferService offerService;

    @Autowired
    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }


    @GetMapping
    public ResponseEntity<List<OfferDto>> getOffers() {
        return ResponseEntity.ok(offerService.findAllOffers());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OfferDto> getOfferById(@PathVariable String id) {
        return ResponseEntity.ok(offerService.findOfferById(id));
    }

    @PostMapping
    public ResponseEntity<OfferDto> insertOffers(@Validated @RequestBody OfferDto offerDto) {
        return ResponseEntity.ok(offerService.addOffers(offerDto));
    }

}
