package com.jordanalphonso.timePuncher.services;

import org.springframework.stereotype.Service;

@Service
public interface IGeneralInfoService<T> {
	
	void addOrUpdateEntity(long id, T entity);
		
	T findEntity(final long id);

}
