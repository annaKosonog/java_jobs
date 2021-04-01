package com.junioroffers.offer;

import com.junioroffers.offer.domain.dto.OfferDto;
import com.junioroffers.offer.domain.exceptions.OfferControllerErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/offers")
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
    @GetMapping(value = "/{id}")
    public ResponseEntity<OfferDto> getOfferById(@PathVariable long id)  {
        return ResponseEntity.ok(offerService.getOfferById(id));
    }

}
