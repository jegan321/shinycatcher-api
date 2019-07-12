package com.shinycatcher.api.util;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * https://www.owasp.org/index.php/Hashing_Java
 * 
 * @author eganj
 */
public class PasswordEncoder {
	
	private PasswordEncoder() {}

	public static EncodingResult encode(String rawPassword) {
		if (StringUtils.isBlank(rawPassword)) {
			throw new RuntimeException("Password cannot be blank");
		}
		byte[] salt = generateSalt();
		byte[] password = hashPassword(rawPassword, salt);
		return new EncodingResult(password, salt);
	}
	
	public static boolean compare(String rawPassword, byte[] salt, byte[] storedEncodedPassword) {
		byte[] encodedPassword = hashPassword(rawPassword, salt);
		return Arrays.equals(encodedPassword, storedEncodedPassword);
	}

	private static byte[] generateSalt() {
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[32];
		random.nextBytes(salt);
		return salt;
	}

	private static byte[] hashPassword(String password, byte[] salt) {
		try {
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
			PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 1000, 256);
			SecretKey key = skf.generateSecret(spec);
			return key.getEncoded();
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static class EncodingResult {
		
		public byte[] password;
		public byte[] salt;
		
		public EncodingResult(byte[] password, byte[] salt) {
			this.password = password;
			this.salt = salt;
		}
	}

}
