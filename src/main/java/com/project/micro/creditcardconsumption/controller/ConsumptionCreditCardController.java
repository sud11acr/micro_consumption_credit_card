package com.project.micro.creditcardconsumption.controller;

import com.project.micro.creditcardconsumption.integration.ConsumptionCreditCardRequest;
import com.project.micro.creditcardconsumption.integration.ConsumptionCreditCardResponse;
import com.project.micro.creditcardconsumption.service.IConsumptionCreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("/consumptionCreditCard")
public class ConsumptionCreditCardController {

    @Autowired
    private IConsumptionCreditCardService service;

    @GetMapping("/findById/{id}")
    public Mono<ResponseEntity<ConsumptionCreditCardResponse>> findById(@PathVariable String id){
        return service.findById(id).map(ResponseEntity::ok).defaultIfEmpty(ResponseEntity.notFound().build());
    }


    @GetMapping("/findAll")
    public Mono<ResponseEntity<Flux<ConsumptionCreditCardResponse>>> findAll() {
        return Mono.just(
                ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(service.findAll()));
    }

    @PostMapping("/create/{id}")
    public Mono<ResponseEntity<ConsumptionCreditCardResponse>>save(@PathVariable String id,@Validated @RequestBody  Mono<ConsumptionCreditCardRequest> consumptionCreditCardRequest){
        return service.save(id,consumptionCreditCardRequest)
                .map(p -> ResponseEntity.created(URI.create("/create".concat("/").concat(p.getIdConsumptionCreditCard())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p));
    }

    @PutMapping("/update/{id}")
    public Mono<ResponseEntity<ConsumptionCreditCardResponse>>update(@PathVariable String id,@RequestBody Mono<ConsumptionCreditCardRequest> creditPaymentRequest ){
        return service.update(id,creditPaymentRequest)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
        return service.delete(id).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
    }


}
