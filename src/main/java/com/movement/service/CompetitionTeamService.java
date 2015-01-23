package com.movement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movement.bussiness.Competition;
import com.movement.bussiness.CompetitionTeam;
import com.movement.dao.CompetitionTeamDao;

@Service
@Transactional
public class CompetitionTeamService {
	
	@Autowired
	private CompetitionTeamDao dao;
	
	public List<CompetitionTeam> getTeamsByCompetition(Competition c){
		
		List<CompetitionTeam> competitionTeams = dao.findByCompetition(c);
		
		return competitionTeams;
		
	}

}
