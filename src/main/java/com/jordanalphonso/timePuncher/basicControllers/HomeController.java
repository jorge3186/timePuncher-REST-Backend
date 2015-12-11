package com.jordanalphonso.timePuncher.basicControllers;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private List<String> requestList = new ArrayList<String>();
	private List<String> descList = new ArrayList<String>();
	private List<String> urlList = new ArrayList<String>();
	
	public HomeController(){
		//requests
		for (int i = 0; i < 11; i++){
			requestList.add("GET");
		}
		for (int i = 0; i < 2; i++){
			requestList.add("POST");
		}
		for (int i = 0; i < 4; i++){
			requestList.add("PUT");
		}
		requestList.add("DELETE");
		
		//descriptions
		descList.add("List of All Users");
		descList.add("Get User");
		descList.add("Get User's Basic Info");
		descList.add("Get User's Work Related Info");
		descList.add("Get User's Authority");
		descList.add("Get Most Recent Punch");
		descList.add("List User's Punches From Today");
		descList.add("List Punches From Specified Day");
		descList.add("Get total number of hours worked today.");
		descList.add("Get total number of hours worked on specified day");
		descList.add("Get Time Report");
		descList.add("Punch In/Out");
		descList.add("Add User");
		descList.add("Update User");
		descList.add("Add or Update User's Basic Info");
		descList.add("Add or Update User's Work Info");
		descList.add("Update User's Authority Role");
		descList.add("Delete User");
		
		//urls
		urlList.add("/api/users");
		urlList.add("/api/users/{id}");
		urlList.add("/api/users/basicInfo/{id}");
		urlList.add("/api/users/workInfo/{id}");
		urlList.add("/api/users/authority/{id}");
		urlList.add("/api/punch/last/{id}");
		urlList.add("/api/punch/today/{id}");
		urlList.add("/api/punch/{day}/{id}");
		urlList.add("/api/hours/today/{id}");
		urlList.add("/api/hours/{day}/{id}");
		urlList.add("/api/report/{id}/{startDate}/{endDate}");
		urlList.add("/api/punch/{id}");
		urlList.add("/api/users");
		urlList.add("/api/users/{id}");
		urlList.add("/api/users/basicInfo/{id}");
		urlList.add("/api/users/workInfo/{id}");
		urlList.add("/api/users/authority/{id}");
		urlList.add("/api/users/{id}");
	
		
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		//model.addAttribute("urlBase", "http://localhost:8080/timePuncher");
		model.addAttribute("title", "TimePuncher Web App - Server");
		
		//add maps
		model.addAttribute("requestList", requestList);
		model.addAttribute("descList", descList);
		model.addAttribute("urlList", urlList);
		
		return "home";
	}
	
}
