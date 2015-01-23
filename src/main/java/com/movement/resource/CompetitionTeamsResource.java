package com.movement.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.movement.bussiness.Competition;
import com.movement.bussiness.CompetitionTeam;
import com.movement.service.CompetitionTeamService;
import com.sun.jersey.api.core.ResourceContext;

@Component
public class CompetitionTeamsResource {
	
	private Competition competition;

	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}
	
	
	@Autowired
	private CompetitionTeamService service;
	
	@GET
	@Produces("application/json;charset=UTF-8")
	public List<CompetitionTeam> getTeams(){
		
		return service.getTeamsByCompetition(competition);
		
	}

}
