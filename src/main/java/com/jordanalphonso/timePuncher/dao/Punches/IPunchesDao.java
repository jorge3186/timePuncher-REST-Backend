package com.jordanalphonso.timePuncher.dao.Punches;

import java.util.List;

import com.jordanalphonso.timePuncher.model.Punches;

public interface IPunchesDao {
	
	public void submitPunch(long id, Punches punch);
	
	public Punches getLastPunch(long id);
	
	public List<Punches> checkIfPunchTableisEmpty(long id);
	
	public List<Punches> getAllPunchesToday(long id);
	
	public List<Punches> getAllPunchesFromDay(String day, long id);
	
	public List<Punches> getPunchesFromRange(String beg, String end, long id);
	
}
