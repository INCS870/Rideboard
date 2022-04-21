package com.rideboard.common;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CryptoUtil {
	private static Logger logger = LoggerFactory.getLogger(CryptoUtil.class);
	private final static int AES_KEY_SIZE = 256;
	private final static String ALGORITHM = "AES";

	// make key
	public static byte[] makeKey() {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance(ALGORITHM);
			kgen.init(AES_KEY_SIZE, new SecureRandom());
			Key key = kgen.generateKey();
			return key.getEncoded();
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}

	public static String encrypt(String strToEncrypt) {
		if(strToEncrypt == null) return null;
		try {
			byte[] salt = getSalt();
			//byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			IvParameterSpec ivspec = new IvParameterSpec(salt);
			
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			KeySpec spec = new PBEKeySpec(AES_KEY.toCharArray(), /*SALT.getBytes()*/salt, 65536, 256);
			SecretKey tmp = factory.generateSecret(spec);
			SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
			return bytesToHex(salt) + bytesToHex(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
			//return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
		} catch (Exception e) {
			System.out.println("Error while encrypting: " + e.toString());
		}
		return null;
	}

	public static String decrypt(String strToDecrypt) {
		if(strToDecrypt == null) return null;
		try {
			String saltHex = strToDecrypt.substring(0,32);
			byte[] salt = hexToBytes(saltHex);
//			byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			IvParameterSpec ivspec = new IvParameterSpec(salt);

			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			KeySpec spec = new PBEKeySpec(AES_KEY.toCharArray(), /*SALT.getBytes()*/salt, 65536, 256);
			SecretKey tmp = factory.generateSecret(spec);
			SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
			return new String(cipher.doFinal(hexToBytes(strToDecrypt.substring(32))));
//			return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
		} catch (Exception e) {
			System.out.println("Error while decrypting: " + e.toString());
		}
		return null;
	}

	private static String bytesToHex(byte[] bytes) {
		char[] hexArray = "0123456789ABCDEF".toCharArray();
		char[] hexChars = new char[bytes.length * 2];
		for (int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}

	private static byte[] hexToBytes(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
		}
		return data;
	}
	
	private static byte[] getSalt() {
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[16];
		random.nextBytes(salt);
		//System.out.println("salt: " + bytesToHex(salt));
		return salt;
	}

	public static void main(String args[]) throws Exception {
	}

	private final static String AES_KEY = "09565EFD54A1862A9300CF99FC8C995392FC1279B31BC67B7CD00B566EE15799";
//	private final static String SALT = "~!@#$%^&*()_+`1234567890-=";
}
