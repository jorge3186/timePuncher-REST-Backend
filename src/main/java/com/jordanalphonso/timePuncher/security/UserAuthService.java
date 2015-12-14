package com.jordanalphonso.timePuncher.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.jordanalphonso.timePuncher.dao.UserDao.IUserService;
import com.jordanalphonso.timePuncher.model.Authority;
import com.jordanalphonso.timePuncher.services.AuthorityService;

@Service
public class UserAuthService<GrantedAuthrotiy> implements UserDetailsService {
	
	@Autowired
	IUserService userService;
	@Autowired
	AuthorityService authorityService;

	public CustomSecurityUser loadUserByUsername(String username){
		com.jordanalphonso.timePuncher.model.User user = userService.findByUsername(username);
		
		if (user == null){
			throw new UsernameNotFoundException("User not found.");
		}
			return new CustomSecurityUser(
					user.getUsername(), user.getPassword(), user.getUser_id(), user.isEnabled(),
					true, true, true,
					getGrantedAuthorities(user));
		
	}
	
	
	private List<GrantedAuthority> getGrantedAuthorities(com.jordanalphonso.timePuncher.model.User user){
		
		Long user_id = user.getUser_id();
		Authority auth = authorityService.findEntity(user_id);
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(auth.getAuthority()));
		
		return authorities;
	}

}
