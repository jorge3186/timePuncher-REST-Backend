package com.jordanalphonso.timePuncher.dao.Info;

public interface GenericInfoDao<T> {
	
	public void saveOrUpdate(long id, T entity);
	
	public T findEntityById(long id);

}
