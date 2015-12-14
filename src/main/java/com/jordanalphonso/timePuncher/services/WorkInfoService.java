package com.jordanalphonso.timePuncher.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jordanalphonso.timePuncher.dao.Info.GenericInfoDao;
import com.jordanalphonso.timePuncher.model.WorkInfo;

@Component("workInfoService")
public class WorkInfoService implements IGeneralInfoService<WorkInfo> {

	GenericInfoDao<WorkInfo> workInfoDao;

	public GenericInfoDao<WorkInfo> getWorkInfoDao() {
		return workInfoDao;
	}
	
	@Autowired
	public void setWorkInfoDao(GenericInfoDao<WorkInfo> workInfoDao) {
		this.workInfoDao = workInfoDao;
	}

	public void addOrUpdateEntity(long id, WorkInfo entity) {
		workInfoDao.saveOrUpdate(id, entity);
		
	}

	public WorkInfo findEntity(long id) {

		return workInfoDao.findEntityById(id);
	}

}
