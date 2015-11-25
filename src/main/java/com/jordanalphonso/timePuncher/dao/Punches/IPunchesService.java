package com.jordanalphonso.timePuncher.dao.Punches;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jordanalphonso.timePuncher.model.Punches;

public interface IPunchesService {
	
	public void submitPunch(long id, HttpServletRequest notes);

	public Punches getLastPunch(long id);
	
	public List<Punches> getPunchesFromToday(long id);
	
	public BigDecimal calcTotalHoursFromToday(long id);
	
	public List<Punches> getPunchesFromDay(String day, long id);
	
	public BigDecimal calcTotalHoursFromSpecifiedDay(long id, String day);
	
	public List<Punches> getPunchesFromRange(String beg, String end, long id);
}
