package com.jordanalphonso.timePuncher;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTParser {
	
	private Map<String, Object> claims = new HashMap<String, Object>();
	private Map<String, Object> header = new HashMap<String,Object>();
	
	public String createToken(){
		
		claims.put("active", "true");
		
		header.put("alg", SignatureAlgorithm.HS256);
		header.put("typ", "JWT");
		
		String token = Jwts.builder()
		.setHeaderParams(header)
		.setSubject("kerialphonso1@yahoo.com")
		.signWith(SignatureAlgorithm.HS256, "r3hcnup3m1t")
		.compact();
		
		return token;
	}
	
	@Test
	public void parser(){
		Object username = (String) Jwts.parser()
		.setSigningKey("r3hcnup3m1t")
		.parseClaimsJws(createToken())
		.getBody()
		.getSubject();
		
		System.out.println(username);
	}

}
