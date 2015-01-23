package com.movement.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.movement.bussiness.SportsEvent;
import com.movement.service.SportsEventService;
import com.sun.jersey.api.core.ResourceContext;

@Component
@Path("/events")
public class EventsResource {
	
	@Context
	private ResourceContext resourceContext;
	
	@Autowired
	private SportsEventService service;
	
	@GET
	@Produces("application/json;charset=UTF-8")
	public List<SportsEvent> allEvents(){
		
		return service.getAllEvents();
	}
	
	@Path("{id}")
	public EventResource getSportEvent(@PathParam("id") int id){
		SportsEvent event = service.getEventByid(id);
		EventResource eventResource = resourceContext.getResource(EventResource.class);
		eventResource.setEvent(event);
		return eventResource;
	}

}
