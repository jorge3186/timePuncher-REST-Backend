package com.jordanalphonso.timePuncher.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jordanalphonso.timePuncher.dao.Info.WorkInfoDao;
import com.jordanalphonso.timePuncher.model.WorkInfo;

@Service("workInfoService")
public class WorkInfoService implements IGeneralInfoService<WorkInfo> {

	WorkInfoDao workInfoDao;
	
	public WorkInfoDao getWorkInfoDao() {
		return workInfoDao;
	}
	@Autowired
	public void setWorkInfoDao(WorkInfoDao workInfoDao) {
		this.workInfoDao = workInfoDao;
	}

	public void addOrUpdateEntity(long id, WorkInfo entity) {
		workInfoDao.saveOrUpdate(id, entity);
		
	}

	public WorkInfo findEntity(long id) {

		return workInfoDao.findEntityById(id);
	}

}
