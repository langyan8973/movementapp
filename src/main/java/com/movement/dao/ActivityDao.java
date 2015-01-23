package com.movement.dao;

import java.util.List;

import com.movement.bussiness.Activity;
import com.movement.bussiness.SportsEvent;

public interface ActivityDao extends GenericDao<Activity, Integer> {

	public List<Activity> getByEvent(SportsEvent event);
	
}
