package com.project.micro.creditcardconsumption.mapper;

import com.project.micro.creditcardconsumption.integration.ConsumptionCreditCardRequest;
import com.project.micro.creditcardconsumption.integration.ConsumptionCreditCardResponse;
import com.project.micro.creditcardconsumption.model.ConsumptionCreditCard;
import org.springframework.beans.BeanUtils;

public class ConsumptionCreditCardMapper {
    public static ConsumptionCreditCardResponse toConsumptionCreditCardModelResp(ConsumptionCreditCard consumptionCreditCard){
        ConsumptionCreditCardResponse consumptionCreditCardResponse=new ConsumptionCreditCardResponse();
        BeanUtils.copyProperties(consumptionCreditCard,consumptionCreditCardResponse);
        return consumptionCreditCardResponse;
    }

    public static ConsumptionCreditCard toConsumptionCreditCardReqModel(ConsumptionCreditCardRequest consumptionCreditCardRequest){
        ConsumptionCreditCard consumptionCreditCard=new ConsumptionCreditCard();
        BeanUtils.copyProperties(consumptionCreditCardRequest,consumptionCreditCard);
        return consumptionCreditCard;

    }
}
