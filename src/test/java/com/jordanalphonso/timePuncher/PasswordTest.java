package com.jordanalphonso.timePuncher;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordTest {

	@Test
	public void getPW(){
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(11);
		//BCryptPasswordEncoder encoder2 = new BCryptPasswordEncoder();
		String pw = "admin";
		String hashedPW = encoder.encode(pw);
		System.out.println(hashedPW);

	}
}
