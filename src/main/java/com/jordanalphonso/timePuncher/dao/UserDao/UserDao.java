package com.jordanalphonso.timePuncher.dao.UserDao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jordanalphonso.timePuncher.model.User;

@Repository
public class UserDao implements IUserDao{
	
	EntityManager manager;
	
	public EntityManager getManager() {
		return manager;
	}
	@PersistenceContext
	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	@Transactional
	public List<User> listAllUsers() {
		
		Query query = manager.createQuery(
				"select u from User u", User.class);
				@SuppressWarnings("unchecked")
				List<User> userList = query.getResultList();
		
		return userList;
	}

	@Transactional
	public void addUser(User user) {

		manager.persist(user);

	}

	@Transactional
	public void updateUser(User user) {

		manager.merge(user);
		
	}

	@Transactional
	public void deleteUser(long id) {
		
		User user = manager.find(User.class, id);
		manager.detach(user);
		
	}

	@Transactional
	public User findUserById(long id) {
		
		User user = manager.find(User.class, id);
		return user;
	}

}
