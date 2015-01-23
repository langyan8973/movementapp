package com.movement.dao;

import java.util.List;

import com.movement.bussiness.Competition;
import com.movement.bussiness.Site;
import com.movement.bussiness.SportsEvent;
import com.movement.bussiness.Unit;

public interface CompetitionDao extends GenericDao<Competition, Integer> {

	public List<Competition> findByEvent(SportsEvent event);
	
	public List<Competition> findByUnit(Unit unit);
	
	public List<Competition> findBySite(Site site);
	
}
