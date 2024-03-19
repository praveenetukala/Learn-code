package com.demo.runners;

import org.springframework.boot.CommandLineRunner;

import org.springframework.stereotype.Component;

@Component
public class MyCommandLineRunner implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		System.out.println("CommandLineRunner");
		System.out.println(args);
	}
}
