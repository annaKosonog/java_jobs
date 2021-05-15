package com.junioroffers.offer.domain.dao;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
@EqualsAndHashCode
@Document(collection = "offers")
public class Offer {

    @Id
    private String id;
    @Field(name = "company_name")
    private String companyName;
    private String position;
    private String salary;
    @Indexed(unique = true)
    private String offerUrl;
}
