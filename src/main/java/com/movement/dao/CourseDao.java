package com.movement.dao;

import java.util.List;

import com.movement.bussiness.Course;
import com.movement.bussiness.SportsEvent;

public interface CourseDao extends GenericDao<Course, Integer> {

	public List<Course> findByEvent(SportsEvent event);
	
}
