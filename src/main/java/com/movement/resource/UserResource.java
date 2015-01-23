package com.movement.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.movement.bussiness.User;
import com.movement.service.UserService;

@Component
@Path("/users")
public class UserResource {

	@Autowired
	private UserService service;
	
	@GET
	@Produces("application/json;charset=UTF-8")
	public List<User> allUsers(){
		
		return service.getAllUsers();
	}
	
}
