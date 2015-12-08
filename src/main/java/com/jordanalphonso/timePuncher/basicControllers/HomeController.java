package com.jordanalphonso.timePuncher.basicControllers;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private Map<Integer, String> requestList = new HashMap<Integer, String>();
	private Map<Integer, String> descList = new HashMap<Integer, String>();
	private Map<Integer, String> urlList = new HashMap<Integer, String>();
	
	public HomeController(){
		//requests
		requestList.put(1, "GET");
		requestList.put(2, "GET");
		requestList.put(3, "GET");
		requestList.put(4, "GET");
		requestList.put(5, "GET");
		requestList.put(6, "GET");
		requestList.put(7, "GET");
		requestList.put(8, "GET");
		requestList.put(9, "GET");
		requestList.put(10, "GET");
		requestList.put(11, "GET");
		requestList.put(12, "POST");
		requestList.put(13, "POST");
		requestList.put(14, "PUT");
		requestList.put(15, "PUT");
		requestList.put(16, "PUT");
		requestList.put(17, "PUT");
		requestList.put(18, "DELETE");
		
		//descriptions
		descList.put(1, "List of All Users");
		descList.put(2, "Get User");
		descList.put(3, "Get User's Basic Info");
		descList.put(4, "Get User's Work Related Info");
		descList.put(5, "Get User's Authority");
		descList.put(6, "Get Most Recent Punch");
		descList.put(7, "List User's Punches From Today");
		descList.put(8, "List Punches From Specified Day");
		descList.put(9, "Get total number of hours worked today.");
		descList.put(10, "Get total number of hours worked on specified day");
		descList.put(11, "Get Time Report");
		descList.put(12, "Punch In/Out");
		descList.put(13, "Add User");
		descList.put(14, "Update User");
		descList.put(15, "Add or Update User's Basic Info");
		descList.put(16, "Add or Update User's Work Info");
		descList.put(17, "Update User's Authority Role");
		descList.put(18, "Delete User");
		
		//urls
		urlList.put(1, "/api/users");
		urlList.put(2, "/api/users/{id}");
		urlList.put(3, "/api/users/basicInfo/{id}");
		urlList.put(4, "/api/users/workInfo/{id}");
		urlList.put(5, "/api/users/authority/{id}");
		urlList.put(6, "/api/punch/last/{id}");
		urlList.put(7, "/api/punch/today/{id}");
		urlList.put(8, "/api/punch/{day}/{id}");
		urlList.put(9, "/api/hours/today/{id}");
		urlList.put(10, "/api/hours/{day}/{id}");
		urlList.put(11, "/api/report/{id}/{startDate}/{endDate}");
		urlList.put(12, "/api/punch/{id}");
		urlList.put(13, "/api/users");
		urlList.put(14, "/api/users/{id}");
		urlList.put(15, "/api/users/basicInfo/{id}");
		urlList.put(16, "/api/users/workInfo/{id}");
		urlList.put(17, "/api/users/authority/{id}");
		urlList.put(18, "/api/users/{id}");
	
		
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
