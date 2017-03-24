package com.predix.iot.eat.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages="com.predix.iot")
public class PredixEatGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(PredixEatGatewayApplication.class, args);
	}
}
