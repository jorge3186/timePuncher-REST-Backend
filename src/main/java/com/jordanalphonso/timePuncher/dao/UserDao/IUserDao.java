package com.jordanalphonso.timePuncher.dao.UserDao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jordanalphonso.timePuncher.model.User;

@Repository
public interface IUserDao {
	
	List<User> listAllUsers();
	
	void addUser(User user);
	
	void updateUser(User user);
	
	void deleteUser(final long id);
	
	User findUserById(final long id);
	
	User findByUsername(String username);

}
