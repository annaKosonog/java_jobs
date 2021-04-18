package com.junioroffers.offer.domain.dao;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@AllArgsConstructor
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
    private String offerUrl;
}
