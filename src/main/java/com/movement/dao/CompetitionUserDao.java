package com.movement.dao;

import java.util.List;

import com.movement.bussiness.Competition;
import com.movement.bussiness.CompetitionUser;

public interface CompetitionUserDao extends GenericDao<CompetitionUser, Integer> {
	
	public List<CompetitionUser> findByCompetition(Competition competition);

}
