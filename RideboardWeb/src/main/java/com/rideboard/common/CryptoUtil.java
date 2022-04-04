package com.rideboard.common;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CryptoUtil {
	private static Logger logger = LoggerFactory.getLogger(CryptoUtil.class);
	private final static int AES_KEY_SIZE = 256;
	
	// make key
	public static byte[] makeKey() {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(AES_KEY_SIZE, new SecureRandom());
			Key key = kgen.generateKey();
			return key.getEncoded();
		} catch (Exception e) {
			logger.error("",e);
		}
		return null;
	}

	// encrypt key
	public static boolean encryptAES(File in, File out) {
		boolean reply = false;
		FileInputStream is = null;
		CipherOutputStream os = null;
		SecretKeySpec aeskeySpec = null;
		Cipher 	aesCipher = null;

		try {
			aesCipher = Cipher.getInstance("AES");
			aeskeySpec = new SecretKeySpec(hexToByte(AES_KEY), "AES");
			aesCipher.init(Cipher.ENCRYPT_MODE, aeskeySpec, new SecureRandom());
			is = new FileInputStream(in);
			os = new CipherOutputStream(new FileOutputStream(out), aesCipher);
			copy(is, os);
			os.flush();
			reply = true;
		} catch (Exception e) {
			logger.error("",e);
		} finally {
			aeskeySpec = null; // reset
			try {
				is.close();
			} catch (Exception e) {
			}
			try {
				os.close();
			} catch (Exception e) {
			}
		}
		return reply;
	}

	public static boolean decryptAES(File in, File out) {
		boolean reply = false;
		CipherInputStream is = null;
		FileOutputStream os = null;
		SecretKeySpec aeskeySpec = null;
		Cipher 	aesCipher = null;

		try {
			aesCipher = Cipher.getInstance("AES");
			aeskeySpec = new SecretKeySpec(hexToByte(AES_KEY), "AES");
			aesCipher.init(Cipher.DECRYPT_MODE, aeskeySpec, new SecureRandom());
			is = new CipherInputStream(new FileInputStream(in), aesCipher);
			os = new FileOutputStream(out);
			copy(is, os);
			os.flush();
			reply = true;
		} catch (Exception e) {
			logger.error("",e);
		} finally {
			aeskeySpec = null; // reset
			try {
				is.close();
			} catch (Exception e) {
			}
			try {
				os.close();
			} catch (Exception e) {
			}
		}
		return reply;
	}

	public static String encryptAES(String orgData){
		 if (orgData == null) {
			 return null;
		 }

		 try {
			 byte[] data = encryptAES(orgData.getBytes(StandardCharsets.UTF_8));
			 return data == null? null : bytesToHex(data);//new String(data);
		} catch (Exception ex) {
			logger.error("", ex);
			return null;
		}

	}
	
	public static byte[] encryptAES(byte[] orgData){
		byte encryptedFile[] = null;
		ByteArrayOutputStream bout = null;
		java.io.ByteArrayInputStream is = null;
		CipherOutputStream os = null;
		SecretKeySpec aeskeySpec = null;
		Cipher 	aesCipher = null;

//		logger.info("[EncryptDecryptUtil::encryptAES] start");
		try{
			aesCipher = Cipher.getInstance("AES");
			aeskeySpec = new SecretKeySpec(hexToByte(AES_KEY), "AES");
			aesCipher.init(Cipher.ENCRYPT_MODE, aeskeySpec);
			bout = new java.io.ByteArrayOutputStream();
			os = new CipherOutputStream(bout, aesCipher);
			is = new java.io.ByteArrayInputStream(orgData);
			copy(is,os);
			os.flush();
			os.close();
			bout.flush();
			encryptedFile = bout.toByteArray();
		} catch (Exception e) {
			logger.error("value from encrypt" + new String(orgData), e);
		} finally {
			aeskeySpec = null; // reset
			try {
				os.close();

			} catch (Exception e) {
			}
			try {
				is.close();
			} catch (Exception e) {
			}
			try {
				bout.close();
			} catch (Exception e) {
			}
		}
		return encryptedFile;
	}
	public static String decryptAES(String encData){
		if (encData == null) {
			return null;
		}
		byte[] data = decryptAES(hexToByte(encData));
		try {
			return data == null? null : new String(data, StandardCharsets.UTF_8);
		} catch (Exception ex) {
			logger.error("", ex);
			return null;
		}
	}
	public static byte[] decryptAES(byte[] encData){
		byte decryptedFile[] = null;
		ByteArrayOutputStream os = null;
		CipherInputStream is = null;
		SecretKeySpec aeskeySpec = null;
		Cipher 	aesCipher = null;
		
//		logger.info("[EncryptDecryptUtil::decryptAES] start");
		try{
			aesCipher = Cipher.getInstance("AES");
			aeskeySpec = new SecretKeySpec(hexToByte(AES_KEY), "AES");
			aesCipher.init(Cipher.DECRYPT_MODE, aeskeySpec);
			is = new CipherInputStream(new java.io.ByteArrayInputStream(encData), aesCipher);
			os = new ByteArrayOutputStream();
			copy(is,os);
			os.flush();
			decryptedFile = os.toByteArray();
		} catch (Exception e) {
			logger.error("decryptAES : value " + bytesToHex(encData) + " ",e);
		} finally {
			aeskeySpec = null; // reset
			try {
				is.close();
			} catch (Exception e) {
			}
			try {
				os.close();
			} catch (Exception e) {
			}
		}
		return decryptedFile;
	}
	
	private static void copy(InputStream is, OutputStream os) throws IOException {
		int i = 0;
		byte[] b = new byte[1024];
		while ((i = is.read(b)) != -1) {
			os.write(b, 0, i);
		}
	}
	
	private static String bytesToHex(byte[] bytes) {
		char[] hexArray = "0123456789ABCDEF".toCharArray();
	    char[] hexChars = new char[bytes.length * 2];
	    for ( int j = 0; j < bytes.length; j++ ) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = hexArray[v >>> 4];
	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	    }
	    return new String(hexChars);
	}
	
	private static byte[] hexToByte(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}
	
	@SuppressWarnings("static-access")
	public static void main(String args[]) throws Exception {
		System.out.println(" HEX AES: " + encryptAES(""));
		System.out.println(" ORG AES: " + decryptAES(""));
	}
	
	private final static String AES_KEY = "09565EFD54A1862A9300CF99FC8C995392FC1279B31BC67B7CD00B566EE15799"; 
}
