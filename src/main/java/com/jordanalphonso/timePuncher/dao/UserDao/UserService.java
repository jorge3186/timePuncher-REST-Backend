package com.jordanalphonso.timePuncher.dao.UserDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.jordanalphonso.timePuncher.model.User;

@Component("userService")
public class UserService extends ObjectMapper implements IUserService {
	
	IUserDao userDao;
	public IUserDao getUserDao() {
		return userDao;
	}
	@Autowired
	@Qualifier(value = "userDao")
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
	
	
	public UserService(){
		registerModule(new Hibernate5Module());
	}

	public List<User> listAllUsers() {

		List<User> userList = userDao.listAllUsers();
		return userList;
	}

	public void addUser(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(11);
		
		try{
			String hashedPW = encoder.encode(user.getPassword());
			user.setPassword(hashedPW);
			userDao.addUser(user);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
	}

	public void updateUser(User user) {
		
		try{
			userDao.updateUser(user);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
	}

	public void deleteUser(final long id) {
		
		try{
			userDao.deleteUser(id);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
	}

	public User findUserById(long id) {
		
		try {
			 User user = userDao.findUserById(id);
			 return user;
		}
		catch (Exception e) {
			return null;
		}
	}
	
	public User findByUsername(String username){
		User user = userDao.findByUsername(username);
		if (user == null){
			return null;
		}
		
			return user;
	}

	public boolean loginCheck(String username, String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(11);
		User user = userDao.findByUsername(username);
		
		if (user != null && encoder.matches(password, user.getPassword())) {
				return true;
			
		}
		else {
			return false; 
		}
		
		
	}


}
