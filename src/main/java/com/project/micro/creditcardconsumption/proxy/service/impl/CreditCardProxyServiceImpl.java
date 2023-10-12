package com.project.micro.creditcardconsumption.proxy.service.impl;

import com.project.micro.creditcardconsumption.proxy.bean.CreditCardBean;
import com.project.micro.creditcardconsumption.proxy.service.ICreditCardProxyService;
import com.project.micro.creditcardconsumption.utils.ExternalProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
@Service
public class CreditCardProxyServiceImpl implements ICreditCardProxyService {

    @Autowired
    private ExternalProperties p;

    @Override
    public Mono<CreditCardBean> findByIdCredit(String idCredit) {
        WebClient webClient= WebClient.builder().baseUrl(p.urlCreditCard).build();
        return webClient.get()
                .uri("/findById/{id}", Collections.singletonMap("id", idCredit))
                .retrieve()
                .bodyToMono(CreditCardBean.class);
    }

    @Override
    public Mono<CreditCardBean> updateCredit(String id, CreditCardBean creditBean) {
        WebClient webClient= WebClient.builder().baseUrl(p.urlCreditCard).build();
        return webClient.put()
                .uri("/update/{id}", Collections.singletonMap("id", id))
                .body(BodyInserters.fromValue(creditBean))
                .retrieve()
                .bodyToMono(CreditCardBean.class);
    }
}
