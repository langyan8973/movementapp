package com.movement.resource;

import java.net.URI;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

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
	
	@POST
	@Consumes("multipart/form-data")
	public Response create(@FormDataParam("title") String title,
			@FormDataParam("content") String content,
			@FormDataParam("time") String time,
			@FormDataParam("address") String address,
			@FormDataParam("uid") Integer uid){
		
		Activity activity = service.create(title, content, time, address, uid, event);
		
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
