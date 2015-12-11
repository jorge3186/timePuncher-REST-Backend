package com.jordanalphonso.timePuncher.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenHandler {
	
	private final String secret;
	private final UserAuthService<GrantedAuthority> userAuthService;
	
	private Map<String, Object> claims = new HashMap<String, Object>();
	private Map<String, Object> header = new HashMap<String,Object>();
	
	public TokenHandler(String secret, UserAuthService<GrantedAuthority> userAuthService){
		this.secret = secret;
		this.userAuthService = userAuthService;
	}
	
	public User parseUserFromToken(String token){
		String username = Jwts.parser()
				.setSigningKey(secret)
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
		return userAuthService.loadUserByUsername(username);
	}
	
	public String createTokenForUser(User user){
		
		claims.put("name", user.getUsername());
		claims.put("active", user.isEnabled());
		
		header.put("alg", SignatureAlgorithm.HS256);
		header.put("typ", "JWT");
		
		return Jwts.builder()
				.setHeaderParams(header)
				.setSubject(user.getUsername())
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

}
