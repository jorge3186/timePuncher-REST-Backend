package com.jordanalphonso.timePuncher.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class TokenAuthService {
	
	private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";
	@Autowired
	private final TokenHandler tokenHandler;
	
	public TokenAuthService(String secret, UserAuthService<GrantedAuthority> userAuthSerivce) {
		tokenHandler = new TokenHandler(secret, userAuthSerivce);
	}
	
	public void addAuthentication(HttpServletResponse response, UserAuthentication authentication){
		final CustomSecurityUser user = (CustomSecurityUser) authentication.getDetails();
		//add token created for user
		response.addHeader(AUTH_HEADER_NAME, tokenHandler.createTokenForUser(user));
		//add header to expose token for reading
		response.addHeader("Access-Control-Expose-Headers", AUTH_HEADER_NAME);

	}
	
	
	public Authentication getAuthentication(HttpServletRequest request){
		final String token = request.getHeader(AUTH_HEADER_NAME);
		if (token != null){
			final CustomSecurityUser user = tokenHandler.parseUserFromToken(token);
			if (user != null){
				return new UserAuthentication(user);
			}
		}
		return null;
		
	}

}
