package com.jordanalphonso.timePuncher.services;

public interface IGeneralInfoService<T> {
	
	public void addOrUpdateEntity(long id, T entity);
		
	public T findEntity(final long id);

}
