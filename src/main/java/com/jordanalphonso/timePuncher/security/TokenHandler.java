package com.jordanalphonso.timePuncher.security;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;

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
	
	public CustomSecurityUser parseUserFromToken(String token){
		String username = Jwts.parser()
				.setSigningKey(secret)
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
		return userAuthService.loadUserByUsername(username);
	}
	
	public String createTokenForUser(CustomSecurityUser user){
		
		Collection<GrantedAuthority> authorities = user.getAuthorities();
		for (GrantedAuthority auth : authorities){
			claims.put("auth", auth.getAuthority());
		}
		
		header.put("alg", SignatureAlgorithm.HS256);
		header.put("typ", "JWT");
		
		claims.put("id", user.getUser_id());
		claims.put("sub", user.getUsername());
		
		return Jwts.builder()
				.setHeaderParams(header)
				.setClaims(claims)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

}
