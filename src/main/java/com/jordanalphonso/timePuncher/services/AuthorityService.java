package com.jordanalphonso.timePuncher.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jordanalphonso.timePuncher.dao.Info.AuthorityDao;
import com.jordanalphonso.timePuncher.dao.Info.GenericInfoDao;
import com.jordanalphonso.timePuncher.model.Authority;

@Service("authorityService")
public class AuthorityService implements IGeneralInfoService<Authority> {

	GenericInfoDao<Authority> authorityDao;

	public GenericInfoDao<Authority> getAuthorityDao() {
		return authorityDao;
	}

	@Autowired
	public void setAuthorityDao(GenericInfoDao<Authority> authorityDao) {
		this.authorityDao = authorityDao;
	}


	public void addOrUpdateEntity(long id, Authority entity) {
		authorityDao.saveOrUpdate(id, entity);
		
	}

	
	public Authority findEntity(long id) {
		
		return authorityDao.findEntityById(id);
	}

}
