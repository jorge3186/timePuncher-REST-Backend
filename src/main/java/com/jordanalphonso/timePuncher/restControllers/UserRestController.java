package com.jordanalphonso.timePuncher.restControllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jordanalphonso.timePuncher.dao.UserDao.UserService;
import com.jordanalphonso.timePuncher.model.JsonViews;
import com.jordanalphonso.timePuncher.model.Response;
import com.jordanalphonso.timePuncher.model.User;

@RestController
@RequestMapping("/api")
public class UserRestController {
	
	UserService userService;
	
	ObjectMapper mapper = new ObjectMapper();

	public UserService getUserService() {
		return userService;
	}
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	
	//add user
	@CrossOrigin
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public Response addUser(@RequestBody User user){
	
		try{
			userService.addUser(user);
			return new Response(1, "User created Successfully.");
		}
		catch(Exception e){
			return new Response(0, e.toString());
		}
	}
	
	//list all users
	@CrossOrigin
	@JsonView(JsonViews.Public.class)
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<User> listAllUsers(HttpServletRequest request, HttpServletResponse response){
		response.addHeader("Access-Control-Allow-Origin", "http://localhost:63342");
		
		List<User> userList =  userService.listAllUsers();
		return userList;
	}
	
	//get user	
	@CrossOrigin
	@JsonView(JsonViews.Public.class)
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public User getUser(@PathVariable(value = "id") long id) {

			User user = userService.findUserById(id);
			
			if (user != null){
			return user;
			}
			else {
				return null;
			}
	}
	
	//delete user
	@CrossOrigin
	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	public Response deleteUser(@PathVariable("id")long id){
		
		try{
			userService.deleteUser(id);
			return new Response(1, "User successfully deleted.");
		}
		catch(Exception e){
			return new Response(0, e.toString());
		}
		
	}
	
	//update user
	@CrossOrigin
	@RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
	public Response updateUser(@PathVariable("id")long id, HttpServletRequest request){
		
		User user = userService.findUserById(id);
		
		try{
			User updatedUser = mapper.readerForUpdating(user).readValue(request.getReader());
			userService.updateUser(updatedUser);
			return new Response(1, "User Successfully updated.");
		}
		catch(Exception e){
			return new Response(0, e.toString());
		}
	}
}
