package com.ctel.bpcl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.ctel.bpcl.security.model.AuthRequest;

@SpringBootTest
@ExtendWith({ SpringExtension.class })
@AutoConfigureWebTestClient
class EdgeserverApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	public void testGetHealthInfo() {
		webTestClient.get().uri("/health").accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isOk();
	}

	@Test
	public void testHelloWorld() {
		webTestClient.get().uri("/").accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isUnauthorized();

	}

	@Test
	public void testFallback() {
		webTestClient.get().uri("/fallback").accept(MediaType.APPLICATION_JSON).exchange().expectStatus()
				.isUnauthorized();

	}

	@Test
	public void testLogin() {

		AuthRequest body = new AuthRequest();
		body.setPassword("admin");
		body.setUsername("admin");
		webTestClient.post().uri("/login").accept(MediaType.APPLICATION_JSON).bodyValue(body)
				.accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isOk();

	}

}
