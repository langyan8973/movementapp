package com.movement.daoimp;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.movement.bussiness.Competition;
import com.movement.bussiness.CompetitionUser;
import com.movement.dao.CompetitionUserDao;

@Repository
public class CompetitionUserDaoImp extends GenericDAOImp﻿<CompetitionUser, Integer> implements CompetitionUserDao {

	@Override
	public List<CompetitionUser> findByCompetition(Competition competition) {
		
		Criteria crit = getSession().createCriteria(CompetitionUser.class);
		crit = crit.createCriteria("competition").add(Restrictions.eq("id", competition.getId()));
		crit.setCacheable(true);

		return (List<CompetitionUser>)crit.list();
	}

}
