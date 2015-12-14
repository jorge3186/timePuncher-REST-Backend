package com.jordanalphonso.timePuncher.dao.Info;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jordanalphonso.timePuncher.model.BasicInfo;

@Repository
public class BasicInfoDao implements GenericInfoDao<BasicInfo>{
	
	EntityManager manager;

	public EntityManager getManager() {
		return manager;
	}
	@PersistenceContext
	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	@Transactional
	public void saveOrUpdate(long id, BasicInfo entity) {
		
		BasicInfo basicInfo = manager.find(BasicInfo.class, id);
		
		if (basicInfo == null) {
			manager.persist(entity);
		}
		else {
			manager.merge(basicInfo);
		}
		
		manager.flush();
	}

	@Transactional
	public BasicInfo findEntityById(long id) {
		
		BasicInfo basicInfo = manager.find(BasicInfo.class, id);
		manager.flush();
		
		return basicInfo;
	}

}
