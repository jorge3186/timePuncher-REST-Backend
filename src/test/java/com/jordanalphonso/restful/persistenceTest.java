package com.jordanalphonso.restful;




import com.jordanalphonso.timePuncher.dao.UserDao.UserService;
import com.jordanalphonso.timePuncher.model.User;

public class persistenceTest {

	public static void main(String[] args){
		
		
		User user = new User();
		user.setEnabled(true);
		user.setUsername("jordan");
		user.setPassword("yo");
		
		UserService userService = new UserService();
		userService.addUser(user);
		
	}
	
}
