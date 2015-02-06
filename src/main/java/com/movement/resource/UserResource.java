package com.movement.resource;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

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
	@Path("/getbyopenid")
	@Consumes("application/x-www-form-urlencoded")
	@Produces("application/json;charset=UTF-8")
	public User getByOpenid(@FormParam("openid") String openid,@FormParam("snstype") int snstype,@Context UriInfo uriInfo,
			@Context HttpServletRequest request,
			@Context SecurityContext securityContext){
		
		User user = service.getByOpenid(openid, snstype);
		
		return user;
		
	}
	
	@POST
	@Path("/reputably")
	@Consumes("application/x-www-form-urlencoded")
	@Produces("application/json;charset=UTF-8")
	public UserEvent reputably(@FormParam("uid") int uid,@FormParam("eid") int eid,@Context UriInfo uriInfo,
			@Context HttpServletRequest request,
			@Context SecurityContext securityContext){
		
		UserEvent userEvent = service.reputably(uid, eid);
		
		return userEvent;
		
	}
	
	@GET
	@Path("{id}/events")
	@Produces("application/json;charset=UTF-8")
	public List<UserEvent> getEvents(@PathParam("id") int id){
		
		User user = service.getById(id+"");
		
		return service.getUserEventsByUid(id);
		
	}
	
}
