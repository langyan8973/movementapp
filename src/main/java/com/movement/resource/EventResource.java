package com.movement.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.movement.bussiness.SportsEvent;
import com.movement.bussiness.UserEvent;
import com.movement.service.SportsEventService;
import com.sun.jersey.api.core.ResourceContext;

@Component
public class EventResource {
	
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
	private SportsEventService service;
	
	@GET
	@Produces("application/json;charset=UTF-8")
	public SportsEvent getSingleEvent(){
		return event;
	}

	@Path("/news")
	public NewsResource getNews(){
		
		NewsResource newsResource = resourceContext.getResource(NewsResource.class);
		
		newsResource.setEvent(event);
		
		return newsResource;
		
	}
	
	@Path("/activities")
	public ActivitiesResource getActivities(){
		
		ActivitiesResource activityResource = resourceContext.getResource(ActivitiesResource.class);
		
		activityResource.setEvent(event);
		
		return activityResource;
		
	}
	
	@Path("/competitions")
	public CompetitionsResource getCompetitions(){
		
		CompetitionsResource competitionsResource = resourceContext.getResource(CompetitionsResource.class);
		
		competitionsResource.setEvent(event);
		
		return competitionsResource;
		
	}
	
	@Path("/courses")
	public CourseResource getCourse(){
		
		CourseResource courseResource = resourceContext.getResource(CourseResource.class);
		
		courseResource.setEvent(event);
		
		return courseResource;
		
	}
	
	@Path("/teams")
	public TeamsResource getTeams(){
		
		TeamsResource teamsResource = resourceContext.getResource(TeamsResource.class);
		
		teamsResource.setEvent(event);
		
		return teamsResource;
		
	}
	
	@GET
	@Path("/talents")
	@Produces("application/json;charset=UTF-8")
	public List<UserEvent> getTalents(){
		
		return service.getUserEventsByEvent(event);
		
	}
}
