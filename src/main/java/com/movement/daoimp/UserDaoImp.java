package com.movement.daoimp;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.movement.bussiness.User;
import com.movement.dao.UserDao;

@Repository
public class UserDaoImp extends GenericDAOImp﻿<User, Integer> implements UserDao {

	@Override
	public User findByOpenID(String openid, Integer snstype) {
		Criteria crit = getSession().createCriteria(User.class)
				.add(Restrictions.eq("openid", openid))
				.add(Restrictions.eq("sns_type", snstype));
		crit.setCacheable(true);
		return (User) crit.uniqueResult();
	}

	@Override
	public User findByUidSnsType(Integer uid, Integer snstype) {
		// TODO Auto-generated method stub
		return null;
	}

}
