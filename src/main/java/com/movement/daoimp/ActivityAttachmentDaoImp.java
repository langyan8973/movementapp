package com.movement.daoimp;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.movement.bussiness.Activity;
import com.movement.bussiness.ActivityAttachment;
import com.movement.dao.ActivityAttachmentDao;

@Repository
public class ActivityAttachmentDaoImp extends GenericDAOImp﻿<ActivityAttachment, Integer> implements ActivityAttachmentDao {

	@Override
	public List<ActivityAttachment> getByActivity(Activity activity) {
		
		Criteria crit = getSession().createCriteria(ActivityAttachment.class);
		crit = crit.createCriteria("activity").add(Restrictions.eq("id", activity.getId()));
		crit.setCacheable(true);

		return (List<ActivityAttachment>)crit.list();
	}

}
