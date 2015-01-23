package com.movement.dao;

import java.util.List;

import com.movement.bussiness.Competition;
import com.movement.bussiness.CompetitionTeam;

public interface CompetitionTeamDao extends GenericDao<CompetitionTeam, Integer> {
	
	public List<CompetitionTeam> findByCompetition(Competition c);

}
