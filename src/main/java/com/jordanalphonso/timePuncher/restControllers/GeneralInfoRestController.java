package com.jordanalphonso.timePuncher.restControllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.jordanalphonso.timePuncher.model.Authority;
import com.jordanalphonso.timePuncher.model.BasicInfo;
import com.jordanalphonso.timePuncher.model.JsonViews;
import com.jordanalphonso.timePuncher.model.Response;
import com.jordanalphonso.timePuncher.model.WorkInfo;
import com.jordanalphonso.timePuncher.services.AuthorityService;
import com.jordanalphonso.timePuncher.services.BasicInfoService;
import com.jordanalphonso.timePuncher.services.WorkInfoService;

@RestController
@RequestMapping(value = "/api")
public class GeneralInfoRestController {

	BasicInfoService basicInfoService;
	WorkInfoService workInfoService;
	AuthorityService authorityService;
	ObjectMapper mapper = new ObjectMapper();
	
	public BasicInfoService getBasicInfoService() {
		return basicInfoService;
	}
	@Autowired
	public void setBasicInfoService(BasicInfoService basicInfoService) {
		this.basicInfoService = basicInfoService;
	}

	
	public WorkInfoService getWorkInfoService() {
		return workInfoService;
	}
	@Autowired
	public void setWorkInfoService(WorkInfoService workInfoService) {
		this.workInfoService = workInfoService;
	}

	
	public AuthorityService getAuthorityService() {
		return authorityService;
	}
	@Autowired
	public void setAuthorityService(AuthorityService authorityService) {
		this.authorityService = authorityService;
	}


	///////////BASIC INFO ////////////////////////////

	/////// SAVE OR UPDATE BASIC INFO FOR USER WHO IS ALREADY CREATED
	@RequestMapping(value = "/users/basicInfo/{id}", method = {RequestMethod.POST, RequestMethod.PUT})
	public Response addOrUpdateBasicInfo(@PathVariable("id")long id, HttpServletRequest request) throws IOException{
		
		BasicInfo basicInfo = basicInfoService.findEntity(id);
		
		try{
			
			if (basicInfo == null){
				BasicInfo newBasic = mapper.readValue(request.getReader().readLine(), BasicInfo.class);
				newBasic.setUser_id(id);
				basicInfoService.addOrUpdateEntity(id, newBasic);
			}
			else{
				
				BasicInfo newInfo = mapper.readerForUpdating(basicInfo).readValue(request.getReader());
				basicInfoService.addOrUpdateEntity(id, newInfo);
			}
		
			return new Response(1, "User's Basic Info has been saved.");
		}
		catch (Exception e){
			return new Response(0, e.toString());
		}
		
	}
	
	
	
	///// RETURN USER's BASIC INFO
	@JsonView(JsonViews.Public.class)
	@RequestMapping(value = "/users/basicInfo/{id}", method = RequestMethod.GET)
	public BasicInfo getBasicInfo(@PathVariable("id") long id){
		
		try{
			BasicInfo basicInfo = basicInfoService.findEntity(id);
			return basicInfo;
		}
		catch (Exception e){
			return null;
		}
		
		
	}
	
	
	
	
	
	
	//////////////// WORK INFO ///////////////////////////
	
	/////// SAVE OR UPDATE WORK INFO FOR USER WHO IS ALREADY CREATED
	@RequestMapping(value = "/user/workInfo/{id}", method = {RequestMethod.POST, RequestMethod.PUT})
	public Response addOrUpdateWorkInfo(@PathVariable("id")long id, HttpServletRequest request) throws IOException{
		
		WorkInfo workInfo = workInfoService.findEntity(id);
		
		try{
			
			if (workInfo == null){
				WorkInfo newWork = mapper.readValue(request.getReader().readLine(), WorkInfo.class);
				newWork.setUser_id(id);
				workInfoService.addOrUpdateEntity(id, newWork);
			}
			else{
				
				WorkInfo newInfo = mapper.readerForUpdating(workInfo).readValue(request.getReader());
				workInfoService.addOrUpdateEntity(id, newInfo);
			}
		
			return new Response(1, "User's Work Info has been saved.");
		}
		catch (Exception e){
			return new Response(0, e.toString());
		}
		
	}
	
	
	
	///// RETURN USER's WORK INFO
	@JsonView(JsonViews.Public.class)
	@RequestMapping(value = "/users/workInfo/{id}", method = RequestMethod.GET)
	public WorkInfo getWorkInfo(@PathVariable("id") long id){
		
		try{
			WorkInfo workInfo = workInfoService.findEntity(id);
			return workInfo;
		}
		catch (Exception e){
			return null;
		}
		
		
	}
	
	
	
	
	
	
	////////////////  AUTHORITY   ///////////////////////////
	
	/////// SAVE OR UPDATE AUTHORITY FOR USER WHO IS ALREADY CREATED
	@RequestMapping(value = "/users/authority/{id}", method = {RequestMethod.POST, RequestMethod.PUT})
	public Response addOrUpdateAuthority(@PathVariable("id")long id, HttpServletRequest request) throws IOException{
		
		Authority auth = authorityService.findEntity(id);

		try{
			
			if (auth == null){
				Authority newAuth = mapper.readValue(request.getReader().readLine(), Authority.class);
				newAuth.setUser_id(id);
				authorityService.addOrUpdateEntity(id, newAuth);
			}
			else{
				
				Authority updatedAuth = mapper.readerForUpdating(auth).readValue(request.getReader());
				authorityService.addOrUpdateEntity(id, updatedAuth);
			}
		
			return new Response(1, "User's Authority has been saved.");
		}
		catch (Exception e){
			return new Response(0, e.toString());
		}
		
	}
	
	
	
	///// RETURN USER's AUTHORITY
	@JsonView(JsonViews.Public.class)
	@RequestMapping(value = "/users/authority/{id}", method = RequestMethod.GET)
	public Authority getAuthority(@PathVariable("id") long id){
		
		try{
			Authority auth = authorityService.findEntity(id);
			return auth;
		}
		catch (Exception e){
			return null;
		}
		
		
	}
	
	
}