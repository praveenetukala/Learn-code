package com.gtwy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

import com.gtwy.utility.CredentialsUtil;

/**
 * Configuration class for setting up an OAuth 2.0 Authorization Server using
 * Spring Security.
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	private final BCryptPasswordEncoder passwordEncoder;
	private final CredentialsUtil credentialsUtil;
	private final AuthenticationManager authenticationManager;

	@Autowired
	public AuthorizationServerConfig(BCryptPasswordEncoder passwordEncoder, CredentialsUtil credentialsUtil,
			AuthenticationManager authenticationManager) {
		this.passwordEncoder = passwordEncoder;
		this.credentialsUtil = credentialsUtil;
		this.authenticationManager = authenticationManager;
	}

	/**
	 * Configures security constraints on the OAuth 2.0 Authorization Server
	 * endpoints.
	 */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.tokenKeyAccess("isAuthenticated()").checkTokenAccess("isAuthenticated()");
	}

	/**
	 * Configures the clients that can access the Authorization Server.
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
				// First client
				.withClient(credentialsUtil.getClient1ClientId()) // Client ID
				.authorizedGrantTypes("client_credentials")
				.secret(passwordEncoder.encode(credentialsUtil.getClient1Secret())) // Client Secret
				.scopes("user_info", "read", "write") // Token Scopes
				.accessTokenValiditySeconds(credentialsUtil.getClient1validityInSec()) // Token Validity
				.autoApprove(false).and()

				// Second client
				.withClient(credentialsUtil.getClient2ClientId()) // Client ID
				.secret(passwordEncoder.encode(credentialsUtil.getClient2Secret())) // Client Secret
				.scopes("user_info", "read", "write") // Token Scopes
				.accessTokenValiditySeconds(credentialsUtil.getClient2validityInSec()) // Token Validity
				.autoApprove(false);
	}

	/**
	 * Configures the endpoints of the Authorization Server.
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager);
	}
}
