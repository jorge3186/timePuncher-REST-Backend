package com.jordanalphonso.timePuncher.dao.Info;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.jordanalphonso.timePuncher.model.Authority;
import com.jordanalphonso.timePuncher.model.User;

public class AuthorityDao implements GenericInfoDao<Authority> {
	
	SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public void saveOrUpdate(long id, Authority entity) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		User user = session.load(User.class, id);
		Hibernate.initialize(user);
		entity.setUser_id(id);
		entity.setUser(user);
		session.saveOrUpdate(entity);
		
		
		tx.commit();
		session.flush();
		session.close();
		
	}

	@Transactional
	public Authority findEntityById(long id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Authority authority = session.get(Authority.class, id);
		Hibernate.initialize(authority);
		
		tx.commit();
		session.flush();
		session.close();
		
		
		return authority;
	}

}
