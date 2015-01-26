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
import com.movement.bussiness.Game;
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
	@Path("/edit")
	@Consumes("application/json")
	public Response edit(Competition competition1){
		
		competition.setAddress(competition1.getAddress());
		
		competition.setDescription(competition1.getDescription());
		
		competition.setEvent(competition1.getEvent());
		
		competition.setSite(competition1.getSite());
		
		competition.setSponsor(competition1.getSponsor());
		
		competition.setStatus(competition1.getStatus());
		
		competition.setTime(competition1.getTime());
		
		competition.setTitle(competition1.getTitle());
		
		service.saveOrUpdate(competition);
		
		return Response.created(URI.create(String.valueOf(competition.getId()))).build();
		
	}
	
	@POST
	@Path("/join")
	@Consumes("multipart/form-data")
	public Response join(@FormDataParam("tid") Integer tid){
		
		service.JoinCompetition(tid, competition);
		
		return Response.created(URI.create(String.valueOf(competition.getId()))).build();
		
	}
	
	@POST
	@Path("/addgame")
	@Consumes("application/json")
	public Response addGame(Game game){
		
		game.setCompetition(competition);
		
		game.setStatus(0);
		
		service.saveOrUpdateGame(game);
		
		return Response.created(URI.create(String.valueOf(game.getId()))).build();
		
	}
	
	
}
