package com.project.micro.creditcardconsumption;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CreditcardconsumptionApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditcardconsumptionApplication.class, args);
	}

}
