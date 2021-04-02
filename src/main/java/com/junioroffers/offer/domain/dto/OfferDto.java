package com.junioroffers.offer.domain.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Builder
@Getter
@EqualsAndHashCode
public class OfferDto {
    private UUID id;
    private String companyName;
    private String position;
    private String salary;
    private String url;
}
