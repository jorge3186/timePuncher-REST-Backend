package com.jordanalphonso.timePuncher.dao.Punches;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jordanalphonso.timePuncher.model.Punches;

public interface IPunchesService {
	
	void submitPunch(long id, HttpServletRequest notes);

	Punches getLastPunch(long id);
	
	List<Punches> getPunchesFromToday(long id);
	
	BigDecimal calcTotalHoursFromToday(long id);
	
	List<Punches> getPunchesFromDay(String day, long id);
	
	BigDecimal calcTotalHoursFromSpecifiedDay(long id, String day);
	
	List<Punches> getPunchesFromRange(String beg, String end, long id);
}
