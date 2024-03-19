package com.ctel;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ctel.upload.FileConverting;

@SpringBootApplication

public class AzureuploadApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(AzureuploadApplication.class, args);
		
		FileConverting FileConverting=new FileConverting();
		FileConverting.FileConvert();
	}

}
