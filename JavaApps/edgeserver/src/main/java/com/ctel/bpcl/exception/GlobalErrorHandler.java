package com.ctel.bpcl.exception;

import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.server.ServerWebExchange;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import reactor.core.publisher.Mono;

@Configuration
public class GlobalErrorHandler implements ErrorWebExceptionHandler {

	private ObjectMapper objectMapper;

	public GlobalErrorHandler(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Override
	public Mono<Void> handle(ServerWebExchange serverWebExchange, Throwable throwable) {

		DataBufferFactory bufferFactory = serverWebExchange.getResponse().bufferFactory();
		DataBuffer dataBuffer = null;
		try {
			if (throwable instanceof ExpiredJwtException) {
				serverWebExchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);

				dataBuffer = bufferFactory.wrap(objectMapper.writeValueAsBytes(
						new HttpError(serverWebExchange.getResponse().getRawStatusCode(), "Access token is expired",null)));

				serverWebExchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
				  
				return serverWebExchange.getResponse().writeWith(Mono.just(dataBuffer));
			} else if (throwable instanceof MalformedJwtException) {
				serverWebExchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);

				dataBuffer = bufferFactory.wrap(
						objectMapper.writeValueAsBytes(new HttpError(serverWebExchange.getResponse().getRawStatusCode(),
								"JWT strings must contain exactly 2 period characters.",null)));

				serverWebExchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
				return serverWebExchange.getResponse().writeWith(Mono.just(dataBuffer));
			} else {

				serverWebExchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
				serverWebExchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

				dataBuffer = bufferFactory.wrap(objectMapper.writeValueAsBytes(
						new HttpError(serverWebExchange.getResponse().getRawStatusCode(), throwable.getMessage(),null)));

				return serverWebExchange.getResponse().writeWith(Mono.just(dataBuffer));

			}
		} catch (JsonProcessingException e) {
			dataBuffer = bufferFactory.wrap(e.getMessage().getBytes());
			return serverWebExchange.getResponse().writeWith(Mono.just(dataBuffer));

		}
	}

	public class HttpError {

		
		private int status;
		private String message;
		private Object payload;

		HttpError(int status, String message,String payload) {
			this.message = message;
			this.status = status;
			this.payload=payload;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public Object getPayload() {
			return payload;
		}

		public void setPayload(Object payload) {
			this.payload = payload;
		}

	}

}