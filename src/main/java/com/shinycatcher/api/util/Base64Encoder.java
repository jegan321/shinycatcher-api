package com.shinycatcher.api.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Base64Encoder {
	
	private Base64Encoder() {}
	
	public static String encode(byte[] rawBytes) {
		return Base64.getEncoder().encodeToString(rawBytes);
	}
	
	public static byte[] decodeAsBytes(String base64String) {
		try {
			return Base64.getDecoder().decode(base64String.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

}
