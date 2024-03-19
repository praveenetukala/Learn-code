package com.pay.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UtilValues {

	@Value("${rzp_key_id}")
	private String rzpKeyId;

	@Value("${rzp_key_secret}")
	private String rzpKeySecret;

	public String getRzpKeyId() {
		return rzpKeyId;
	}

	public String getRzpKeySecret() {
		return rzpKeySecret;
	}
}
