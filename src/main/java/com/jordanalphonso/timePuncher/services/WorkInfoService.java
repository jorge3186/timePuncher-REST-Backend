package com.jordanalphonso.timePuncher.services;

import org.springframework.stereotype.Service;

import com.jordanalphonso.timePuncher.dao.Info.GenericInfoDao;
import com.jordanalphonso.timePuncher.model.WorkInfo;

@Service("workInfoService")
public class WorkInfoService implements IGeneralInfoService<WorkInfo> {

	GenericInfoDao<WorkInfo> workInfoDao;

	public GenericInfoDao<WorkInfo> getWorkInfoDao() {
		return workInfoDao;
	}

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
