package com.ctel.config;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ctel.model.KongConfigReq;

@RestController
public class KongAdminConfig {

	@RequestMapping("/get")
	public void fetchInfo() {

		RestTemplate restTemplate = new RestTemplate();

		try {
			String apiListResp = restTemplate.getForObject("http://localhost:8001/", String.class);
			System.out.println(apiListResp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/add")
	public void add() {

		KongConfigReq configReq = new KongConfigReq();
		configReq.setName("stocksapi");
		configReq.setHosts("stockapi");
		configReq.setUpstream_url("http://localhost:8081");
		configReq.setUris("/");

		RestTemplate restTemplate = new RestTemplate();

		try {
			HttpEntity<KongConfigReq> apiEntity = new HttpEntity<>(configReq);
			ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8001/services", apiEntity,
					String.class);
			System.out.println("--------->>>:  " + response);
		} catch (Exception e) {
			System.out.println("Exception ocuured");
			e.printStackTrace();
		}
	}
}
