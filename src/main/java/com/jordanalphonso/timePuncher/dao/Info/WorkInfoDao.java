package com.jordanalphonso.timePuncher.dao.Info;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.jordanalphonso.timePuncher.model.User;
import com.jordanalphonso.timePuncher.model.WorkInfo;

public class WorkInfoDao implements GenericInfoDao<WorkInfo>{
	
	SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public void saveOrUpdate(long id, WorkInfo entity) {
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
	public WorkInfo findEntityById(long id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		WorkInfo workInfo = session.get(WorkInfo.class, id);
		Hibernate.initialize(workInfo);
		
		tx.commit();
		session.flush();
		session.close();
		
		return workInfo;
	}

}
