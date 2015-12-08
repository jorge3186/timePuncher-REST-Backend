package com.jordanalphonso.timePuncher.restControllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jordanalphonso.timePuncher.dao.UserDao.IUserService;
import com.jordanalphonso.timePuncher.model.Response;
import com.jordanalphonso.timePuncher.model.User;
import com.jordanalphonso.timePuncher.security.TokenAuthService;
import com.jordanalphonso.timePuncher.security.UserAuthService;
import com.jordanalphonso.timePuncher.security.UserAuthentication;

@RestController
@RequestMapping("/api")
public class LoginRestController {
	
	@Autowired
	UserAuthService<GrantedAuthority> userAuthService;
	@Autowired
	TokenAuthService tokenAuthService;
	@Autowired
	IUserService userService;
	
	
	@CrossOrigin
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Response login(@RequestBody User user, HttpServletResponse response) throws IOException{
		String loginCheck = userService.loginCheck(user.getUsername(), user.getPassword());
		
		try{
			if (loginCheck.equals("Successful login")){
				org.springframework.security.core.userdetails.User authUser = userAuthService.loadUserByUsername(user.getUsername());
				UserAuthentication authentication = new UserAuthentication(authUser);
				tokenAuthService.addAuthentication(response, authentication);
	
				return new Response(1, "Successful Login");
			}
			return new Response(0, "Invalid Credentials");
		}
		catch(Exception e){
			return new Response(0, e.toString());
		}
		
		
	}

}
