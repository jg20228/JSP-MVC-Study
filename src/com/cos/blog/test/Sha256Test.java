package com.cos.blog.test;

import java.security.MessageDigest;

import org.junit.Test;

public class Sha256Test {
	private final static String salt = "코스";

	@Test
	public void encSha256() {
		//임의의 비밀번호
		String plain = "1234";
		//결과값 저장하는곳
		String result = "";
		
		//비밀번호 배열
		byte[] bytePlain = plain.getBytes();
		//salt 배열
		byte[] byteSalt = salt.getBytes();
		
		byte[] bytePlainAndSalt = new byte[bytePlain.length+byteSalt.length];
		
		System.arraycopy
		(
				bytePlain, 
				0, 
				bytePlainAndSalt, 
				0, 
				bytePlain.length
		);
		
		System.arraycopy
		(
				byteSalt, 
				0, 
				bytePlainAndSalt, 
				bytePlain.length, 
				byteSalt.length
		);
		
		try {
			//싱글톤, MessageDigest에 다른 것도 있다.
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			System.out.println(md);
			
			md.update(bytePlainAndSalt);
			System.out.println(md);
			
			byte[] byteData = md.digest();
			
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < bytePlainAndSalt.length; i++) {
				sb.append(Integer.toHexString(byteData[i] & 0xFF)+256).substring(1);
			}
			result = sb.toString();
			
			System.out.println(result);
		} catch (Exception e) {
			
		}
	}
}
