package com.jordanalphonso.timePuncher.dao.Punches;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jordanalphonso.timePuncher.PunchLogic.PunchLogic;
import com.jordanalphonso.timePuncher.model.Punches;

@Service("punchesService")
public class PunchesService extends ObjectMapper implements IPunchesService {
	
	PunchesDao punchesDao;
	PunchLogic punchLogic;

	public PunchLogic getPunchLogic() {
		return punchLogic;
	}
	@Autowired
	public void setPunchLogic(PunchLogic punchLogic) {
		this.punchLogic = punchLogic;
	}
	public PunchesDao getPunchesDao() {
		return punchesDao;
	}
	@Autowired
	public void setPunchesDao(PunchesDao punchesDao) {
		this.punchesDao = punchesDao;
	}

	public void submitPunch(long id, HttpServletRequest request) {
		
		Punches punch = new Punches();
		
		///// ADD USER INPUT NOTES //////////////////////
		try {
			punch = readerForUpdating(punch).readValue(request.getReader());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//grab most recent punch
		if (punchesDao.checkIfPunchTableisEmpty(id) != null){
			
			Punches lastPunch = punchesDao.getLastPunch(id);
			
			//check to see the current status and punch the opposite
			if (lastPunch.getStatus().equals("IN")){
				punch.setStatus("OUT");
			}
			else if (lastPunch.getStatus().equals("OUT")){
				punch.setStatus("IN");
			}
			
		}
	
		//fire off punch!
		punchesDao.submitPunch(id, punch);
		
	}

	
	
	public Punches getLastPunch(long id) {
		
		return punchesDao.getLastPunch(id);
	}
	
	
	public List<Punches> getPunchesFromToday(long id) {
		
		return punchesDao.getAllPunchesToday(id);
	}
	
	
	public List<Punches> getPunchesFromRange(String beg, String end, long id) {
		
		return punchesDao.getPunchesFromRange(beg, end, id);
	}
	
	
	public List<Punches> getPunchesFromDay(String day, long id) {
		
		return punchesDao.getAllPunchesFromDay(day, id);
	}
	
	
	
	public BigDecimal calcTotalHoursFromToday(long id) {
		
		return punchLogic.calcHoursFromToday(id);
		
	}
	
	public BigDecimal calcTotalHoursFromSpecifiedDay(long id, String day) {
		
		return punchLogic.calcHoursFromDay(id, day);
	}
}
