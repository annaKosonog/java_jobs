package com.junioroffers.offer.domain.mappers;

import com.junioroffers.infrastracture.model.dto.JobOfferDto;
import com.junioroffers.offer.domain.dao.Offer;
import com.junioroffers.offer.domain.dto.OfferDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface OfferMapper {

    OfferMapper INSTANCE = Mappers.getMapper(OfferMapper.class);


    OfferDto mapToOfferDto(Offer from);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "companyName", source = "to.company")
    @Mapping(target = "position", source = "to.title")
    Offer mapToOffer(JobOfferDto to);

    @Mapping(target = "id", ignore = true)
    Offer mapFromOffer(OfferDto from);
}
