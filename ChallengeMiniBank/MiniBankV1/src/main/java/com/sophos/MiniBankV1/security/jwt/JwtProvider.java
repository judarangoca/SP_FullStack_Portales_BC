package com.sophos.MiniBankV1.security.jwt;

import java.nio.charset.MalformedInputException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.sophos.MiniBankV1.security.entity.PrincipalUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;


//This class will generate the Token. 
//Have a validation method which verifies if the token is not deprecated or expired
@Component
public class JwtProvider {
	
	
	//Recommendation: Check
	//https://github.com/koushikkothagal/spring-security-jwt/blob/master/src/main/java/io/javabrains/springsecurityjwt/util/JwtUtil.java

	private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class) ;
	
	private String secret = "secret";
	private int expiration = 36000 ; //in seconds
	
	public String generateToken(Authentication authentication) {
		
		PrincipalUser principalUser = (PrincipalUser) authentication.getPrincipal();
		
		return Jwts.builder()
				.setSubject(principalUser.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + expiration*1000 ) )
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
	}
	

	
	public boolean validateToken(String token) {
		
		try {			
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		} catch (MalformedJwtException e) {
			logger.error("token malformed");
		} catch (UnsupportedJwtException e) {
			logger.error("token not supported");
		} catch (ExpiredJwtException e) {
			logger.error("token expired");
		} catch (IllegalArgumentException e) {
			logger.error("empty token");
		} catch (SignatureException e) {
			logger.error("signature failure");
		}
		
		return false;
	}

//	public String getUsernameFromToken(String token) {
//		return Jwts.parser()
//			.setSigningKey(secret)
//			.parseClaimsJws(token)
//			.getBody()
//			.getSubject();
//	}
	
	 public String getUsernameFromToken(String token) {
		 
		 if(token.startsWith("Bearer ")){
			 token = token.substring(7); //Drift apart "Bearer "
			 }		 
		 return extractClaim(token, Claims::getSubject);
	   }
	
	   public Date extractExpiration(String token) {
	       return extractClaim(token, Claims::getExpiration);
	   }
	
	   public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
	       final Claims claims = extractAllClaims(token);
	       return claimsResolver.apply(claims);
	   }
	   private Claims extractAllClaims(String token) {
	       return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	   }
	
	   private Boolean isTokenExpired(String token) {
	       return extractExpiration(token).before(new Date());
	   }

}
