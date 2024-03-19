package com.ctel.bpcl.util;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.ctel.bpcl.exception.GlobalException;

@Component
@Configuration
public class EncryptDecryptHelper {

	@Value("${AES.KEY}")
	private String key;
	@Value("${AES.IV}")
	private String iv ;
	private static String CIPHER_NAME = "AES/CBC/PKCS5PADDING";
	private static int CIPHER_KEY_LEN = 16; // 128 bits
	
	private Logger logger = LoggerFactory.getLogger(EncryptDecryptHelper.class);

	/**
	 * Encrypt data using AES Cipher (CBC) with 128 bit key
	 * 
	 * 
	 * @param key  - key to use should be 16 bytes long (128 bits)
	 * @param iv   - initialization vector
	 * @param data - data to encrypt
	 * @return encryptedData data in base64 encoding with iv attached at end after a
	 *         :
	 */
	
	public  String encrypt(String data) {
		try {
			if (key.length() < EncryptDecryptHelper.CIPHER_KEY_LEN) {
				int numPad = EncryptDecryptHelper.CIPHER_KEY_LEN - key.length();

				for (int i = 0; i < numPad; i++) {
					key += "0"; // 0 pad to len 16 bytes
				}

			} else if (key.length() > EncryptDecryptHelper.CIPHER_KEY_LEN) {
				key = key.substring(0, CIPHER_KEY_LEN); // truncate to 16 bytes
			}

			IvParameterSpec initVector = new IvParameterSpec(iv.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

			Cipher cipher = Cipher.getInstance(EncryptDecryptHelper.CIPHER_NAME);
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, initVector);

			byte[] encryptedData = cipher.doFinal((data.getBytes()));

			String base64_EncryptedData = Base64.getEncoder().encodeToString(encryptedData);
			
			return base64_EncryptedData;
			//String base64_IV = Base64.getEncoder().encodeToString(iv.getBytes("UTF-8"));

			//return base64_EncryptedData + ":" + base64_IV;

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}
	
	public  String encrypt(String key, String iv, String data) {
		try {
			if (key.length() < EncryptDecryptHelper.CIPHER_KEY_LEN) {
				int numPad = EncryptDecryptHelper.CIPHER_KEY_LEN - key.length();

				for (int i = 0; i < numPad; i++) {
					key += "0"; // 0 pad to len 16 bytes
				}

			} else if (key.length() > EncryptDecryptHelper.CIPHER_KEY_LEN) {
				key = key.substring(0, CIPHER_KEY_LEN); // truncate to 16 bytes
			}

			IvParameterSpec initVector = new IvParameterSpec(iv.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

			Cipher cipher = Cipher.getInstance(EncryptDecryptHelper.CIPHER_NAME);
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, initVector);

			byte[] encryptedData = cipher.doFinal((data.getBytes()));

			String base64_EncryptedData = Base64.getEncoder().encodeToString(encryptedData);
			String base64_IV = Base64.getEncoder().encodeToString(iv.getBytes("UTF-8"));

			return base64_EncryptedData + ":" + base64_IV;

		} catch (Exception ex) {
			logger.info("In the Encryption Exception",ex);
			ex.printStackTrace();
		}

		return null;
	}

	/**
	 * Decrypt data using AES Cipher (CBC) with 128 bit key
	 * 
	 * @param key  - key to use should be 16 bytes long (128 bits)
	 * @param data - encrypted data with iv at the end separate by :
	 * @return decrypted data string
	 */

	public  String decrypt(String key, String data) {
		try {

			String[] parts = data.split(":");

			IvParameterSpec iv = new IvParameterSpec(Base64.getDecoder().decode(parts[1]));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

			Cipher cipher = Cipher.getInstance(EncryptDecryptHelper.CIPHER_NAME);
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

			byte[] decodedEncryptedData = Base64.getDecoder().decode(parts[0]);

			byte[] original = cipher.doFinal(decodedEncryptedData);
			
			return new String(original);
		} catch (Exception ex) {
			
			throw new GlobalException(HttpStatus.EXPECTATION_FAILED, "Exception While Decrypting The datas");
		}

//		return null;
	}

	public static void main(String[] args) {
	    	EncryptDecryptHelper encryptDecryptHelper=new EncryptDecryptHelper();
//	    	String data="{\"Queues\":{\"Queue\":[{\"Name\":\"NOTIFICATION\",\"URL\":\"http://10.10.10.171:32001/services/default/GetService/GetData?operationName=Request-Response&ObjectType=NOTIFICATION&LocationId=TT00012&Token=19000101000001&Partition=TTS\",\"LastTransactionDate\":\"2021-01-09T16:59:30.014\",\"LastSyncDate\":\"\",\"Token\":\"19000101000001\"},{\"Name\":\"REMARKS\",\"URL\":\"http://10.10.10.171:32001/services/default/GetService/GetData?operationName=Request-Response&ObjectType=REMARKS&LocationId=TT00012&Token=19000101000001&Partition=TTS\",\"LastTransactionDate\":\"2021-01-09T16:59:33.004\",\"LastSyncDate\":\"\",\"Token\":\"19000101000001\"}]}}";
//	    	String encrptData=encryptDecryptHelper.encrypt(key, iv, data);
//	    	String encrptData=encryptDecryptHelper.encrypt(data);
//	    	System.out.println(" encrptData ===>>>"+ encrptData);
//	    	String decrptData=encryptDecryptHelper.decrypt(key, encrptData);
	    	
	    	
//	    	String dt="{\r\n"
//	    			+ "\"username\":\"\",\r\n"
//	    			+ "\"password\":\" \"\r\n"
//	    			+ "}";
//	    	String encrptData1=encryptDecryptHelper.encrypt(encryptDecryptHelper.key, encryptDecryptHelper.iv, dt);
//	    	System.out.println(" encrptData ===>>>"+encrptData1);
//	    	String decrptData1=encryptDecryptHelper.decrypt(encryptDecryptHelper.key, encrptData1);
//	    	System.out.println(" decrptData ===>>>"+ decrptData1);
//	    	 
	    	    
	
		}

}