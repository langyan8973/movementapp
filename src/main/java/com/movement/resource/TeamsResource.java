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

import com.movement.bussiness.Activity;
import com.movement.bussiness.SportsEvent;
import com.movement.bussiness.Team;
import com.movement.service.TeamService;
import com.sun.jersey.api.core.ResourceContext;
import com.sun.jersey.multipart.FormDataParam;

@Component
public class TeamsResource {
	
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
	private TeamService service;
	
	@GET
	@Produces("application/json;charset=UTF-8")
	public List<Team> allTeams(){
		
		return service.getByEvent(event);
	}
	
	@POST
	@Consumes("multipart/form-data")
	public Response create(@FormDataParam("name") String name,
			@FormDataParam("slogan") String slogan,
			@FormDataParam("logo") String logo,
			@FormDataParam("time") String time,
			@FormDataParam("description") String description,
			@FormDataParam("uid") Integer uid){
		
		Team team = service.create(name, slogan, logo, time, description, uid, event);
		
		return Response.created(URI.create(String.valueOf(team.getId()))).build();
		
	}
	
	@Path("{id}")
	public TeamResource geTeam(@PathParam("id") int id){
		
		Team team = service.getById(id);
		
		TeamResource teamResource = resourceContext.getResource(TeamResource.class);
		
		teamResource.setTeam(team);
		
		return teamResource;
		
	}
	
}
