package com.movement.daoimp;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.movement.bussiness.Activity;
import com.movement.bussiness.User;
import com.movement.bussiness.UserActivity;
import com.movement.bussiness.UserEvent;
import com.movement.dao.UserActivityDao;

@Repository
public class UserActivityDaoImp extends GenericDAOImp﻿<UserActivity, Integer> implements UserActivityDao {

	@Override
	public UserActivity getByUserAndActivity(User user, Activity activity) {
		
		Criteria crit = getSession().createCriteria(UserActivity.class);
		crit = crit.add(Restrictions.eq("user.id", user.getId()));
		crit = crit.add(Restrictions.eq("activity.id", activity.getId()));
		crit.setCacheable(true);
		
		return (UserActivity)crit.uniqueResult();
	}

	@Override
	public List<UserActivity> getByActivity(Activity activity) {
		Criteria crit = getSession().createCriteria(UserActivity.class);
		crit = crit.add(Restrictions.eq("activity.id", activity.getId()));
		crit.setCacheable(true);
		return (List<UserActivity>)crit.list();
	}

}
