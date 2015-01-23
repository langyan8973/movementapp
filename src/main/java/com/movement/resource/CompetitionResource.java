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
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.movement.bussiness.Competition;
import com.movement.bussiness.CompetitionTeam;
import com.movement.service.CompetitionService;
import com.sun.jersey.api.core.ResourceContext;
import com.sun.jersey.multipart.FormDataParam;

@Component
public class CompetitionResource {
	
	private Competition competition;

	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}
	
	@Context
	private ResourceContext resourceContext;

	@Autowired
	private CompetitionService service;
	
	@GET
	@Produces("application/json;charset=UTF-8")
	public Competition getSingle(){
		
		return competition;
		
	}
	
	@Path("/competitionteams")
	public CompetitionTeamsResource getTeams(){
		
		CompetitionTeamsResource resource = resourceContext.getResource(CompetitionTeamsResource.class);
		
		resource.setCompetition(competition);
		
		return resource;
		
	}
	
	@POST
	@Path("/join")
	@Consumes("multipart/form-data")
	public Response join(@FormDataParam("tid") Integer tid){
		
		service.JoinCompetition(tid, competition);
		
		return Response.created(URI.create(String.valueOf(competition.getId()))).build();
		
	}
}
