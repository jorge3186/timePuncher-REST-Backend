package com.jordanalphonso.timePuncher.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class AuthFilter extends GenericFilterBean {
	
	private final TokenAuthService tokenAuthService;
	
	public AuthFilter(TokenAuthService tokenAuthService){
		this.tokenAuthService = tokenAuthService;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		Authentication authentication = tokenAuthService.getAuthentication(httpRequest);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		filterChain.doFilter(request, response);
		SecurityContextHolder.getContext().setAuthentication(null);
		
	}

}
