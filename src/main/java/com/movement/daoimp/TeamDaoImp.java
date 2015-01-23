package com.movement.daoimp;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.movement.bussiness.Activity;
import com.movement.bussiness.SportsEvent;
import com.movement.bussiness.Team;
import com.movement.dao.TeamDao;

@Repository
public class TeamDaoImp extends GenericDAOImp﻿<Team, Integer> implements TeamDao {

	@Override
	public List<Team> findByEvent(SportsEvent event) {
		
		Criteria crit = getSession().createCriteria(Team.class);
		crit = crit.createCriteria("event").add(Restrictions.eq("id", event.getId()));
		crit.setCacheable(true);

		return (List<Team>)crit.list();
	}

}
