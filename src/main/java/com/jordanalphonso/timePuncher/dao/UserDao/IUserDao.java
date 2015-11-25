package com.jordanalphonso.timePuncher.dao.UserDao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.jordanalphonso.timePuncher.model.User;

@Component
public interface IUserDao {
	
	public List<User> listAllUsers();
	
	public void addUser(User user);
	
	public void updateUser(User user);
	
	public void deleteUser(final long id);
	
	public User findUserById(final long id);

}
