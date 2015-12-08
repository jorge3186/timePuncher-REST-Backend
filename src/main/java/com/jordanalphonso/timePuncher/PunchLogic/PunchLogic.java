package com.jordanalphonso.timePuncher.PunchLogic;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jordanalphonso.timePuncher.dao.Punches.IPunchesDao;
import com.jordanalphonso.timePuncher.dao.Punches.PunchesDao;
import com.jordanalphonso.timePuncher.model.Punches;

@Service("punchLogic")
public class PunchLogic {
	
	IPunchesDao punchesDao;
	public IPunchesDao getPunchesDao() {
		return punchesDao;
	}
	@Autowired
	public void setPunchesDao(IPunchesDao punchesDao) {
		this.punchesDao = punchesDao;
	}


	public BigDecimal calcHoursFromToday(long id){
		//dao get all punches from today
		List<Punches> day = punchesDao.getAllPunchesToday(id);
		
		if (day.size() != 0){
			return calculateHours(id, day);	
		}
		else return new BigDecimal(0);		
	}
	
	public BigDecimal calcHoursFromDay(long id, String day){
		//dao get all punces from specified day
		List<Punches> specificDay = punchesDao.getAllPunchesFromDay(day, id);
		
		if (specificDay.size() != 0){
			return calculateHours(id, specificDay);
		}
		else {
			return new BigDecimal(0);
		}
		
		
		
	}
	
	
	
	////// calculate day's total worked hours
	public BigDecimal calculateHours(long id, List<Punches> day){
		
		//joda formatter for time intervals
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

		//declare seperate lists for in and out punches
		List<String> timesIn = new ArrayList<String>();
		List<String> timesOut = new ArrayList<String>();
		
		////// seperate the in times and the out times
		int i = 0;
		for (Punches p : day){
			String timeF = p.getTime().toString().substring(0, 19);
			i++;
			
			if (p.getStatus().equals("IN")){
				timesIn.add(timeF);
			}
			else {
				timesOut.add(timeF);
			}
		}

		
		
		DateTime p1;
		DateTime p2;
		
		int timesInSize = timesIn.size();
		int timesOutSize = timesOut.size();
		
		List<Duration> timeList = new ArrayList<Duration>();
		
///////////////////////// DIFFERENT OPTIONS FOR CALCULATING TIME OF GIVEN DAY /////////////////////
		
		//make sure there is 1 punch OUT for every punch IN (and also that the first punch of the day is an IN punch)
		if (timesInSize == timesOutSize && day.get(0).getStatus().equals("IN")){
			//parse and get value of time in minutes then add to list
			for (int p = 0; p < timesOutSize; p++){
				p1 = formatter.parseDateTime(timesOut.get(p));
				p2 = formatter.parseDateTime(timesIn.get(p));
				Duration t = new Duration(p2, p1);
				timeList.add(t);
			}
		}
		
		
		//if there are more punch INs than punch OUTS - (meaning they are currently still clocked IN)
		else if (timesInSize > timesOutSize && day.get(0).getStatus().equals("IN")) {
			//grab current formatted time and use that as FAKE last punch OUT
			LocalTime time = new LocalTime();
			LocalDate date = new LocalDate();
			String now = date.toString() + " " + time.toString().substring(0, 8);
			
			
			for (int p = 0; p < (timesOutSize); p++){
				p1 = formatter.parseDateTime(timesOut.get(p));
				p2 = formatter.parseDateTime(timesIn.get(p));
				Duration t = new Duration(p2, p1);
				timeList.add(t);
			}
			
			/// add last calculation of in punch until now
			p1 = formatter.parseDateTime(timesIn.get(timesInSize-1));
			p2 = formatter.parseDateTime(now);
			Duration t = new Duration(p1, p2);
			timeList.add(t);
		

		}
		
		
		// if there are more IN punches than OUT punches - (meaning user worked through midnight and is currently clocked out)
		else if (timesInSize > timesOutSize && day.get(0).getStatus().equals("OUT")){
			//create midnight - 12:01 AM as first start time
			LocalDate date = new LocalDate();
			String time = "00:00:00";
			String midnight = date.toString() + " " + time;
			
			for (int p = 0; p > (timesOutSize); p++){
				p1 = formatter.parseDateTime(timesOut.get(p));
				p2 = formatter.parseDateTime(timesIn.get(p));
				Duration t = new Duration(p2, p1);
				timeList.add(t);
			}
			
			p1 = formatter.parseDateTime(midnight);
			p2 = formatter.parseDateTime(timesOut.get(0));
			Duration t = new Duration(p1, p2);
			timeList.add(t);
			
		}
		
		
		// if there are more OUT punches than IN punches - (meaning user worked through midnight)
		else if (timesInSize < timesOutSize && day.get(0).getStatus().equals("OUT")){

			
			for (int p = 0; p > (timesOutSize); p++){
				p1 = formatter.parseDateTime(timesOut.get(p));
				p2 = formatter.parseDateTime(timesIn.get(p));
				Duration t = new Duration(p2, p1);
				timeList.add(t);
			}
			
		}

		
		long minutes = 0;
		for (Duration d : timeList){
			long min = d.getStandardMinutes();
			minutes = minutes + min;
		}
		
		
		//pretty format with only 2 decimal places
		double hoursDouble = (Double.valueOf(minutes)/60.00);
		BigDecimal hours = BigDecimal.valueOf(hoursDouble);
		BigDecimal niceNumber = hours.setScale(2, RoundingMode.CEILING);
		
		return niceNumber;
		
		
	}
}
