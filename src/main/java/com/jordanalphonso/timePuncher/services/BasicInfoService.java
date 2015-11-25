package com.jordanalphonso.timePuncher.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jordanalphonso.timePuncher.dao.Info.BasicInfoDao;
import com.jordanalphonso.timePuncher.dao.UserDao.UserDao;
import com.jordanalphonso.timePuncher.model.BasicInfo;
import com.jordanalphonso.timePuncher.model.User;

@Service("basicInfoService")
public class BasicInfoService implements IGeneralInfoService<BasicInfo> {
	
	BasicInfoDao basicInfoDao;

	//getters and setters
	public BasicInfoDao getBasicInfoDao() {
		return basicInfoDao;
	}
	@Autowired
	public void setBasicInfoDao(BasicInfoDao basicInfoDao) {
		this.basicInfoDao = basicInfoDao;
	}

	

	//DAO methods
	public void addOrUpdateEntity(long id, BasicInfo entity) {
		basicInfoDao.saveOrUpdate(id, entity);
		
	}

	public BasicInfo findEntity(long id) {
		
		return basicInfoDao.findEntityById(id);
	}

}
