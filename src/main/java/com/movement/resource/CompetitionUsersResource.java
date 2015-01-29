package com.movement.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.movement.bussiness.Competition;
import com.movement.bussiness.CompetitionUser;
import com.movement.service.CompetitionUserService;

@Component
public class CompetitionUsersResource {

	private Competition competition;

	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}
	
	@Autowired
	private CompetitionUserService service;
	
	@GET
	@Produces("application/json;charset=UTF-8")
	public List<CompetitionUser> getPlayers(){
		
		List<CompetitionUser> competitionUsers = service.getByCompetition(competition);
		
		return competitionUsers;
		
	}
	
}
