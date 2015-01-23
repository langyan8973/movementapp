package com.movement.daoimp;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.movement.bussiness.CompetitionTeam;
import com.movement.bussiness.News;
import com.movement.bussiness.SportsEvent;
import com.movement.dao.NewsDao;


@Repository
public class NewsDaoImp extends GenericDAOImp﻿<News, Integer> implements
					NewsDao {
	
	public List<News> getNewsByEvent(SportsEvent event){
		Criteria crit = getSession().createCriteria(News.class);
		crit = crit.createCriteria("event").add(Restrictions.eq("id", event.getId()));
		crit.setCacheable(true);

		return (List<News>)crit.list();
	}

}
