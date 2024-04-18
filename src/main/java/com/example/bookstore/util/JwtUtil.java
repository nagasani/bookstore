package com.example.bookstore.util;

import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtUtil 
{
	//private static final long TOKEN_VALIDITY = 1000 * 60 * 60 * 10; // 10 hours in milliseconds
	//private static final String SECRET_KEY = "your-base64-encoded-secret-key-here"; // Securely manage this key
	private static final long TOKEN_VALIDITY = 1000 * 60 * 5; // 5 Minutes in milliseconds
	private static final String SECRET_KEY = "dGVzdA=="; 

	public String generateToken(String userDetails) 
	{	  
	    return Jwts.builder()
	            .subject(userDetails)
	            .issuedAt(new Date(System.currentTimeMillis()))
	            .expiration(new Date(System.currentTimeMillis() +    TOKEN_VALIDITY))
	            .signWith(getSignInKey())
	            .compact();
	}
	
	private Claims extractAllClaims(String token)
	{
		 return Jwts.parser()
				.verifyWith(getSignInKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
		}
	
	 private SecretKey getSignInKey() 
	 {
		 byte[] decodedKey = Base64.getDecoder().decode(SECRET_KEY);
	     return Keys.hmacShaKeyFor(decodedKey);
	 }	
}
