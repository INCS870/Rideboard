package com.rideboard.common;

import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import javax.servlet.http.HttpSession;

import org.bouncycastle.util.encoders.Hex;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class Utils {

	public static String formatDate(java.util.Date date) {
		return new java.text.SimpleDateFormat("yyyy-MM-dd").format(date);
	}
	public static String formatDateTime(java.util.Date date) {
		return new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}
	
	public static String hash(String plain) {
		byte[] hashedraw = new byte[0];
		try{
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			hashedraw = digest.digest(plain.getBytes(StandardCharsets.UTF_8));
		} catch(Exception e) {
			
		}
		return new String(Hex.encode(hashedraw));
	}
	public static void addSession(java.util.Map<String,Object> data) {
		if(data != null && !data.isEmpty()) {
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession();
			for(String key:data.keySet()) {
				session.setAttribute(key, data.get(key));
			}
		}
	}
	public static void addSession(String key, Object obj) {
		if(key != null) {
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession();
			session.setAttribute(key, obj);
		}
	}
	public static Object getSession(String key) {
		if(key != null) {
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession();
			return session.getAttribute(key);
		}
		return null;
	}
	public static void removeSession(String key) {
		if(key != null) {
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession();
			session.removeAttribute(key);
		}
	}

	public static <T,G> void autoMap(G input, T output) throws Exception {
		Method[] inputMethods = null;
		if(input != null && output != null) {
			inputMethods = input.getClass().getMethods();
			for(Method inputMethod:inputMethods) {
				String fieldName = inputMethod.getName();
				if(fieldName.contains("Class") || !fieldName.startsWith("get")) continue;
				Object value = inputMethod.invoke(input);
				if(value != null) {
					Method outputMethod = null;
					try {
						outputMethod = output.getClass().getMethod(fieldName.replaceFirst("get", "set"), inputMethod.getReturnType());
						if (outputMethod != null) {
							outputMethod.invoke(output, value);
						}
					}catch(NoSuchMethodException nse) {
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		String plain = "password";
		System.out.println("\n Plain "+plain+" -- > " + hash(plain));
		
	}
}
