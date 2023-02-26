package com.junioroffers.offer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("offers")
public class Offer {

    @Id
    private String id;

    @Field("company")
    private String companyName;

    @Field("position")
    private String position;

    @Field("salary")
    private String salary;

    @Field("url")
    private String offerUrl;
}
