package com.project.micro.creditcardconsumption.service;

import com.project.micro.creditcardconsumption.integration.ConsumptionCreditCardRequest;
import com.project.micro.creditcardconsumption.integration.ConsumptionCreditCardResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IConsumptionCreditCardService {

    Mono<ConsumptionCreditCardResponse> findById(String id);
    Flux<ConsumptionCreditCardResponse> findAll();
    Mono<ConsumptionCreditCardResponse>save(String id,Mono<ConsumptionCreditCardRequest> creditPaymentRequest);
    Mono<ConsumptionCreditCardResponse>update(String id,Mono<ConsumptionCreditCardRequest> creditPaymentRequest);
    Mono<Void>delete(String id);
}
