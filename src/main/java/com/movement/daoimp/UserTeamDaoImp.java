package com.movement.daoimp;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.movement.bussiness.Activity;
import com.movement.bussiness.Team;
import com.movement.bussiness.UserTeam;
import com.movement.dao.UserTeamDao;

@Repository
public class UserTeamDaoImp extends GenericDAOImp﻿<UserTeam, Integer> implements UserTeamDao {

	@Override
	public List<UserTeam> getByTeam(Team team) {
		
		Criteria crit = getSession().createCriteria(UserTeam.class).add(
				Restrictions.not(Restrictions.eq("status", -1)));
		crit = crit.createCriteria("team").add(Restrictions.eq("id", team.getId()));
		crit.setCacheable(true);

		return (List<UserTeam>)crit.list();
	}

	@Override
	public List<UserTeam> getByTeamAndStatus(Team team,int status) {

		Criteria crit = getSession().createCriteria(UserTeam.class).add(Restrictions.eq("status", status));
		crit = crit.createCriteria("team").add(Restrictions.eq("id", team.getId()));
		crit.setCacheable(true);
	
		return (List<UserTeam>)crit.list();

	}

}
