package com.junioroffers.offer.domain.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Builder
@Getter
@ToString
@EqualsAndHashCode
public class OfferDto implements Serializable {

    private static final long serialVersionUID = -4856846361193249489L;
    private final String id;
    @NotBlank(message = "{companyName.not.blank}")
    private final String companyName;
    @NotBlank(message = "{position.not.blank}")
    private final String position;
    @NotBlank(message = "{salary.not.blank}")
    private final String salary;
    @NotBlank(message = "{offerUrl.not.blank}")
    private final String offerUrl;
}
