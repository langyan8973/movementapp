package com.movement.dao;

import java.util.List;

import com.movement.bussiness.SportsEvent;
import com.movement.bussiness.Team;

public interface TeamDao extends GenericDao<Team, Integer> {

	public List<Team> findByEvent(SportsEvent event);
	
}
