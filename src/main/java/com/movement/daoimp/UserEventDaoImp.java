package com.movement.daoimp;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.movement.bussiness.SportsEvent;
import com.movement.bussiness.User;
import com.movement.bussiness.UserEvent;
import com.movement.dao.UserEventDao;

@Repository
public class UserEventDaoImp extends GenericDAOImp﻿<UserEvent, Integer> implements UserEventDao {

	@Override
	public UserEvent getByUserAndEvent(User user, SportsEvent event) {
		
		Criteria crit = getSession().createCriteria(UserEvent.class);
		crit = crit.add(Restrictions.eq("user.id", user.getId()));
		crit = crit.add(Restrictions.eq("event.id", event.getId()));
		crit.setCacheable(true);
		
		return (UserEvent)crit.uniqueResult();
	}

}
