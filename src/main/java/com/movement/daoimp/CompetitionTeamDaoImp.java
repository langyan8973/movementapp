package com.movement.daoimp;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.movement.bussiness.Competition;
import com.movement.bussiness.CompetitionTeam;
import com.movement.dao.CompetitionTeamDao;

@Repository
public class CompetitionTeamDaoImp extends GenericDAOImp﻿<CompetitionTeam, Integer> implements CompetitionTeamDao {

	@Override
	public List<CompetitionTeam> findByCompetition(Competition c) {
		Criteria crit = getSession().createCriteria(CompetitionTeam.class);
		crit = crit.createCriteria("competition").add(Restrictions.eq("id", c.getId()));
		crit.setCacheable(true);

		return (List<CompetitionTeam>)crit.list();
	}
	
	

}
