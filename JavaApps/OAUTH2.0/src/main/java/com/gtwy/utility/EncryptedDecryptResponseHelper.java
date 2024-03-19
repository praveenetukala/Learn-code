package com.gtwy.utility;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@Configuration
public class EncryptedDecryptResponseHelper {

	@Autowired
	private EncryptDecryptHelper encryptDecryptHelper;

	@Autowired
	private ObjectMapper objectMapper;

	@Value("${AES.KEY}")
	private String key;
	@Value("${AES.IV}")
	private String iv;

	public Map<String, String> encryptedSuccessResponse(ResponseEntity<?> response, Class<?> service) {

		String encrpt = null;
		String decrpt = null;
		Map<String, String> map = new HashMap<>();
		try {
			ResponseEntity<?> responseEntity = response;
			String resp = objectMapper.writeValueAsString(responseEntity.getBody());
			encrpt = encryptDecryptHelper.encrypt(key, iv, resp);
			decrpt = encryptDecryptHelper.decrypt(key, encrpt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("userResponse", encrpt);
		return map;
	}

	public Map<String, String> encryptedErrorResponse(ResponseEntity<?> response, Class<?> service) {
		String encrpterror = null;
		Map<String, String> map = new HashMap<>();

		try {
			ResponseEntity<?> responseEntity = response;
			String resp = objectMapper.writeValueAsString(responseEntity.getBody());
			encrpterror = encryptDecryptHelper.encrypt(key, iv, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("userResponse", encrpterror);

		return map;
	}

	public String decryptRequest(String decryptClass) {
		String decrptReq = null;
		decrptReq = encryptDecryptHelper.decrypt(key, decryptClass.toString());
		return decrptReq;
	}

}
