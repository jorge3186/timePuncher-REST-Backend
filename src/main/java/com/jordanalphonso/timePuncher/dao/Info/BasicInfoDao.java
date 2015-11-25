package com.jordanalphonso.timePuncher.dao.Info;


import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.jordanalphonso.timePuncher.model.BasicInfo;
import com.jordanalphonso.timePuncher.model.User;

public class BasicInfoDao implements GenericInfoDao<BasicInfo>{
	
	SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public void saveOrUpdate(long id, BasicInfo entity) {
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
	public BasicInfo findEntityById(long id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		BasicInfo basicInfo = session.get(BasicInfo.class, id);
		Hibernate.initialize(basicInfo);
		
		tx.commit();
		session.flush();
		session.close();
		
		return basicInfo;
	}

}
