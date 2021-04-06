package com.junioroffers.infrastracture.model.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class OfferDto {
    String title;
    String company;
    String salary;
    String offerUrl;
}
