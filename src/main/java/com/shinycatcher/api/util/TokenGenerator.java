package com.shinycatcher.api.util;

import java.math.BigInteger;
import java.security.SecureRandom;

public class TokenGenerator {
	
	private TokenGenerator() {}
	
	public static String generateRandomToken() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}

}
