package com.jordanalphonso.timePuncher.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomSecurityUser extends User{
	
	private long user_id;
	
	public CustomSecurityUser(String username, String password, long user_id, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonLocked, accountNonLocked, accountNonLocked, authorities);
		this.user_id = user_id;

	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	
	
}
