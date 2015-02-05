package com.movement.resource;

import java.net.URI;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.movement.bussiness.Activity;
import com.movement.bussiness.SportsEvent;
import com.movement.service.ActivityService;
import com.sun.jersey.api.core.ResourceContext;
import com.sun.jersey.multipart.FormDataParam;

@Component
public class ActivitiesResource {
	
	private SportsEvent event;

	public SportsEvent getEvent() {
		return event;
	}

	public void setEvent(SportsEvent event) {
		this.event = event;
	}
	
	@Context
	private ResourceContext resourceContext;

	@Autowired
	private ActivityService service;
	
	@GET
	@Produces("application/json;charset=UTF-8")
	public List<Activity> getByEvent(){
		
		return service.getActivitiesByevent(event);
		
	}
	
	@Path("/around")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Activity> findAround(@QueryParam("x") double x,
			@QueryParam("y") double y, @QueryParam("distance") double distance){
		
		return service.findAround(x, y, distance, event);
		
		
	}
	
	
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public Response create(@FormParam("title") String title,
			@FormParam("content") String content,
			@FormParam("time") String time,
			@FormParam("address") String address,
			@FormParam("x") Double x,
			@FormParam("y") Double y,
			@FormParam("uid") Integer uid,@Context UriInfo uriInfo,
			@Context HttpServletRequest request,
			@Context SecurityContext securityContext){
		
		Activity activity = service.create(title, content, time, address, uid, x,y,event);
		
		return Response.created(URI.create(String.valueOf(activity.getId()))).build();
		
	}
	
	@Path("{id}")
	public ActivityResource getActivity(@PathParam("id") int id){
		
		Activity activity = service.getById(id);
		
		ActivityResource activityResource = resourceContext.getResource(ActivityResource.class);
		
		activityResource.setActivity(activity);
		
		return activityResource;
		
	}
	
}
