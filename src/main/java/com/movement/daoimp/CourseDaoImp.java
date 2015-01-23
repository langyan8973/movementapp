package com.movement.daoimp;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.movement.bussiness.Course;
import com.movement.bussiness.News;
import com.movement.bussiness.SportsEvent;
import com.movement.dao.CourseDao;

@Repository
public class CourseDaoImp extends GenericDAOImp﻿<Course, Integer> implements CourseDao {

	@Override
	public List<Course> findByEvent(SportsEvent event) {
		
		Criteria crit = getSession().createCriteria(Course.class);
		crit = crit.createCriteria("event").add(Restrictions.eq("id", event.getId()));
		crit.setCacheable(true);

		return (List<Course>)crit.list();
	}

}
