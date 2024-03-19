package com.gtwy.utility;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CredentialsUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	@Value("${oauth.client1.clienId}")
	private String client1ClientId;

	@Value("${oauth.client2.clienId}")
	private String client2ClientId;

	@Value("${oauth.client1.clientSecret}")
	private String client1Secret;

	@Value("${oauth.client2.clientSecret}")
	private String client2Secret;

	@Value("${oauth.client1.validity}")
	private Integer client1validityInSec;

	@Value("${oauth.client2.validity}")
	private Integer client2validityInSec;

	public String getClient1ClientId() {
		return client1ClientId;
	}

	public void setClient1ClientId(String client1ClientId) {
		this.client1ClientId = client1ClientId;
	}

	public String getClient2ClientId() {
		return client2ClientId;
	}

	public void setClient2ClientId(String client2ClientId) {
		this.client2ClientId = client2ClientId;
	}

	public String getClient1Secret() {
		return client1Secret;
	}

	public void setClient1Secret(String client1Secret) {
		this.client1Secret = client1Secret;
	}

	public String getClient2Secret() {
		return client2Secret;
	}

	public void setClient2Secret(String client2Secret) {
		this.client2Secret = client2Secret;
	}

	public Integer getClient1validityInSec() {
		return client1validityInSec;
	}

	public void setClient1validityInSec(Integer client1validityInSec) {
		this.client1validityInSec = client1validityInSec;
	}

	public Integer getClient2validityInSec() {
		return client2validityInSec;
	}

	public void setClient2validityInSec(Integer client2validityInSec) {
		this.client2validityInSec = client2validityInSec;
	}
}
