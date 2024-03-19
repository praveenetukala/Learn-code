package com.demo.util;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.stereotype.Component;
import com.demo.bean.SendData;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class EncryptDecryptHelper {

	private static final int CIPHER_KEY_LEN = 16; // 128 bits
	private static String key = "BPclh@mTt$Byel89";

	/**
	 * Return the encrypted data in String format
	 * 
	 * @param data
	 * @return
	 */
	public static String encrypt(String data) {

		try {

			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, generateAESKey());

			byte[] encryptedBytes = cipher.doFinal(data.getBytes("UTF-8"));

			String base64_EncryptedData = Base64.getEncoder().encodeToString(encryptedBytes);
			return base64_EncryptedData;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}// encrypt

	/**
	 * Generate The Secrete key
	 * 
	 * @return
	 * @throws Exception
	 */
	public static SecretKey generateAESKey() throws Exception {
		try {

			if (key.length() < EncryptDecryptHelper.CIPHER_KEY_LEN) {
				int numPad = EncryptDecryptHelper.CIPHER_KEY_LEN - key.length();

				for (int i = 0; i < numPad; i++) {
					key += "0"; // Adding 0 if length islessThen 16 bytes
				}
			} else if (key.length() > EncryptDecryptHelper.CIPHER_KEY_LEN) {
				key = key.substring(0, CIPHER_KEY_LEN); // Remove if length is GreaterThen 16 bytes
			}

			SecretKey secretKey = new SecretKeySpec(key.getBytes("UTF-8"), "AES");// AES is the an Algorithm

			return secretKey;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}// generateAESKey

	/**
	 * Return the decrypted data in String format
	 * 
	 * @param data
	 * @return
	 */
	public static String decrypt(String encryptedData) throws Exception {

		try {

			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, generateAESKey());

			// Decode the Base64 encoded encrypted text
			byte[] encryptedBytes = Base64.getDecoder().decode(encryptedData);

			byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

			// Convert the decrypted bytes back to a string
			return new String(decryptedBytes);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}// decrypt

	public static void main(String[] args) throws Exception {
		try {
			
			System.out.println(decrypt( "da09cd816dcc45876190d68faf48fdb034e3d00cf0773e3e85f3b1b7bd3887b3"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// main method

}
