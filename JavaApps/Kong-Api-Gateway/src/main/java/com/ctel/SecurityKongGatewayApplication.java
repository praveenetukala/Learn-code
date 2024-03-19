package com.ctel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SecurityKongGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityKongGatewayApplication.class, args);
		System.out.println("Kong api getway is started ....!");
	}
}
