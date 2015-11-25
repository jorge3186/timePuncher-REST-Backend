package com.jordanalphonso.timePuncher.restControllers;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jordanalphonso.timePuncher.dao.Punches.PunchesService;
import com.jordanalphonso.timePuncher.model.Hours;
import com.jordanalphonso.timePuncher.model.JsonViews;
import com.jordanalphonso.timePuncher.model.Punches;
import com.jordanalphonso.timePuncher.model.Response;

@RestController
@RequestMapping("/api")
public class PunchesRestController {

	PunchesService punchesService;
	ObjectMapper mapper = new ObjectMapper();

	public PunchesService getPunchesService() {
		return punchesService;
	}
	@Autowired
	public void setPunchesService(PunchesService punchesService) {
		this.punchesService = punchesService;
	}


	/////// PUNCH IN/OUT
	@RequestMapping(value = "/punch/{id}", method = RequestMethod.POST)
	public Response submitPunch(@PathVariable long id, HttpServletRequest request) {
		
		try{
			punchesService.submitPunch(id, request);
			return new Response(1, "User punch success.");
		}
		catch (Exception e){
			return new Response(0, e.toString());
		}
	}
	
	
	////// GET USER'S MOST RECENT PUNCH
	@JsonView(JsonViews.Public.class)
	@RequestMapping(value = "/punch/last/{id}", method = RequestMethod.GET)
	public Punches getLastPunch(@PathVariable long id){
	
		try{
			Punches punch = punchesService.getLastPunch(id);
			return punch;
		}
		catch(Exception e){
			return null;
		}
		
	}
	
	////// GET LIST OF PUNCHES FROM TODAY
	@JsonView(JsonViews.Public.class)
	@RequestMapping(value = "/punch/today/{id}", method = RequestMethod.GET)
	public List<Punches> getAllPunchesFromToday(@PathVariable long id){
		
		try{
			return punchesService.getPunchesFromToday(id);
		}
		catch (Exception e){
			return null;
		}	
	}
	
	//////GET LIST OF PUNCHES FROM User Specified Day
	@JsonView(JsonViews.Public.class)
	@RequestMapping(value = "/punch/{day}/{id}", method = RequestMethod.GET)
	public List<Punches> getAllPunchesFromSpecifiedDay(@PathVariable("day") String day, @PathVariable("id") long id){
		
		System.out.println(day);
		try{
			return punchesService.getPunchesFromDay(day, id);
		}
		catch (Exception e){
			return null;
		}	
	}
	
	////// GET REPORT LIST
	@JsonView(JsonViews.Public.class)
	@RequestMapping(value = "/report/{startDate}/{endDate}/{id}", method = RequestMethod.GET)
	public List<Punches> getReport(@PathVariable("startDate") String beg, 
									@PathVariable("endDate") String end, 
									@PathVariable("id") long id){
		try{
			return punchesService.getPunchesFromRange(beg, end, id);
		}
		catch(Exception e){
			return null;
		}
		
	}
	
	
	/////////////////////// GET DOUBLE(TOTAL HRS Today)
	@JsonView(JsonViews.Public.class)
	@RequestMapping(value = "/hours/today/{id}", method = RequestMethod.GET)
	public Hours getTotalHrsToday(@PathVariable("id") long id){
		Hours hours = new Hours();
		
		BigDecimal totalHrs = punchesService.calcTotalHoursFromToday(id);
		hours.setUser_id(id);
		hours.setHours(totalHrs);

		return hours;
		
	}
	
	
	/////////////////////// GET DOUBLE(TOTAL HRS Today)
	@JsonView(JsonViews.Public.class)
	@RequestMapping(value = "/hours/{day}/{id}", method = RequestMethod.GET)
	public Hours getTotalHrsFromDay(@PathVariable("id") long id, @PathVariable("day") String day){
		Hours hours = new Hours();
		
		BigDecimal totalHrs = punchesService.calcTotalHoursFromSpecifiedDay(id, day);
		hours.setUser_id(id);
		hours.setHours(totalHrs);

		return hours;
	
	}

}
