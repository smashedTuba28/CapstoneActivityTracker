package edu.ycp.cs320.CapstonActivtyTracker.db;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class hashSHA256 {

	/*
	 * class designed using following two sites for references
	 * https://www.baeldung.com/sha-256-hashing-java
	 * https://www.mkyong.com/java/java-sha-hashing-example/
	*/
	
	public static String getHash(String password)  {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hashbytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));
			return bytesToHex(hashbytes);
		}catch (NoSuchAlgorithmException e) {
			System.err.println(e);
		}
		return null;
	}
	
	private static String bytesToHex(byte[] hashbytes) {
		
		StringBuilder builder = new StringBuilder();
		for (byte b: hashbytes) {
			builder.append(String.format("%02x", b));
		}	
		return builder.toString();
	}
}
