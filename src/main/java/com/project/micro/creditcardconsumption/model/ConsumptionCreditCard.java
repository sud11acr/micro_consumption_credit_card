package com.project.micro.creditcardconsumption.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Document(collection = "consumptionCreditCard")
public class ConsumptionCreditCard {
    @Id
    private String idConsumptionCreditCard;
    private String idCreditCard;
    private BigDecimal consumptionAmount;
    private Date consumptionDate;
    private Date registrationDate;
    private Date modificationDate;
    private Boolean status;

}
