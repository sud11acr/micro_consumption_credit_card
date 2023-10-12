package com.project.micro.creditcardconsumption.service.impl;

import com.project.micro.creditcardconsumption.exception.ErrorException;
import com.project.micro.creditcardconsumption.integration.ConsumptionCreditCardRequest;
import com.project.micro.creditcardconsumption.integration.ConsumptionCreditCardResponse;
import com.project.micro.creditcardconsumption.mapper.ConsumptionCreditCardMapper;
import com.project.micro.creditcardconsumption.model.ConsumptionCreditCard;
import com.project.micro.creditcardconsumption.proxy.bean.CreditCardBean;
import com.project.micro.creditcardconsumption.proxy.service.ICreditCardProxyService;
import com.project.micro.creditcardconsumption.repo.IConsumptionRepo;
import com.project.micro.creditcardconsumption.service.IConsumptionCreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class ConsumptionCreditCardServiceImpl implements IConsumptionCreditCardService {

    @Autowired
    private IConsumptionRepo repo;

    @Autowired
    private ICreditCardProxyService proxy;
    @Override
    public Mono<ConsumptionCreditCardResponse> findById(String id) {
        return repo.findById(id).map(ConsumptionCreditCardMapper::toConsumptionCreditCardModelResp);
    }

    @Override
    public Flux<ConsumptionCreditCardResponse> findAll() {
        return repo.findAll().map(ConsumptionCreditCardMapper::toConsumptionCreditCardModelResp);
    }

    @Override
    public Mono<ConsumptionCreditCardResponse> save(String id,Mono<ConsumptionCreditCardRequest> consumptionCreditCardRequest) {

        return proxy.findByIdCredit(id).
                flatMap(creditCard->{
                    return consumptionCreditCardRequest.map(ConsumptionCreditCardMapper::toConsumptionCreditCardReqModel)
                            .flatMap(p->{
                                p.setRegistrationDate(new Date());
                                p.setModificationDate(new Date());
                                p.setStatus(true);
                                p.setIdCreditCard(p.getIdCreditCard());
                                p.setConsumptionDate(new Date());
                                p.setConsumptionAmount(p.getConsumptionAmount());
                                BigDecimal newBalance=creditCard.getCurrentBalance().subtract(p.getConsumptionAmount());
                                if((newBalance.compareTo(BigDecimal.ZERO))<0){

                                    return Mono.error(new ErrorException("El valor de pago es mayor al saldo pendiente"));
                                }
                                creditUpdate(newBalance,id,creditCard);

                                return repo.save(p);
                            })
                            .map(ConsumptionCreditCardMapper::toConsumptionCreditCardModelResp);

                });


        /*
        return consumptionCreditCardRequest.map(ConsumptionCreditCardMapper::toConsumptionCreditCardReqModel)
                .flatMap(p->{
                    p.setRegistrationDate(new Date());
                    p.setModificationDate(new Date());
                    p.setStatus(true);
                    return repo.save(p);
                })
                .map(ConsumptionCreditCardMapper::toConsumptionCreditCardModelResp);

         */
    }

    @Override
    public Mono<ConsumptionCreditCardResponse> update(String id, Mono<ConsumptionCreditCardRequest> consumptionCreditCardRequest) {
        Mono<ConsumptionCreditCard> monoBody = consumptionCreditCardRequest.map(p-> ConsumptionCreditCardMapper.toConsumptionCreditCardReqModel(p));
        Mono<ConsumptionCreditCard> monoBD = repo.findById(id);

        return monoBD.zipWith(monoBody,(bd,pl)->{
                    bd.setModificationDate(new Date());
                    bd.setIdCreditCard(pl.getIdCreditCard());
                    bd.setConsumptionAmount(pl.getConsumptionAmount());
                    bd.setConsumptionDate(new Date());
                    bd.setModificationDate(new Date());
                    return bd;
                }).flatMap(p->repo.save(p))
                .map(c->ConsumptionCreditCardMapper.toConsumptionCreditCardModelResp(c));
    }

    @Override
    public Mono<Void> delete(String id) {
        return repo.deleteById(id);
    }

    private Mono<Boolean>creditUpdate(BigDecimal balance, String idCredit, CreditCardBean creditCardBean) {
        creditCardBean.setCurrentBalance(balance);
        proxy.updateCredit(idCredit,creditCardBean).subscribe();
        return Mono.just(true);
    }
}
