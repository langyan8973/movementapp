package com.movement.dao;

import java.util.List;

import com.movement.bussiness.Activity;
import com.movement.bussiness.SportsEvent;

public interface ActivityDao extends GenericDao<Activity, Integer> {

	public List<Activity> getByEvent(SportsEvent event);
	
	public List<Activity> findByExtent(double xmin,double xmax,double ymin,double ymax,SportsEvent event);
	
}
