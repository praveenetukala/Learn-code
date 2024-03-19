package com.bpcl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringDataRadisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataRadisApplication.class, args);
		System.out.println("App Landed...!");
	}
}
