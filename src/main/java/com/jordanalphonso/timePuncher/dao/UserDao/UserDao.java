package com.jordanalphonso.timePuncher.dao.UserDao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.jordanalphonso.timePuncher.model.User;


public class UserDao implements IUserDao{
	
	SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public List<User> listAllUsers() {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		@SuppressWarnings("unchecked")
		List<User> usersList = session.createQuery("from User").list();
		
		Hibernate.initialize(usersList);
		
		tx.commit();
		session.flush();
		session.close();
		
		return usersList;
	}

	@Transactional
	public void addUser(User user) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.persist(user);
		tx.commit();
		session.flush();
		session.close();
		
	}

	@Transactional
	public void updateUser(User user) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.saveOrUpdate(user);
		tx.commit();
		session.flush();
		session.close();
		
	}

	@Transactional
	public void deleteUser(long id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		User user = (User) session.load(User.class, id);
		
		session.delete(user);
		
		tx.commit();
		session.flush();
		session.close();
		
	}

	@Transactional
	public User findUserById(long id) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		User user = session.load(User.class, id);
		Hibernate.initialize(user);
		
		tx.commit();
		session.flush();
		session.close();
		
		return user;
	}

}
