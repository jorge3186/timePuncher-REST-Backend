package com.jordanalphonso.timePuncher.dao.Info;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jordanalphonso.timePuncher.model.Authority;

@Repository
public class AuthorityDao implements GenericInfoDao<Authority> {
	
	EntityManager manager;

	public EntityManager getManager() {
		return manager;
	}
	
	@PersistenceContext
	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	@Transactional
	public void saveOrUpdate(long id, Authority entity) {
		
		Authority auth = manager.find(Authority.class, id);
		
		if (auth == null){
			manager.persist(entity);
		}
		else {
			manager.merge(auth);
		}
		
		manager.flush();
	}

	@Transactional
	public Authority findEntityById(long id) {
		
		Authority auth = manager.find(Authority.class, id);
		manager.flush();
		
		return auth;
		
	}

}
