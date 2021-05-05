package com.junioroffers.offer.domain.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

@Builder
@Getter
@EqualsAndHashCode
public class OfferDto implements Serializable {

    private static final long serialVersionUID = -4856846361193249489L;
    private final String id;
    private final String companyName;
    private final String position;
    private final String salary;
    private final String offerUrl;
}
