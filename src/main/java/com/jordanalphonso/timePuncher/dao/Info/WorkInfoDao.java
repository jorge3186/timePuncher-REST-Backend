package com.jordanalphonso.timePuncher.dao.Info;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jordanalphonso.timePuncher.model.User;
import com.jordanalphonso.timePuncher.model.WorkInfo;

@Repository
public class WorkInfoDao implements GenericInfoDao<WorkInfo>{
	
	EntityManager manager;

	public EntityManager getManager() {
		return manager;
	}
	@PersistenceContext
	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	@Transactional
	public void saveOrUpdate(long id, WorkInfo entity) {
		
		WorkInfo workInfo = manager.find(WorkInfo.class, id);
		
		if (workInfo == null){
			manager.persist(entity);
		}
		else {
			manager.merge(workInfo);
		}
		
	}

	@Transactional
	public WorkInfo findEntityById(long id) {
		
		WorkInfo workInfo = manager.find(WorkInfo.class, id);
		
		return workInfo;
	}

}
