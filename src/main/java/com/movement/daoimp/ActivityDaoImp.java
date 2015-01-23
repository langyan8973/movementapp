package com.movement.daoimp;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.movement.bussiness.Activity;
import com.movement.bussiness.News;
import com.movement.bussiness.SportsEvent;
import com.movement.dao.ActivityDao;

@Repository
public class ActivityDaoImp extends GenericDAOImp﻿<Activity, Integer> implements ActivityDao {

	public List<Activity> getByEvent(SportsEvent event){
		Criteria crit = getSession().createCriteria(Activity.class);
		crit = crit.createCriteria("event").add(Restrictions.eq("id", event.getId()));
		crit.setCacheable(true);

		return (List<Activity>)crit.list();
	}
	
}
