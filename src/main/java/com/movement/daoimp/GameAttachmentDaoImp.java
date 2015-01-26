package com.movement.daoimp;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.movement.bussiness.Course;
import com.movement.bussiness.Game;
import com.movement.bussiness.GameAttachment;
import com.movement.dao.GameAttachmentDao;

@Repository
public class GameAttachmentDaoImp extends GenericDAOImp﻿<GameAttachment, Integer> implements GameAttachmentDao {

	@Override
	public List<GameAttachment> findByGame(Game game) {
		
		Criteria crit = getSession().createCriteria(GameAttachment.class);
		crit = crit.createCriteria("game").add(Restrictions.eq("id", game.getId()));
		crit.setCacheable(true);

		return (List<GameAttachment>)crit.list();
		
	}

	
	
}
