package com.gtwy.controller;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.gtwy.model.TokenRequest;
import com.gtwy.model.Response;

@RestController
public class TokenController {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${oauth.token.access.url}")
	private String tokenAccessUrl;

	@PostMapping("/v1/accessToken")
	public ResponseEntity<?> generateToken(@RequestBody TokenRequest tokenRequest) {
		System.out.println("TokenController.generateToken()");

		HttpHeaders headers = new HttpHeaders();

		headers.set("User-Agent", "Mozilla/5.0");
		headers.set("Content-Type", "application/x-www-form-urlencoded");

		StringBuffer buffer = new StringBuffer();

		buffer.append(tokenRequest.getClientId()).append(":").append(tokenRequest.getClientSecret());

		System.out.println("Buffer :  " + buffer);

		String base64Credentials = new String(Base64.encodeBase64(buffer.toString().getBytes()));

		headers.add("Authorization", "Basic " + base64Credentials);

		StringBuilder tokenBody = new StringBuilder();

		tokenBody.append("grant_type=").append(tokenRequest.getGrantType()).append("&").append("scope=")
				.append(tokenRequest.getScope());

		System.out.println("Token body :" + tokenBody);
		System.out.println("Headers :" + headers);

		HttpEntity<String> entity = new HttpEntity<String>(tokenBody.toString(), headers);

		try {
			ResponseEntity<String> response = restTemplate.exchange(tokenAccessUrl, HttpMethod.POST, entity,
					String.class);

			System.out.println("Response :" + response);
			if (response.getStatusCode().is2xxSuccessful()) {
				return response;
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
						new Response<>(HttpStatus.UNAUTHORIZED.value(), "Please provide valid credentials", null));
			}
		} catch (Exception e) {
			System.out.println("Exception occured while getting the Token");
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(new Response<>(HttpStatus.UNAUTHORIZED.value(), "Please provide valid credentials", null));
		}
	}
}
