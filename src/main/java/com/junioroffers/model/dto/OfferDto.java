package com.junioroffers.model.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class OfferDto {
    String title;
    String company;
    String salary;
    String offerUrl;
}
