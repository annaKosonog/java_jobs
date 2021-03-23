package com.junioroffers.model.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OfferDto {
    String id;
    String title;
    String company;
    String salary;
    String offerUrl;
}
