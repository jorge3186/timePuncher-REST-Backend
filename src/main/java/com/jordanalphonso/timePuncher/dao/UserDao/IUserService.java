package com.jordanalphonso.timePuncher.dao.UserDao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jordanalphonso.timePuncher.model.User;

@Service
public interface IUserService {
	
	List<User> listAllUsers();
	
	void addUser(User user);
	
	void updateUser(User user);
	
	void deleteUser(final long id);
	
	User findUserById(long id);
	
	User findByUsername(String username);
	
	String loginCheck(String username, String password);

}
