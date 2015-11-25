package com.jordanalphonso.timePuncher.dao.UserDao;

import java.util.List;

import com.jordanalphonso.timePuncher.model.User;

public interface IUserService {
	
	public List<User> listAllUsers();
	
	public void addUser(User user);
	
	public void updateUser(User user);
	
	public void deleteUser(final long id);
	
	public User findUserById(long id);

}
