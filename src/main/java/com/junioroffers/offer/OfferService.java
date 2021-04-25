package com.junioroffers.offer;

import com.junioroffers.infrastracture.model.dto.JobOfferDto;
import com.junioroffers.offer.domain.dao.Offer;
import com.junioroffers.offer.domain.dto.OfferDto;
import com.junioroffers.offer.domain.exceptions.OfferNotFoundException;
import com.junioroffers.offer.domain.mappers.OfferMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OfferService {

    private final OfferRepository offerRepository;

    public List<OfferDto> findAllOffers() {

        return offerRepository.findAll()
                .stream()
                .map(OfferMapper::mapToOfferDto)
                .collect(Collectors.toList());
    }

    public OfferDto findOfferById(String id) {
        return offerRepository.findById(id)
                .map(OfferMapper::mapToOfferDto)
                .orElseThrow(() -> new OfferNotFoundException(id));
    }

    public List<Offer> saveAll(List<Offer> offersList) {
        return offerRepository.saveAll(offersList);
    }

    public List<Offer> saveAllOffers(List<JobOfferDto> jobOfferDto) {
        final List<Offer> offers = conditionsForAddingOffersToTheDatabase(jobOfferDto);
        offerRepository.saveAll(offers);
        return offers;
    }

    public List<Offer> conditionsForAddingOffersToTheDatabase(List<JobOfferDto> jobOfferDto) {
        return jobOfferDto.stream()
                .filter(offerDto -> !offerDto.getOfferUrl().isEmpty())
                .filter(offerDto -> !offerRepository.existsByOfferUrl(offerDto.getOfferUrl()))
                .map(OfferMapper::mapToOffer)
                .collect(Collectors.toList());
    }
}
