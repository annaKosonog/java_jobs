package com.junioroffers.offer.domain.dto;

import lombok.Builder;
import lombok.Getter;
import java.util.UUID;



@Builder
@Getter
public class OfferDto {
    private UUID id;
    private String companyName;
    private String position;
    private String salary;
    private String url;
}
