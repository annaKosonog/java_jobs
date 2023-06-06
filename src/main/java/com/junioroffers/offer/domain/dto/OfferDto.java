package com.junioroffers.offer.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class OfferDto implements Serializable {

    private static final long serialVersionUID = -4856846361193249489L;
    private String id;
    @NotBlank(message = "{companyName.not.blank}")
    private String companyName;
    @NotBlank(message = "{position.not.blank}")
    private String position;
    @NotBlank(message = "{salary.not.blank}")
    private String salary;
    @NotBlank(message = "{offerUrl.not.blank}")
    private String offerUrl;
}
