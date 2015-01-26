package com.movement.resource;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.movement.bussiness.Competition;
import com.movement.bussiness.SportsEvent;
import com.movement.bussiness.Unit;
import com.movement.service.CompetitionService;
import com.sun.jersey.api.core.ResourceContext;

@Component
public class CompetitionsResource {
	
	private SportsEvent event;	
	
	public SportsEvent getEvent() {
		return event;
	}

	public void setEvent(SportsEvent event) {
		this.event = event;
	}

	@Context
	ResourceContext resourceContext;

	@Autowired
	private CompetitionService service;
	
	@GET
	@Produces("application/json;charset=UTF-8")
	public List<Competition> getByEvent(){
		
		return service.getByEvent(event);
		
	}
	
	@Path("{id}")
	public CompetitionResource getCompetition(@PathParam("id") int id){
		
		Competition competition = service.getById(id);
		
		CompetitionResource resource = resourceContext.getResource(CompetitionResource.class);
		
		resource.setCompetition(competition);
		
		return resource;
		
	}
	
	@POST
	@Consumes("application/json")
	public Response create(Competition competition){
		
		competition.setStatus(0);
		
		Competition competition2 = service.saveOrUpdate(competition);
		
		return Response.created(URI.create(String.valueOf(competition2.getId()))).build();
		
	}
}
