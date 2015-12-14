package com.jordanalphonso.timePuncher.dao.UserDao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jordanalphonso.timePuncher.model.User;

@Component("userDao")
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
		
		TypedQuery<User> query = manager.createQuery(
				"select u from User u", User.class);

				List<User> userList = query.getResultList();
		
		return userList;
	}

	@Transactional
	public void addUser(User user) {

		manager.persist(user);
		manager.flush();

	}

	@Transactional
	public void updateUser(User user) {

		manager.merge(user);
		manager.flush();
		
	}

	@Transactional
	public void deleteUser(long id) {
		
		User user = manager.find(User.class, id);
		manager.remove(user);
		manager.flush();
		
	}

	@Transactional
	public User findUserById(long id) {
		
		User user = manager.find(User.class, id);
		manager.flush();
		
		return user;
	}
	@Transactional
	public User findByUsername(String username) {
		
		try{
			TypedQuery<User> query = manager.createQuery(
					"select u from User u where username= :username", User.class)
					.setParameter("username", username);
			
			List<User> userList = query.getResultList();
			return userList.get(0);
		}
		catch (Exception e){
			return null;
		}

	}

}
