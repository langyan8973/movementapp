package com.movement.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.movement.bussiness.User;
import com.movement.bussiness.UserEvent;
import com.movement.service.UserService;
import com.sun.jersey.multipart.FormDataParam;

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
	
	@GET
	@Path("{id}")
	@Produces("application/json;charset=UTF-8")
	public User getById(@PathParam("id") int id){
		
		User user = service.getById(id+"");
		
		return user;
		
	}
	
	@POST
	@Path("/reputably")
	@Consumes("multipart/form-data")
	@Produces("application/json;charset=UTF-8")
	public UserEvent reputably(@FormDataParam("uid") Integer uid,@FormDataParam("eid") Integer eid){
		
		UserEvent userEvent = service.reputably(uid, eid);
		
		return userEvent;
		
	}
	
}
