package com.jordanalphonso.timePuncher.dao.Info;

public interface GenericInfoDao<T> {
	
	void saveOrUpdate(long id, T entity);
	
	T findEntityById(long id);

}
