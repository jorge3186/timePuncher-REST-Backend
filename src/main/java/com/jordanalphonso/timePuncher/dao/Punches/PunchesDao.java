package com.jordanalphonso.timePuncher.dao.Punches;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jordanalphonso.timePuncher.model.Punches;
import com.jordanalphonso.timePuncher.model.User;

@Repository("punchesDao")
public class PunchesDao implements IPunchesDao {
	
	EntityManager manager;

	public EntityManager getManager() {
		return manager;
	}
	@PersistenceContext
	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	@Transactional
	public void submitPunch(long id, Punches punch) {
		
		User user = manager.find(User.class, id);
		
		punch.setUser(user);
		punch.setUser_id(id);
		
		manager.persist(punch);
		manager.flush();
		
	}

	@Transactional
	public Punches getLastPunch(long id) {
		
		Query query = manager.createQuery(
				"select p from Punches p where p.user_id = :user_id and p.punch_id = (select max(p.punch_id) from p)");
				query.setParameter("user_id", id);
				Punches lastPunch = (Punches) query.getSingleResult();
				
		return lastPunch;
		
	}


	@Transactional
	public List<Punches> getPunchesFromRange(String beg, String end, long id) {
		//create the empty list we will be returning
		List<Punches> fullList = new ArrayList<Punches>();
		
		//List<Punches> startDay = getAllPunchesFromDay(beg, id);
		List<Punches> endDay = getAllPunchesFromDay(end, id);
		
		Query query = manager.createQuery(
				"select p from Punches p where p.user_id = :user_id AND " +
				"p.time between :beg and :end");
				query.setParameter("user_id", id);
				query.setParameter("beg", beg + "%");
				query.setParameter("end", end + "%");
				@SuppressWarnings("unchecked")
				List<Punches> reportList = query.getResultList();
						
		//fullList.addAll(startDay);
		fullList.addAll(reportList);
		fullList.addAll(endDay);
		
		
		return fullList;
	}
	
	@Transactional
	public List<Punches> getAllPunchesToday(long id) {
		
		//get today's date - formatted the way mySQL has it in CURRENT_TIMESTAMP()
		Calendar calendar = Calendar.getInstance();
		Timestamp date = new Timestamp(calendar.getTime().getTime());
		String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		
		Query query = manager.createQuery(
				"select p from Punches p where p.user_id = :user_id AND " +
				"p.time like :today");
				query.setParameter("user_id", id);
				query.setParameter("today", (formattedDate + "%"));
				@SuppressWarnings("unchecked")
				List<Punches> todayList = query.getResultList();
				
		
		return todayList;
	}
	
	@Transactional
	public List<Punches> getAllPunchesFromDay(String day, long id) {
		
		Query query = manager.createQuery(
				"select p from Punches p where p.user_id = :user_id AND " +
				"p.time like :day");
				query.setParameter("user_id", id);
				query.setParameter("day", day + "%");
				@SuppressWarnings("unchecked")
				List<Punches> punchesFromDay = query.getResultList();
				
				manager.flush();
		
		return punchesFromDay;
	}
	@Transactional
	public List<Punches> checkIfPunchTableisEmpty(long id) {
		
		Query query = manager.createQuery("select p from Punches p where p.user_id = :user_id");
			query.setParameter("user_id", id);
			@SuppressWarnings("unchecked")
			List<Punches> punches = query.getResultList();
		
		if (punches.isEmpty()){
			return null;
		}
		else{
			return punches;
		}
		
		
		
	}

}
