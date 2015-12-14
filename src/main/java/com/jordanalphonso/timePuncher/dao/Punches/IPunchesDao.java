package com.jordanalphonso.timePuncher.dao.Punches;

import java.util.List;

import com.jordanalphonso.timePuncher.model.Punches;

public interface IPunchesDao {
	
	void submitPunch(long id, Punches punch);
	
	Punches getLastPunch(long id);
	
	List<Punches> checkIfPunchTableisEmpty(long id);
	
	List<Punches> getAllPunchesToday(long id);
	
	List<Punches> getAllPunchesFromDay(String day, long id);
	
	List<Punches> getPunchesFromRange(String beg, String end, long id);
	
}
