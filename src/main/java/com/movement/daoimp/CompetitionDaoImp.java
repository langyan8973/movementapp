package com.movement.daoimp;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.movement.bussiness.Competition;
import com.movement.bussiness.News;
import com.movement.bussiness.Site;
import com.movement.bussiness.SportsEvent;
import com.movement.bussiness.Unit;
import com.movement.dao.CompetitionDao;

@Repository
public class CompetitionDaoImp extends GenericDAOImp﻿<Competition, Integer> implements CompetitionDao {

	@Override
	public List<Competition> findByEvent(SportsEvent event) {
		Criteria crit = getSession().createCriteria(Competition.class);
		crit = crit.createCriteria("event").add(Restrictions.eq("id", event.getId()));
		crit.setCacheable(true);

		return (List<Competition>)crit.list();
	}

	@Override
	public List<Competition> findByUnit(Unit unit) {
		
		Criteria crit = getSession().createCriteria(Competition.class);
		crit = crit.createCriteria("sponsor").add(Restrictions.eq("id", unit.getId()));
		crit.setCacheable(true);

		return (List<Competition>)crit.list();
	}

	@Override
	public List<Competition> findBySite(Site site) {
		
		Criteria crit = getSession().createCriteria(Competition.class);
		crit = crit.createCriteria("site").add(Restrictions.eq("id", site.getId()));
		crit.setCacheable(true);

		return (List<Competition>)crit.list();
	}

}
