package com.sophos.MiniBankV1.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.repository.query.QueryLookupStrategy.Key;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenUtils {
	
	private final static String ACCESS_TOKEN_SECRET = "1234abcd" ; 
	private final static Long ACCESS_TOKEN_VALIDITY_SECONDS = (long) (30*24*3600) ;  //30 days
	
	public static String createToken(String nombre, String email) {
	
		long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS*1000; 
		Date expirationDate = new Date(System.currentTimeMillis()+expirationTime);
		
		Map<String, Object> extra = new HashMap<>();
		extra.put("nombre", nombre);
		
		return Jwts.builder()
				.setSubject(email)
				.setExpiration(expirationDate)
				.addClaims(extra)
				.signWith(SignatureAlgorithm.ES256, ACCESS_TOKEN_SECRET.getBytes())
				.compact();
		}
	


}
