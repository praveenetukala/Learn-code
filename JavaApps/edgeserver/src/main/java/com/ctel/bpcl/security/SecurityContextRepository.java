package com.ctel.bpcl.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import io.jsonwebtoken.MalformedJwtException;
import reactor.core.publisher.Mono;

/**
 *
 * @author Gowtham G
 */
@Component
public class SecurityContextRepository implements ServerSecurityContextRepository {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public Mono<Void> save(ServerWebExchange swe, SecurityContext sc) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Mono<SecurityContext> load(ServerWebExchange swe) {
		ServerHttpRequest request = swe.getRequest();
		String path = request.getPath().value();
		if (path.equals("/health")) {
			return Mono.empty();
		} else if (path.equals("/fallback")) {
			return Mono.empty();
		}else if (path.equals("/login")) {
			return Mono.empty();
		}else if (path.equals("/authenticate")) {
			return Mono.empty();
		}else if (path.equals("/v1/productionPlain")) {
 			return Mono.empty();
		}else if (path.equals("/v1/couponsAllocateLine")) {
 			return Mono.empty();
		}else if (path.equals("/v1/labelcodesAllocateLine")) {
 			return Mono.empty();
		}else if (path.equals("/v1/hwEquipments")) {
 			return Mono.empty();
		}else if (path.equals("/v1/productionData")) {
 			return Mono.empty();
		}else if (path.equals("/")) {
			return Mono.empty();
		}
		 
	 
		String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			String authToken = authHeader.substring(7);
			Authentication auth = new UsernamePasswordAuthenticationToken(authToken, authToken);
			return this.authenticationManager.authenticate(auth).map((authentication) -> {
				return new SecurityContextImpl(authentication);
			});
		} else {

			throw new MalformedJwtException("JWT strings must contain exactly 2 period characters");

		}
	}

}
