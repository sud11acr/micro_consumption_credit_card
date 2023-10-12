package com.project.micro.creditcardconsumption.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ConsumptionCreditCardDto {

    private String idConsumptionCreditCard;
    private String idCreditCard;
    private BigDecimal consumptionAmount;
    private Date consumptionDate;
}
