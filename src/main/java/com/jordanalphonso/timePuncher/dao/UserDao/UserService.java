package com.jordanalphonso.timePuncher.dao.UserDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.jordanalphonso.timePuncher.model.User;

@Service
public class UserService extends ObjectMapper implements IUserService {
	
	@Autowired
	IUserDao userDao;
	
	public UserService(){
		registerModule(new Hibernate5Module());
	}

	public IUserDao getUserDao() {
		return userDao;
	}
	
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public List<User> listAllUsers() {

		List<User> userList = userDao.listAllUsers();
		return userList;
	}

	public void addUser(User user) {
		
		try{
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


}
