package com.project.micro.creditcardconsumption.proxy.service;

import com.project.micro.creditcardconsumption.proxy.bean.CreditCardBean;
import reactor.core.publisher.Mono;

public interface ICreditCardProxyService {

    Mono<CreditCardBean> findByIdCredit(String idCredit);
    Mono<CreditCardBean>updateCredit(String id,CreditCardBean creditBean);
}
