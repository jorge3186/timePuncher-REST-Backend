package com.jordanalphonso.timePuncher.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jordanalphonso.timePuncher.dao.Info.GenericInfoDao;
import com.jordanalphonso.timePuncher.model.BasicInfo;

@Component("basicInfoService")
public class BasicInfoService implements IGeneralInfoService<BasicInfo> {
	
	GenericInfoDao<BasicInfo> basicInfoDao;

	public GenericInfoDao<BasicInfo> getBasicInfoDao() {
		return basicInfoDao;
	}
	@Autowired
	public void setBasicInfoDao(GenericInfoDao<BasicInfo> basicInfoDao) {
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
