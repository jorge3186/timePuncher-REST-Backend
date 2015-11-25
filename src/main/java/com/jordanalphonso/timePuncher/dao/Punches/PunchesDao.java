package com.jordanalphonso.timePuncher.dao.Punches;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.jordanalphonso.timePuncher.model.Punches;
import com.jordanalphonso.timePuncher.model.User;

public class PunchesDao implements IPunchesDao {
	
	SessionFactory sessionFactory;

	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public void submitPunch(long id, Punches punch) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		User user = session.load(User.class, id);
		Hibernate.initialize(user);
		punch.setUser(user);
		punch.setUser_id(id);
		session.save(punch);
		
		tx.commit();
		session.flush();
		session.close();
	}

	@Transactional
	public Punches getLastPunch(long id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		DetachedCriteria Dc = DetachedCriteria.forClass(Punches.class)
				.setProjection(Projections.max("punch_id"));
		
		Criteria c = session.createCriteria(Punches.class);
		Criterion user_id = Restrictions.eq("user_id", id);
		c.add(user_id);
		c.add(Property.forName("punch_id").eq(Dc));
		
		@SuppressWarnings("unchecked")
		List<Punches> punches = c.list();
			
		session.close();
		return punches.get(0);		
	}


	@Transactional
	public List<Punches> getPunchesFromRange(String beg, String end, long id) {
		//create the empty list we will be returning
		List<Punches> fullList = new ArrayList<Punches>();
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		//List<Punches> startDay = getAllPunchesFromDay(beg, id);
		List<Punches> endDay = getAllPunchesFromDay(end, id);
		
		Criteria report = session.createCriteria(Punches.class);
		Criterion user_id = Restrictions.eq("user_id", id);
		Criterion between = Restrictions.between("time",	 beg + " %", end + " %");
		LogicalExpression andExp = Restrictions.and(user_id, between);
		
		report.add(user_id);
		report.add(between);
		report.add(andExp);
		
		@SuppressWarnings("unchecked")
		List<Punches> reportList = report.list();
		
		tx.commit();
		session.flush();
		session.close();
		
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
		
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Criteria punchesToday = session.createCriteria(Punches.class);
		Criterion user_id = Restrictions.eq("user_id", id);
		Criterion today = Restrictions.like("time", formattedDate + " %");
		LogicalExpression andExp = Restrictions.and(user_id, today);
		
		punchesToday.add(user_id);
		punchesToday.add(today);
		punchesToday.add(andExp);
		
		@SuppressWarnings("unchecked")
		List<Punches> todayList = punchesToday.list();
		
		tx.commit();
		session.flush();
		session.close();
		
		return todayList;
	}
	
	@Transactional
	public List<Punches> getAllPunchesFromDay(String day, long id) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Criteria punchesToday = session.createCriteria(Punches.class);
		Criterion user_id = Restrictions.eq("user_id", id);
		Criterion dayC = Restrictions.like("time", day + " %");
		LogicalExpression andExp = Restrictions.and(user_id, dayC);
		
		punchesToday.add(user_id);
		punchesToday.add(dayC);
		punchesToday.add(andExp);
		
		@SuppressWarnings("unchecked")
		List<Punches> punchesFromDay = punchesToday.list();
		
		tx.commit();
		session.flush();
		session.close();
		
		return punchesFromDay;
	}
	@Transactional
	public List<Punches> checkIfPunchTableisEmpty(long id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Criteria c = session.createCriteria(Punches.class);
		Criterion user_id = Restrictions.eq("user_id", id);
		c.add(user_id);
		
		@SuppressWarnings("unchecked")
		List<Punches> punches = c.list();
		
		tx.commit();
		session.flush();
		session.close();
		
		if (punches.isEmpty()){
			return null;
		}
		else{
			return punches;
		}

		
		
	}

}
