package com.gtwy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
//@EnableEurekaClient
public class SecurityGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityGatewayApplication.class, args);
		System.out.println("Security gateway application landed.....!");
	}

}
