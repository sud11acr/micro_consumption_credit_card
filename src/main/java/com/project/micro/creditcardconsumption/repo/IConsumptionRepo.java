package com.project.micro.creditcardconsumption.repo;

import com.project.micro.creditcardconsumption.model.ConsumptionCreditCard;
import com.project.micro.creditcardconsumption.proxy.bean.CreditCardBean;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface IConsumptionRepo extends ReactiveMongoRepository<ConsumptionCreditCard,String> {

}
